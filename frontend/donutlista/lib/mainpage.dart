import 'package:flutter/material.dart';

Widget mainPage = Container(
  child: MainItemGrid()
      ); 

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
  children: <Widget>[
    Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[100],
      child: const Text("Doughnut"), 
    ),
    Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[200],
      child: const Text('Billys'),
    ),
    Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[300],
      child: const Text('Powerking'),
    ),
    Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[400],
      child: const Text('zxcvbn'),
    ),
    Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[500],
      child: const Text('dfghjkl'),
    ),
    Container(
      padding: const EdgeInsets.all(8),
      color: Colors.teal[600],
      child: const Text('ertyuio'),
    ),
  ],
);
}
}

//Contaioners for page switches 
//Contaner 1: Main Page / Strecklista
/* Test
Widget mainPage = Container(
  child: const Column(
    children: <Widget>[
      Row(children: <Widget>[
        Expanded(
          child: Text ('''Dounuts''' '15:-' 'Antal')
           ,),
        Expanded(
          child: Text ('''PowerKing''' '12:-' 'Antal') 
          ,),
        Expanded(
          child: Text ('''Billys''' '10:-' 'Antal')
      )
      ],)
    ]

  
  ),
  ); 
*/

/* Counting button */
//TODO: Fix button code 

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
              borderRadius: BorderRadius.circular(10),
              color: Colors.green,
            ),
            child: Row(
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
            ),);
  }
}