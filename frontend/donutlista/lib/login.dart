import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';
import 'homescreen.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';



class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  @override
  LoginState createState() => LoginState();
}

class LoginState extends State<LoginPage> { 
  final apiUrl = "localhost:8080";

  TextEditingController usernameController = TextEditingController();
  TextEditingController passwordController = TextEditingController();

  final snackBar = SnackBar(
    content: Text('Failed to login!'),
);


 Future<void> sendPostRequest() async {
    var content = {
        'stateID': "något",
        'data': {
          'enGrej': 'en annan grej'
        }
      };
    final response = await http.post(
      
      Uri.http(apiUrl, '/login'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
      );
  
    if (response.statusCode == 200) {
      Navigator.push(context, MaterialPageRoute(builder: (_) => HomeScreen()));
    } else {
      snackBar;
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
                child: SizedBox(
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
              padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                controller: usernameController,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Email',
                    hintText: 'Enter valid email id as abc@gmail.com'),
              ),
            ),
            Padding(
              padding: EdgeInsets.only(
                  left: 15.0, right: 15.0, top: 15, bottom: 0),
              //padding: EdgeInsets.symmetric(horizontal: 15),
              child: TextField(
                controller: passwordController,
                obscureText: true,
                decoration: InputDecoration(
                    border: OutlineInputBorder(),
                    labelText: 'Password',
                    hintText: 'Enter secure password'),
              ),
            ),
            TextButton(
              onPressed: (){
                //TODO FORGOT PASSWORD SCREEN GOES HERE
              },
              child: Text(
                'Forgot Password',
                style: TextStyle(color: HexColor('#09CDDA'), fontSize: 15),
              ),
            ),
            SizedBox(
              height: 50,
              width: 250,
              //decoration: BoxDecoration(
              //    color: Colors.blue, borderRadius: BorderRadius.circular(20)),
              child: TextButton(
                onPressed: sendPostRequest,
                child: Text(
                  'Login',
                  style: TextStyle(color: Colors.white, fontSize: 25),
                ),
              ),
            ),
            const SizedBox(
              height: 130,
            ),
            const Text('New User? Create Account')
          ],
        ),
      ),
    );
  }
}