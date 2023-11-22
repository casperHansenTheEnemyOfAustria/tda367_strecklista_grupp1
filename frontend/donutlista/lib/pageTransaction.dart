//TODO: Remove unused imports
// ignore_for_file: unused_import

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

import 'navigation.dart';
class TransactionPage extends StatefulWidget {
 @override
 State<TransactionPage> createState() => _TransactionState();
}
class _TransactionState extends State<TransactionPage> {
 @override
 Widget build(BuildContext context) {
 return Scaffold(
 drawer: NavDrawer(),
 appBar: AppBar(
 title: Text("Transactions"),
 ),
 body: Center(child: Text("Transactions")),
 );
 }
}