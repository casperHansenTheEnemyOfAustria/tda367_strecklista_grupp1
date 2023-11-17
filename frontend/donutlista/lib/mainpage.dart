import 'package:flutter/material.dart';

Widget mainPage = const MainItemGrid(); 

class MainItemGrid extends StatelessWidget{
  const MainItemGrid({super.key});
  @override
  Widget build(BuildContext context){
      return GridView.count(
  primary: false,
  padding: const EdgeInsets.all(20),
  crossAxisSpacing: 10,
  mainAxisSpacing: 10,
  crossAxisCount: 2,
  children: 
    <Widget>[
      Container(
        padding: const EdgeInsets.all(8),
        child: ItemButton()
        ), 
    ],

/* TODO: Add summary list */

/* TODO: Add Buy and cancel button */

);
}
}


/* Counting button */
class ItemButton extends StatefulWidget {
  int counter = 0;

  ItemButton({super.key});

  @override
  ButtonState createState() => ButtonState();
}


class ButtonState extends State<ItemButton> {

  @override
  Widget build(BuildContext context) {

    return Container(
              decoration: BoxDecoration(
              //padding: const EdgeInsets.all(8),
              color: Colors.teal[100],
            ),
            child: 
                Column(            
                children: [
                Text("Item"), //TODO: Change to list from database
                Text("XX:-"), //TODO: Change to list from database
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    GestureDetector(
                      onTap: () => setState(() {
                              widget.counter == 0 ? print('counter at 0') : widget.counter--;
                            }),
                        child: Icon(Icons.remove)),
                    Text('${widget.counter}'),
                    GestureDetector(
                      onTap: () {setState(() {
                        print('set');
                                widget.counter++;
                            });},
                        child: Icon(Icons.add)),
                  
                  ],
            ),
            GestureDetector(
                      onTap: () => setState(() {
                              widget.counter == 0 ? print('counter at 0') : widget.counter = 0;
                            }),
                        child: Icon(Icons.delete)),
            ]
            )
            );
  }
}

/* Summary list */
