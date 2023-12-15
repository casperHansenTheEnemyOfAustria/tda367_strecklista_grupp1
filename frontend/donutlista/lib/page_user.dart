import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:donutlista/globals.dart' as globals;

import 'navigation.dart';

// ignore: must_be_immutable
class UserPage extends StatefulWidget {
  final String userID;
  const UserPage({super.key, required this.userID});

  @override
  State<UserPage> createState() => _UserState();
  Future<String> getNameFromID(String userID) async {
    // Get the text from the forms
    return globals.getNameFromID(userID);
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
