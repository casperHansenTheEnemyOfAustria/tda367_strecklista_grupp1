import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'navigation.dart';

// ignore: must_be_immutable
class UserPage extends StatefulWidget {
  final String userID;
  UserPage({super.key, required this.userID});

  var name = 'loading name...';
  @override
  State<UserPage> createState() => _UserState();
}

class _UserState extends State<UserPage> {
  var name = 'loading name...';
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(
        userID: widget.userID,
      ),
      appBar: AppBar(
        title: Text(widget.name),
      ),
      body: Center(child: Text(widget.name)),
    );
  }

  // THIS SHIT DON'T FUCKING WORK
  Future<void> getNameFromID(String userID) async {
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
      setState(() {
        widget.name = response.body;
      });
    } else {
      // ignore: avoid_print
      print("Failed to get name"); //TODO: Remove
    }
  }
}
