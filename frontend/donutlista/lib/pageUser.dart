import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'navigation.dart';

class UserPage extends StatefulWidget {
  final String userID;
  UserPage({super.key, required this.userID});

  var name = 'loading name...';
  @override
  State<UserPage> createState() => _UserState();
  Future<String> getNameFromID(String userID) async {
    // Get the text from the forms
    var content = {"sessionID": userID};
    const apiUrl = "localhost:8080";
    final response = await http.post(
      Uri.http(apiUrl, '/getName'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return 'error';
    }
  }
}

class _UserState extends State<UserPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(
        userID: widget.userID,
      ),
      appBar: AppBar(
          title: FutureBuilder(
              builder: (ctx, snapshot) {
                if (snapshot.connectionState == ConnectionState.done) {
                  widget.name = snapshot.data.toString();
                  return Text(widget.name);
                } else {
                  return const Text('loading...');
                }
              },
              future: widget.getNameFromID(widget.userID))),
      body: Center(child: Text(widget.name)),
    );
  }
}
