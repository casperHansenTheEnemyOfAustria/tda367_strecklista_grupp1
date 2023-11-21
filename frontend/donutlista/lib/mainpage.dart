import 'package:flutter/material.dart';

import 'mainpageWidgets/grid.dart';
import 'mainpageWidgets/summary.dart';

Widget mainPage = const MainPage(); 

class MainPage extends StatelessWidget{
  const MainPage({super.key});
  @override
  Widget build(BuildContext context){
      return Column(
        children: [MainItemGrid(), SummaryList()

        ],
      );
  
  }
}








