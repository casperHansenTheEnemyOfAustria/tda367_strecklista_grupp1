import 'dart:convert';

import 'package:donutlista/mainpageWidgets/bottom_nav.dart';
import 'package:donutlista/mainpageWidgets/grid.dart';
import 'package:flutter/material.dart';

import 'package:http/http.dart' as http;

import 'main.dart' show apiUrl;
import 'mainpageWidgets/summary_list.dart';
import 'navigation.dart';

class MainPage extends StatefulWidget {
  final String userID;
  final String title;
  const MainPage({super.key, required this.title, required this.userID});

  @override
  State<MainPage> createState() => _MainPage();
}

class _MainPage extends State<MainPage> {
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        drawer: NavDrawer(userID: widget.userID),
        appBar: AppBar(
          title: Text(widget.title),
        ),
        body: const Column(children: [
          SizedBox(height: 400.0, child: MainItemGrid()),
          SizedBox(
            height: 150.0,
            child: SummaryList(),
          )
        ]),
        bottomNavigationBar: const PurchaseButtons());
  }

  Future<Map<String, String>> getProducts(String userID) async {
    var content = {"sessionID": userID};
    final response = await http.post(
      Uri.http(apiUrl, '/getProducts'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );

    return jsonDecode(response.body);
  }
}
