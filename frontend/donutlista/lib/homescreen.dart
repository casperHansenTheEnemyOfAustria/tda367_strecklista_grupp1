import 'package:flutter/material.dart';
import 'appbar.dart';
import 'mainpage.dart';

/*
Homescreen shows all widgets on the page, 
the appbar with menu, and the page the user is on.
*/

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      
      title: 'StecklistIT',
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: const Color.fromARGB(255, 63, 63, 63),
          actionsIconTheme: const IconThemeData(
          size: 30.0,
          opacity: 10.0
  ),
          title: Container(child: titleSection),
          ),
        body: MainPage()
              

//TODO: Add page switch as homescreen body
/* Find a way to connect which dropdown is selected with a correct change to the body */



        ),
    );
  }
}
