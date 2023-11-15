import 'package:donutlista/appbar.dart';
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget{
  const MyApp({super.key});
  @override
  Widget build(BuildContext context){
    return const HomeScreen();
  }
}

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
          backgroundColor: const Color.fromARGB(255, 63, 63, 63),
          actionsIconTheme: const IconThemeData(
          size: 30.0,
          opacity: 10.0
  ),
          title: Container(child: titleSection)
          ),

//TODO: Add page switch as homescreen body

        ),
    );
  }
}






