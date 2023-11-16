import 'package:flutter/material.dart';


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
