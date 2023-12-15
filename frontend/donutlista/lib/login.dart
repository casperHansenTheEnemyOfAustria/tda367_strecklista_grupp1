import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'homescreen.dart';
import 'package:http/http.dart' as http;
import 'globals.dart';
import 'package:donutlista/globals.dart' as globals;




class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  LoginState createState() => LoginState();
}

class LoginState extends State<LoginPage> {

  var userName = '';
  var password = '';

  final loginSnackBar = const SnackBar(
    content: Text('Fel nickname eller lösenord'),
  );

  Future<void> sendLoginPostRequest() async {
    // Get the text from the forms
    var content = {"userName": userName, "password": password};
    final response = await http.post(
      Uri.http(apiUrl, '/login'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );

    if (response.statusCode == 200) {
      // ignore: use_build_context_synchronously
      Navigator.push(context,
          MaterialPageRoute(builder: (_) => HomeScreen(userID: response.body)));
      // ignore: avoid_print
      print(response.body); //TODO: Remove
      globals.sessionID = response.body;
    } else {
        // ignore: use_build_context_synchronously
        ScaffoldMessenger.of(context).showSnackBar(loginSnackBar);

    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: SingleChildScrollView(
        child: Column(
          children: <Widget>[
            Padding(
              padding: const EdgeInsets.only(top: 60.0),
              child: Center(
                // ignore: sized_box_for_whitespace
                child: Container( //Container is used instead of sizedBox to have the logo
                    width: 200,
                    height: 150,
                    /*decoration: BoxDecoration(
                        color: Colors.red,
                        borderRadius: BorderRadius.circular(50.0)),*/
                    child: Image.asset('assets/images/smurf.png')),
              ),
            ),
            Padding(
              //padding: const EdgeInsets.only(left:15.0,right: 15.0,top:0,bottom: 0),
              padding: const EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Nick',
                    hintText: 'Fyll i ditt nickname här'),
                onChanged: (text) {
                  userName = text;
                },
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 0),
              child: TextField(
                obscureText: true,
                decoration: const InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Lösenord',
                    hintText: 'Fyll i ditt löseord här'),
                onChanged: (text) {
                  password = text;
                },
              ),
            ),
            
            TextButton(
              onPressed: () {
              },
              child: Text(
                'Visste du att smurfar är tre äpplen höga?',
                style: TextStyle(color: HexColor('#09CDDA'), fontSize: 15),
              ),
            ),
            
            SizedBox(
              height: 50,
              width: 250,
              //decoration: BoxDecoration(
              //    color: Colors.blue, borderRadius: BorderRadius.circular(20)),
              child: TextButton(
                onPressed: () {
                  sendLoginPostRequest();
                },
                child: const Text(
                  'Logga in',
                  style: TextStyle(color: Colors.white, fontSize: 25),
                ),
              ),
            ),
            const SizedBox(
              height: 130,
            ),
            const Text('Ny användare? Kontakta admin')
          ],
        ),
      ),
    );
  }
}
