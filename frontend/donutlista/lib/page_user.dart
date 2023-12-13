import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

import 'navigation.dart';

// ignore: must_be_immutable
class UserPage extends StatefulWidget {
  final String userID;
  const UserPage({super.key, required this.userID});

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
              if (snapshot.hasError) {
                return const Text('error');
              } else {
                return Text(snapshot.data.toString());
              }
            }
            return const Text('loading...');
          },
          future: widget.getNameFromID(widget.userID),
        ),
      ),
      body: FutureBuilder(
        builder: (ctx, snapshot) {
          if (snapshot.connectionState == ConnectionState.done) {
            if (snapshot.hasError) {
              return const Center(child: Text('error'));
            } else {
              return Center(child: Text(snapshot.data.toString()));
            }
          } else {
            return const Center(child: Text('loading...'));
          }
        },
        future: widget.getNameFromID(widget.userID),
      ),
    );
  }
}
