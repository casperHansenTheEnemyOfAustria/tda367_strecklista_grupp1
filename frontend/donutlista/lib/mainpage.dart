import 'package:flutter/material.dart';

import 'mainpageWidgets/grid.dart';
import 'mainpageWidgets/summary.dart';
import 'mainpageWidgets/buyOrCancel.dart';


/* TODO: Create combinig layout (top layout) */

/* TODO: <subgoal> Add ItemGrid */
/* TODO: <subgoal> Add summary list */
/* TODO: <subgoal> Add Buy and cancel button */

Widget mainPage = const MainPageLayout(); 

class MainPageLayout extends StatelessWidget{
  const MainPageLayout({super.key});
  @override
  Widget build(BuildContext context){
      return Column(
        children: [ MainItemGrid(), SummaryList()

        ],
      );
  
  }
}








