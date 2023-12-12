//TODO: Remove unused imports
// ignore: unused_import
import 'package:flutter/material.dart';
// ignore: unused_import
import 'package:hexcolor/hexcolor.dart';

import 'navigation.dart';

class InventoryPage extends StatefulWidget {
  final String userID;
  const InventoryPage({super.key, required this.userID});

  @override
  State<InventoryPage> createState() => _InventoryState();
}

class _InventoryState extends State<InventoryPage> {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      drawer: NavDrawer(
        userID: widget.userID,
      ),
      appBar: AppBar(
        title: Text("Inventory"),
      ),
      body: Center(child: Text("Inventory info")),
    );
  }
}
