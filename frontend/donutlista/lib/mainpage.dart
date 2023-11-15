//TODO: Remove unused imports
// ignore: unused_import
import 'package:flutter/material.dart';
// ignore: unused_import
import 'package:hexcolor/hexcolor.dart';

class MainItemGrid extends StatelessWidget{
  const MainItemGrid({super.key});
  @override
  Widget build(BuildContext context){
      return  GridView.count(
      // Create a grid with 2 columns. If you change the scrollDirection to
      // horizontal, this produces 2 rows.
      crossAxisCount: 2,
      // Generate 100 widgets that display their index in the List.
      children: List.generate(100, (index) {
        return Center(
          child: Text(
            'Item $index',
            style: Theme.of(context).textTheme.headlineSmall,
          ),
        );
      }),
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
