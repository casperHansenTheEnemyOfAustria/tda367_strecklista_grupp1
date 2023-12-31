import 'dart:convert';
import 'dart:io';
import 'package:donutlista/homescreen.dart';
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:http/http.dart' as http;
import 'package:donutlista/globals.dart' as globals;

//TODO: Fix counter and make dynamic


/* Navigation to purchase or delete order */

class PurchaseButtons extends StatefulWidget {
  const PurchaseButtons({super.key, 
  });

  @override
  State<PurchaseButtons> createState() => _PurchaseButtonsState();
}

class _PurchaseButtonsState extends State<PurchaseButtons> {
  
  static const yaySnackBar = SnackBar(
  content: Text('Yay! Köpet godkändes!'),
  /*action: SnackBarAction(
    label: 'Undo',
    onPressed: () {
      // Some code to undo the change. //TODO later: Add undo button to buy snackbar 
    },
  ),*/
  );
  static const naySnackBar = SnackBar(
  content: Text('Nay! Köpet gick inte igenom!'),
  );
  static const cancelSnackBar = SnackBar(
  content: Text('Köp avbrutet'),
  );
  
  Future<void> sendBuyPostRequest() async {
    // Get the text from the forms
    var content = {
      "sessionID": globals.sessionID
      };
    final response = await http.post(
      Uri.http(globals.apiUrl, '/completePurchase'), //TODO: Byt till rätt för att se om det funkar 
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
  
  if (response.statusCode == 200) {
      // ignore: use_build_context_synchronously
      ScaffoldMessenger.of(context).showSnackBar(yaySnackBar);
      globals.summaryOfThingsInCart.value = List.filled(1, {});
      var duration = const Duration(seconds: 1);
      
      await Future.delayed(const Duration(milliseconds: 500));
 
      Navigator.push(context,
          MaterialPageRoute(builder: (_) => HomeScreen(userID: globals.sessionID)));
        globals.updateTransactionList();
    } else {
      // ignore: use_build_context_synchronously
      ScaffoldMessenger.of(context).showSnackBar(naySnackBar);
    }
  }

      @override
      Widget build(BuildContext context) {
        return FractionallySizedBox(
      widthFactor: 1.0,
      heightFactor: 0.07,
      
      child: Row(
        children: [
          Expanded(
            child: TextButton(
              
              style: ButtonStyle(
                backgroundColor: const MaterialStatePropertyAll<Color>(Color.fromARGB(255, 98, 98, 98)),
                foregroundColor: MaterialStateProperty.all<Color>(Colors.white)
                
                ),
                onPressed: () { //TODO: onPressed: (reset all counters)
                  ScaffoldMessenger.of(context).showSnackBar(cancelSnackBar);
                 },
              child: const Text('Avbryt'),
            ),
          ), 
          
          Expanded(
            child:TextButton(
              style: ButtonStyle(
                backgroundColor: MaterialStatePropertyAll<Color>(HexColor('#09CDDA')),
                foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
              ),
              onPressed: sendBuyPostRequest,
              // ignore: prefer_const_constructors
              child: Text('Köp'),// TODO: Add sum(price*multipier):- here
            )
          )
        ],

      )
      
        );
      }
  }
  

