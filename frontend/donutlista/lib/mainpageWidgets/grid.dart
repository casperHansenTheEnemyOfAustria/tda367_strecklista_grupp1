import 'package:flutter/material.dart';

import 'button.dart';


/* Widget: Grid of Counting buttons */

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
        child: ItemButton(),
        ), 
    ],
);
}
}
