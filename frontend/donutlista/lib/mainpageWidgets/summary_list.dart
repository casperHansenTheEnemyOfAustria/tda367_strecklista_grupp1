import 'package:flutter/material.dart';
import 'package:donutlista/main.dart';


/* Widget: Grid of Counting buttons */

class SummaryList extends StatelessWidget{
  const SummaryList({super.key});
  @override
  Widget build(BuildContext context){
      return 
      ListView.builder(
        itemCount: itemMap.length,
        itemBuilder: (context, index) => SummaryTile(index),
        );
}
}

class SummaryTile extends StatelessWidget {
  int itemNo;

  SummaryTile(this.itemNo,{super.key} );
  
  get currentCounter => null;  
  
  // TODO: Add getter for currentCounter 

  @override
  Widget build(BuildContext context) {
    
    return Padding(
      padding: const EdgeInsets.all(8.5),
      child: Row(            
      children: [
      const //Remove later 
      Text('Iteeem  '
        //itemMap[itemNo],
        //key: Key('text_$itemNo'),
        ),
      const //Remove later 
      Text(
        'PRIS :-  '
        //itemList[itemNo],
        //key: Key('text_$itemNo'),
        ),
        Text( 'multiplier  '
          '$currentCounter() to String'
          ),
      ],
        )
    );
  }
}
