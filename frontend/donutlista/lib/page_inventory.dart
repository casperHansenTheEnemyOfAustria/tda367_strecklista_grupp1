
import 'package:flutter/material.dart';

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
        title: const Text("Inventory"),
      ),
      body: const Center(child: Text("Inventory info")),
    );
  }
}
