
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:donutlista/main.dart';

/* Widget: Grid of Counting buttons */

//TODO: If counter is over 0 Add(Name,Price,Multiplier) to SummaryList

//TODO: Create getter for counter so that it updates
var counter = 0; 
//var counter = ActionListener(counter);

class MainItemGrid extends StatefulWidget{

  const MainItemGrid({super.key});

  @override
  State<MainItemGrid> createState() => _ItemGridState();
}
class _ItemGridState extends State<MainItemGrid> {
  
  @override
  Widget build(BuildContext context){
      return 
      GridView.builder(
        itemCount: itemMap.length,
        itemBuilder: (context, index) => ItemTile(index),
        gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
        ),
      );
}
}


// ignore: must_be_immutable
class ItemTile extends StatefulWidget {
  int itemNo; //TODO: Change to ItemID & Add itemName, ItemPrice
    

  ItemTile(
    this.itemNo, {super.key}
  );

  @override
  State<ItemTile> createState() => _ActiveItemTile();
}

class _ActiveItemTile extends State<ItemTile> {



  int _incrementCounter() {
    setState(() {
      counter++;
    });
    return counter;
  }

  int _decrementCounter() {
    setState(() {
      if (counter == 0) {
        counter = 0;
      } else {
        counter--;
      } 
    });
    return counter;
  }


  int _resetCounter() {
    setState(() {
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
      child: Container(
        decoration: 
        BoxDecoration(
        color: HexColor('#09BABE'),
            ),
        child: 
          Column(            
          children: [
          // ignore: prefer_const_constructors
          Text('Item' //TODO: Add itemName here
            //ItemMap[itemNo],
            //key: Key('text_$itemNo'),
          ),
          // ignore: prefer_const_constructors
          Text(
            'PRIS' //TODO: Add itemPrice here
            //itemList[itemNo],
            //key: Key('text_$itemNo'),
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
