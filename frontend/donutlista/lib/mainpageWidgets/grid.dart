import 'dart:collection';
import 'dart:convert';

import 'dart:io';

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:donutlista/globals.dart' as globals;
import 'package:http/http.dart' as http;

/* Widget: Grid of Counting buttons */

//TODO: If counter is over 0 Add(Name,Price,Multiplier) to SummaryList

//TODO: Create getter for counter so that it updates
//var counter = ActionListener(counter);

class MainItemGrid extends StatefulWidget {
  const MainItemGrid({super.key});

  @override
  State<MainItemGrid> createState() => _ItemGridState();
}

class _ItemGridState extends State<MainItemGrid> {
  Future<String> itemListJson = Future.value("");

  Future<String> sendItemsPostRequest() async {
    print("hihihi");
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

      return response.body;
    } else {
      print("cock");
      return "";

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
    setState(() {
      itemListJson = sendItemsPostRequest();
    });
    // print(itemListJson);

    return Scaffold(
        body: FutureBuilder<String>(
      future: itemListJson,
      builder: (context, snapshot) {
        // print(snapshot.data);

        // List<dynamic> list = jsonDecode(snapshot.data as String);
        if (snapshot.connectionState == ConnectionState.done) {
          if (snapshot.hasError) {
            return const Text('Error');
          }
          List<Map<String, String>> outmap = List.empty();
          List<dynamic> list = jsonDecode(snapshot.data! as String);
          list.forEach((element) {
            element.toString();
            Map<String, String> map = Map();
            map["Name"] = element["Name"];
            map["Price"] = element["Price"];
            map["Id"] = element["Id"];
            map["Amount"] = element["Amount"];
            outmap += List.from([map]);
          });
          print(outmap[0]);

          return GridView.builder(
            itemCount: outmap.length,
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
              mainAxisSpacing: 10,
              crossAxisSpacing: 10,
            ),
            itemBuilder: (BuildContext context, int index) =>
                ItemTile(index, outmap[index]),
          );
        } else if (snapshot.hasError) {
          return const Text('Error');
        }
        return const Center(
          child: CircularProgressIndicator(),
        );
      },
    ));
  }
}

// ignore: must_be_immutable
class ItemTile extends StatefulWidget {
//TODO: Change to ItemID & Add itemName, ItemPrice
  Map<String, String> listItem;
  int index;

  ItemTile(this.index, this.listItem, {super.key});

  sendAddToCartRequest(String itemId) async {
    
    var content = {
      "sessionID": globals.sessionID,
      "data": {
        "productID": itemId,
      }
    };
    final response = await http.post(
      Uri.http(globals.apiUrl, '/addToCart'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    return response.statusCode == 200;
  }

  sendRemoveFromCartRequest(String itemId) async {
    var content = {
      "sessionID": globals.sessionID,
      "data": {
        "productID": itemId,
      }
    };
    final response = await http.post(
      Uri.http(globals.apiUrl, '/removeFromCart'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    return response.statusCode == 200;
  }

  sendResetCartRequest(String itemId) async {
    var content = {
      "sessionID": globals.sessionID,
      "data": {
        "productID": itemId,
      }
    };
    final response = await http.post(
      Uri.http(globals.apiUrl, '/resetCart'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    return response.statusCode == 200;
  }
  

  @override
  State<ItemTile> createState() => _ActiveItemTile();
}

class _ActiveItemTile extends State<ItemTile> {
    
  var counter = 0;

  int _incrementCounter() {
    globals.sendGetCartRequest().then((value) => print(value));
    
    setState(() {
      widget
          .sendAddToCartRequest(widget.listItem["Id"]!)
          .then((value) => counter++);
    });
    globals.summaryOfThingsInCart.value.add(widget.listItem["Name"]!+ ": Price: " + widget.listItem["Price"]! + ", Total: "+ (double.parse(widget.listItem["Price"]!)*counter).toString());
    return counter;
  }

  int _decrementCounter() {
    setState(() {
      if (counter == 0) {
        counter = 0;
      } else {
        widget
            .sendRemoveFromCartRequest(widget.listItem["Id"]!)
            .then((value) => counter--);
      }
    });
  globals.summaryOfThingsInCart.value.add(widget.listItem["Name"]!+ ": Price: " + widget.listItem["Price"]! + ", Total: "+ (double.parse(widget.listItem["Price"]!)*counter).toString());
    return counter;
  }

  int _resetCounter() {
    setState(() {globals.sendGetCartRequest().then((value) => print(value));
      widget
        .sendResetCartRequest(widget.listItem["Id"]!) //TODO create ResetCart
        .then((value) => counter = 0);
    });
    globals.summaryOfThingsInCart.value.add(widget.listItem["Name"]!+ ": Price: " + widget.listItem["Price"]! + ", Total: "+ (double.parse(widget.listItem["Price"]!)*counter).toString());
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
                decoration: BoxDecoration(
                  color: HexColor('#09BABE'),
                ),
                child: Column(
                  children: [
                    // ignore: prefer_const_constructors
                    Text(//TODO: Add "productName" here
                        //ItemMap[itemNo],
                        //key: Key('text_$itemNo'),
                        widget.listItem["Name"]!),
                    // ignore: prefer_const_constructors
                    Text(
                        //'PRIS' //TODO: Add "productCost" here
                        //itemList[itemNo],
                        //key: Key('text_$itemNo'),
                        widget.listItem["Price"]!),
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
                        ]),
                    GestureDetector(
                        onTap: () {
                          _resetCounter();
                          //print(counter);
                        },
                        child: const Icon(Icons.delete)),
                  ],
                )));
  }
}
