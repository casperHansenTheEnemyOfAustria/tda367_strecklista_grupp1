import 'package:donutlista/appbar.dart';
import 'package:donutlista/mainpage.dart';
import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'package:http/http.dart' as http;
import 'login.dart';

/* MyApp gives theme and starts off att login page */

void main() => runApp(MyApp());

String BaseURI = 'http://localhost:8080';


class URLparser{
  static parseURL(String path) async { 
    var url = Uri.parse(BaseURI+path);
    var response = await http.get(url);
    return response;
  }
    
}  
    //URLparser.parseURL("/login");


class MyApp extends StatelessWidget {
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