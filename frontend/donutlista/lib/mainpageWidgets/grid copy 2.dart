import 'package:flutter/material.dart';

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
    return Padding(
      padding: const EdgeInsets.all(8.5),
      child: ListTile(
        tileColor: color.withOpacity(0.3),
        //onTap: () {},
        leading: 
          Container(
          width: 100,
          //height: 20,
          color: color.withOpacity(0.5),
          child:
            Column(            
            children: [
              Text(
                  itemList[itemNo],
                  key: Key('text_$itemNo'),
                  ),
            Row(
              //mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                
                GestureDetector(
                  onTap: () {
                          if (this.counter == 0)
                            this.counter = 0;
                          else 
                            this.counter--; 
                          print(this.counter);
                        },
                    child: Icon(Icons.remove)),
                Text('${this.counter}'),
                GestureDetector(
                  onTap: () {
                            this.counter++;
                            print(this.counter);

                        },
                    child: Icon(Icons.add)),
              
              ],
        ),
        GestureDetector(
                  onTap: () {
                          this.counter = 0;
                          print(this.counter);

                        },
                    child: Icon(Icons.delete)),
        ]
        )
        ),
      ),
    );
  }
}
