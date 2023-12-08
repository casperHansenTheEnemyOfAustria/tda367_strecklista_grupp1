import 'package:flutter/material.dart';

import 'navigation.dart';
class UserPage extends StatefulWidget {
 @override
 State<UserPage> createState() => _UserState();
}
class _UserState extends State<UserPage> {
 @override
 Widget build(BuildContext context) {
 return Scaffold(
 drawer: NavDrawer(),
 appBar: AppBar(
 title: Text("User"),
 ),
 body: Center(child: Text("User")),
 );
 }
}