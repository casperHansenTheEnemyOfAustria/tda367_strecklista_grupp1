import 'package:donutlista/mainpageWidgets/bottomNav.dart';
import 'package:donutlista/mainpageWidgets/grid.dart';
import 'package:donutlista/mainpageWidgets/itemCard.dart';
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

import 'mainpageWidgets/summary_list.dart';
import 'navigation.dart';


class MainPage extends StatefulWidget { 
  const MainPage({super.key, required this.title});
  final String title;
  
  @override
  State<MainPage> createState() => _MainPage();
  }

class _MainPage extends State<MainPage> {
  @override
  Widget build(BuildContext context) {
  return Scaffold(
    drawer: NavDrawer(),
    appBar: AppBar(
      title: Text(widget.title),
      ),
    body: Center(
    child: MainItemGrid(),
    ),
    bottomSheet:
      Container(
        decoration: BoxDecoration(
          /*border: Border.all(
            color: HexColor('#09CDDA'),
            width: 0,
          ), */
          //borderRadius: BorderRadius.circular(10),     
          color: HexColor('#000000'),
          ),
        child: 
      SummaryList(), //TODO: Implement w controller
      ),
    bottomNavigationBar: 
      PurchaseButtons(), //TODO
    );
  }
}






