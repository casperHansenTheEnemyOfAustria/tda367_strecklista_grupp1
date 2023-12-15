// ignore: file_names

import 'package:donutlista/transaction_list.dart';
import 'package:flutter/material.dart';

import 'navigation.dart';

class TransactionPage extends StatefulWidget {
  final String userID;
  const TransactionPage({Key? key, required this.userID}) : super(key: key);

  @override
  State<TransactionPage> createState() => _TransactionState();
}

class _TransactionState extends State<TransactionPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
        drawer: NavDrawer(
          userID: widget.userID,
        ),
        appBar: AppBar(
          title: const Text("Transactions"),
        ),
        body: const TransactionList());
  }
}
