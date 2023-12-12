import 'package:flutter/material.dart';

/* Navigation to purchase or delete order */

class PurchaseButtons extends StatefulWidget {
  const PurchaseButtons({super.key, 
  });

  @override
  State<PurchaseButtons> createState() => _PurchaseButtonsState();
}

class _PurchaseButtonsState extends State<PurchaseButtons> {
  /*
  Future<void> sendBuyPostRequest() async {
    // Get the text from the forms
    var content = {"userName": userName, "password": password};
    final response = await http.post(
      Uri.http(apiUrl, '/login'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );

    if (response.statusCode == 200) {
      Navigator.push(context,
          MaterialPageRoute(builder: (_) => HomeScreen(userID: response.body)));
      print(response.body);
    } else {
      snackBar;
    }
  }
  */
  
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
                backgroundColor: MaterialStatePropertyAll<Color>(Color.fromARGB(255, 98, 98, 98)),
                foregroundColor: MaterialStateProperty.all<Color>(Colors.white)
                
                ),
                onPressed: () { },
              child: Text('Cancel'),
            ),
          ), 
          
          Expanded(
            child:TextButton(
              style: ButtonStyle(
                backgroundColor: MaterialStatePropertyAll<Color>(Color.fromARGB(255, 76, 175, 172)),
                foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
              ),
              onPressed: () { },
              child: Text('Buy'),
            )
          )
        ],

      )
      
        );
        
  }
}
