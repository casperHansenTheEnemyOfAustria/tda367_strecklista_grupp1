import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'login.dart';

/* MyApp gives theme and starts off att login page */

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
          colorScheme: const ColorScheme.highContrastDark()
              .copyWith(secondary: HexColor("#09cdda"))),
      debugShowCheckedModeBanner: false,
      home: const LoginPage(),
    );
  }
}
