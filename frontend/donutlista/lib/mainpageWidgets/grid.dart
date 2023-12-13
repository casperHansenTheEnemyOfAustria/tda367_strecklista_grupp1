
import 'dart:collection';
import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:donutlista/globals.dart' as globals;
import 'package:http/http.dart' as http;



/* Widget: Grid of Counting buttons */

//TODO: If counter is over 0 Add(Name,Price,Multiplier) to SummaryList

//TODO: Create getter for counter so that it updates
//var counter = ActionListener(counter);

class MainItemGrid extends StatefulWidget{

  const MainItemGrid({super.key});

  @override
  State<MainItemGrid> createState() => _ItemGridState();
}
class _ItemGridState extends State<MainItemGrid> {
  Future<List<Map<String, String>>> itemList = Future(() => List.empty());
       

  
  Future<List<Map<String, String>>> sendItemsPostRequest() async {
    // Get the text from the forms
    var content = {"sessionID": globals.sessionID};
    final response = await http.post(
      Uri.http(globals.apiUrl, '/getProducts'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );

    if (response.statusCode == 200) {
      // ignore: use_build_context_synchronously, avoid_print
      
   
      return jsonDecode(response.body);
    } else {
      return List.empty();
      //TODO CAT
    }
  }

  @override
  Widget build(BuildContext context) {
      // List<Map<String, String>>coolerItemList = List.empty(); 
      // sendItemsPostRequest().then((value) {
      //       for (var element in value) {
      //         print(element);
      //         coolerItemList.add(element);
      //       }
      //     setState((){
      //       itemList = coolerItemList;
      //     });
         
      // });
      // itemList = 
      // print(itemList);
      // print("hi");
    
     
      return 
      Scaffold(
        body: FutureBuilder<List<Map<String, String>>>(
          future: sendItemsPostRequest(),
          builder: (context, snapshot) {
            var list = snapshot!.data;
            List<Map<String, String>>coolerItemList = List.empty(); 
            for (var element in list!) {
              print(element);
              coolerItemList.add(element);
            }
            return GridView.builder(
              itemCount: snapshot.data!.length,
              itemBuilder: (context, index) => ItemTile(snapshot.data!.elementAt(index)),
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
              )
            ); 
          },
        )

      );
      
}
}


// ignore: must_be_immutable
class ItemTile extends StatefulWidget {
//TODO: Change to ItemID & Add itemName, ItemPrice
  Map<String, String> listItem;
   
  ItemTile(
    this.listItem, {super.key}
  );
 
  @override
  State<ItemTile> createState() => _ActiveItemTile();
}

class _ActiveItemTile extends State<ItemTile> {
  var counter = 0;
  Map<String, String> listItem = Map();


  _ActiveItemTile(){
    
    listItem = widget.listItem;
  }
  int _incrementCounter() {
    setState(() {
      //TODO api add to cart
      counter++;
    });
    return counter;
  }

  int _decrementCounter() {
    setState(() {
      if (counter == 0) {
        counter = 0;
      } else {
        //TODO remove from cart
        counter--;
      } 
    });
    return counter;
  }


  int _resetCounter() {
    setState(() {
      //TODO api empty cart
      counter = 0;
    });
    return counter;
  }


  @override
  Widget build(BuildContext context) {
    //final Color color = Colors.primaries[itemNo % Colors.primaries.length];
    return 
    /*
    AnimatedBuilder(
      animation: animation, 
      builder: builder)
    */
    Padding(
      padding: const EdgeInsets.all(8.5),
      
      /*
      child: FutureBuilder<String>(
        future: _calculation, // a previously-obtained Future<String> or null
        builder: (BuildContext context, AsyncSnapshot<String> snapshot) {
      */
      
      child: Container(
        decoration: 
        BoxDecoration(
        color: HexColor('#09BABE'),
            ),
        child: 
          Column(            
          children: [
          // ignore: prefer_const_constructors
          Text(//TODO: Add "productName" here
            //ItemMap[itemNo],
            //key: Key('text_$itemNo'),
            listItem["Name"]!
          ),
          // ignore: prefer_const_constructors
          Text(
            //'PRIS' //TODO: Add "productCost" here
            //itemList[itemNo],
            //key: Key('text_$itemNo'),
            listItem["Price"]!,
          ),
          Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            GestureDetector(

              onTap: () {
                      _decrementCounter();
                      //print(counter);
                    },
              child: const Icon(Icons.remove)),
            Text('$counter'),
            GestureDetector(
              onTap: () {
                        _incrementCounter();
                        //print(counter);
                        },
              child: const Icon(Icons.add)),
                                  ]
          ),
          GestureDetector(
            onTap: () {
                    _resetCounter();
                    //print(counter);
                  },
            child: const Icon(Icons.delete)
            ),
          ],
        )
      )
    );
  }
}
