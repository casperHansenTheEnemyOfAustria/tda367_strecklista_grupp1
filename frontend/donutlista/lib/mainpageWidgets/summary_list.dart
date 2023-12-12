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

// ignore: must_be_immutable
class SummaryTile extends StatefulWidget {
  int itemNo;

  SummaryTile(this.itemNo,{super.key} );

  @override
  State<SummaryTile> createState() => _SummaryTileState();
}

class _SummaryTileState extends State<SummaryTile> {
  get currentCounter => null;  

  // TODO: Add getter for currentCounter 
  @override
  Widget build(BuildContext context) {
    
    return Padding(
      padding: const EdgeInsets.all(8.5),
      child: Row(            
      children: [
      const //Remove later 
      Text('Item' //TODO: Add itemName here
        //itemMap[itemNo],
        //key: Key('text_$itemNo'),
        ),
      const //Remove later 
      Text(
        'PRIS :-  ' //TODO: Add itemPrice here
        //itemList[itemNo],
        //key: Key('text_$itemNo'),
        ),
        Text('$counter'
          ),
      ],
        )
    );
  }
}
