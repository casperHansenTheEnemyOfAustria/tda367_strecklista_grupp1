import 'package:flutter/material.dart';
import 'package:donutlista/main.dart';
import 'package:donutlista/globals.dart' as globals;


/* Widget: Grid of Counting buttons */

class SummaryList extends StatelessWidget{
  const SummaryList({super.key});

  @override
    Widget build(BuildContext context){
      Map<String, String> summaryOfThingsInCart = Map();
        return ValueListenableBuilder(
            
    
            valueListenable: globals.summaryOfThingsInCart,
            builder: (context, value, widget) {
            summaryOfThingsInCart.addAll( value);
            print("hello");
            return Text(   
                summaryOfThingsInCart.toString()
            );
        });
  
    }
}

// ignore: must_be_immutable
class SummaryTile extends StatefulWidget {
  int itemNo;
  String itemCount = "";
  String price = "";
  String itemName = "";
  String itemID = "";

  SummaryTile(this.itemNo,this.itemID, this.itemCount, {super.key} );

  @override
  State<SummaryTile> createState() => _SummaryTileState();


  setPrice(String price) {
    this.price = price;
  }
  setItemName(String itemName) {
    this.itemName = itemName;
  }

}

class _SummaryTileState extends State<SummaryTile> {
  get currentCounter => null;  

  // TODO: Add getter for currentCounter 
  @override
  Widget build(BuildContext context) {

    return FutureBuilder<Map<String,String>>(
      future: globals.sendGetProductRequest(widget.itemID),
      builder:(context, snapshot){
        print(snapshot.data);
        if(snapshot.connectionState == ConnectionState.done){
          
            return Padding(
              padding: const EdgeInsets.all(8.5),
              child: Row(            
              children: [
              Text(
                snapshot.data!["Name"]!
              ),

              Text(
                (int.parse(widget.itemCount)*int.parse(snapshot.data!["Price"]!)).toString()//TODO: Add itemPrice here
                //itemList[itemNo],
                //key: Key('text_$itemNo'),
                ),
                Text('$counter'
                  ),
              ],
                )
            );
          
        }
        else{
          return const Center(child: Text('loading...'));
        }
      }
    );


  }

  void getTotalPrice(AsyncSnapshot<Map<String, String>> snapshot) {
    var totalPrice = 0;
    for(String price in snapshot.data!.values){
      totalPrice += int.parse(price);
    }
  }
}
