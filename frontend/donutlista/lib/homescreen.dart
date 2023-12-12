import 'package:donutlista/page_main.dart';
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

/*
Homescreen shows all widgets on the page, 
the appbar with menu, and the page the user is on.
*/

class HomeScreen extends StatelessWidget {
  final String userID;

  const HomeScreen({super.key, required this.userID});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        theme: ThemeData(
            colorScheme: const ColorScheme.highContrastDark()
                .copyWith(secondary: HexColor("#09cdda"))),
        title: 'StecklistIT',
        home: MainPage(
          title: 'Välkommen tillbaka!',
          userID: userID,
        ));
  }
}
