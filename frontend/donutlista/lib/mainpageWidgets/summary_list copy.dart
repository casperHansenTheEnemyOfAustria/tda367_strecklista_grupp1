import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:donutlista/main.dart';
import 'grid.dart';


/* Widget: Grid of Counting buttons */

class SummaryList extends StatelessWidget{
  const SummaryList({super.key});
  @override
  Widget build(BuildContext context){
      return 
      ListView.builder(
        itemCount: itemList.length,
        itemBuilder: (context, index) => ItemTile(index),
        );
}
}

class ItemTile extends StatelessWidget {
  int itemNo;

  ItemTile(
    this.itemNo, {super.key}
  );

  @override
  Widget build(BuildContext context) {
    final Color color = Colors.primaries[itemNo % Colors.primaries.length];
    
    return Padding(
      padding: const EdgeInsets.all(8.5),
      //TODO: Center
      child: Container(
        child: 
          Row(            
          children: [
          Text(
            itemList[itemNo],
            key: Key('text_$itemNo'),
            ),
          Text(
            'PRIS * multipler :-'
            //itemList[itemNo],
            //key: Key('text_$itemNo'),
            ),
          ],
        )
      )
    );
  }
}
