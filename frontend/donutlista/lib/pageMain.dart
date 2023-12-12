import 'package:donutlista/mainpageWidgets/bottomNav.dart';
import 'package:donutlista/mainpageWidgets/grid.dart';
import 'package:flutter/material.dart';

import 'mainpageWidgets/summary_list.dart';
import 'navigation.dart';

class MainPage extends StatefulWidget {
  final String userID;
  final String title;
  const MainPage({super.key, required this.title, required this.userID});

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
        body: Column(children: [
          SizedBox(height: 400.0, child: MainItemGrid()),
          SizedBox(
            height: 150.0,
            child: SummaryList(),
          )
        ]),
        bottomNavigationBar: PurchaseButtons()); //TODO
  }
}
