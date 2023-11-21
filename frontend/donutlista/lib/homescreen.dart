import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'mainpage.dart';
import 'navigation.dart';


/*
Homescreen shows all widgets on the page, 
the appbar with menu, and the page the user is on.
*/

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        colorScheme: const ColorScheme.highContrastDark().copyWith(
          secondary: HexColor("#09cdda")
        )
      ),
      title: 'StecklistIT',
      home: Scaffold(
        appBar: AppBar(
          title: Text('Current Page' //Add current page according to which is displayed
          ),
        ),
        body: Semantics(
          container: true, 
          child: Center(
          ),
        ),
        drawer: Drawer(child: NavDrawer(),)
          ),

//TODO: Add page switch as homescreen body
/* Find a way to connect which dropdown is selected with a correct change to the body */



        );
  }
}
