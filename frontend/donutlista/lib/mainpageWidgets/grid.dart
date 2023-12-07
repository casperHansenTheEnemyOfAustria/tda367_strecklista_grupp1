
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:donutlista/main.dart';

import 'itemCard.dart';

  Map<String, String> itemLMap = {'Item 1':'Price 1', 'Item 2':'Price 2', 'Item 3':'Price 3'};


/* Widget: Grid of Counting buttons */

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
        itemCount: itemList.length,
        itemBuilder: (context, index) => ItemTile(index),
        gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
          crossAxisCount: 2,
        ),
      );
}
}


class ItemTile extends StatefulWidget {
  int itemNo;
    

  ItemTile(
    this.itemNo, {super.key}
  );

  @override
  State<ItemTile> createState() => _ActiveItemTile();
}

class _ActiveItemTile extends State<ItemTile> {
  int counter = 0;
  Map<String, String> itemMap = {'Item 1':'Price 1', 'Item 2':'Price 2', 'Item 3':'Price 3'};




  int _incrementCounter() {
    setState(() {
      counter++;
    });
    return counter;
  }

  int _decrementCounter() {
    setState(() {
      if (counter == 0)
        counter = 0;
      else 
        counter--; 
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
    
    return Padding(
      padding: const EdgeInsets.all(8.5),
      //TODO: Center
      child: Container(
        decoration: 
        BoxDecoration(
        color: HexColor('#09cdda'),
            ),
        child: 
          Column(            
          children: [
          Text('Item' 
            //ItemMap[itemNo],
            //key: Key('text_$itemNo'),
          ),
          Text(
            'PRIS'
            //itemList[itemNo],
            //key: Key('text_$itemNo'),
          ),
          Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            GestureDetector(
              onTap: () {
                      _decrementCounter();
                      print(counter);
                    },
              child: Icon(Icons.remove)),
            Text('${counter.toString()}'),
            GestureDetector(
              onTap: () {
                        _incrementCounter();
                        print(counter);
                        },
              child: Icon(Icons.add)),
                                  ]
          ),
          GestureDetector(
            onTap: () {
                    _resetCounter();
                    print(counter);
                  },
            child: Icon(Icons.delete)
            ),
          ],
        )
      )
    );
  }
}
