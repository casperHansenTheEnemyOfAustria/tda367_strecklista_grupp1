import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'login.dart';

/* MyApp gives theme and starts off att login page */

List<String> itemList = ['Item 1', 'Item 2', 'Item 3'];
//Map<String, String> itemList = {'Item 1':'Price 1', 'Item 2':'Price 2', 'Item 3':'Price 3'};

Map<String, int> ItemMap = {
  "Item 1": 10,
  "Item 2": 20,
  "Item 3": 30
};

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        colorScheme: const ColorScheme.highContrastDark().copyWith(
          secondary: HexColor("#09cdda")
        )
      ),
      debugShowCheckedModeBanner: false,
      home: LoginPage(),
    );
  }
}