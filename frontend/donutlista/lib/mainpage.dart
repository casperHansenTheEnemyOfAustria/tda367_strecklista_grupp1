import 'package:flutter/material.dart';

import 'mainpageWidgets/grid.dart';
import 'mainpageWidgets/summary.dart';
import 'mainpageWidgets/buyOrCancel.dart';


/* TODO: Create combinig layout (top layout) */

/* TODO: <subgoal> Add ItemGrid */
/* TODO: <subgoal> Add summary list */
/* TODO: <subgoal> Add Buy and cancel button */

Widget mainPage = const MainPage(); 

class MainPage extends StatelessWidget{
  const MainPage({super.key});
  @override
  Widget build(BuildContext context){
      return Column(
        children: [ MainItemGrid(), SummaryList()

        ],
      );
  
  }
}








