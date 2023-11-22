import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';


/* Counting Card */
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
