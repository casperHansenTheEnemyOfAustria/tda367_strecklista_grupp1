import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

import 'itemCard.dart';

List<String> itemList = ['Item 1', 'Item 2', 'Item 3'];

/* Widget: Grid of Counting buttons */

class MainItemGrid extends StatelessWidget{
  const MainItemGrid({super.key});
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

class ItemTile extends StatelessWidget {
  final int itemNo;
  int counter = 0;

  ItemTile(
    this.itemNo, {super.key}
  );

  @override
  Widget build(BuildContext context) {
    final Color color = Colors.primaries[itemNo % Colors.primaries.length];
    
    /*
    leading: FlutterLogo(size: 72.0),
    title: Text('Three-line ListTile'),
    subtitle:
      Text('A sufficiently long subtitle warrants three lines.'),
    trailing: Icon(Icons.more_vert),
    isThreeLine: true,
    */
    
    return Padding(
      padding: const EdgeInsets.all(8.5),
      //TODO: Center
      child: Container(
              decoration: 
              BoxDecoration(
                /* border: Border.all(
                color: HexColor('#09CDDA'),
                width: 1,
                ), */
              color: HexColor('#09cdda'),
            ),
            child: 
                Column(            
                children: [
                Text(
                  itemList[itemNo],
                  key: Key('text_$itemNo'),
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
                              if (counter == 0)
                                counter = 0;
                              else 
                                counter--; 
                              print(counter);
                            },
                      child: Icon(Icons.remove)),
                    Text('${counter.toString()}'),
                    GestureDetector(
                      onTap: () {
                                counter++;
                                print(counter);
                                },
                      child: Icon(Icons.add)),
                                  ]
            ),
          GestureDetector(
            onTap: () {
                    counter = 0;
                    //print(counter);
                  },
            child: Icon(Icons.delete)
            ),
          ],
        )
      )
    );
  }
}
