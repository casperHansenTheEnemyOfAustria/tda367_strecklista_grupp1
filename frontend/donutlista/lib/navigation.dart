import 'package:donutlista/page_main.dart';
import 'package:donutlista/page_inventory.dart';
import 'package:donutlista/page_transaction.dart';
import 'package:donutlista/page_user.dart';

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

/* Press the Navigation Drawer button to the left of AppBar 
to show a simple Drawer with two items. */

class NavDrawer extends StatelessWidget {
  final String userID;
  const NavDrawer({super.key, required this.userID});

  @override
  Widget build(BuildContext context) {
    const drawerHeader = UserAccountsDrawerHeader( //Change drawerhead to Nick + dropdown to change active in kommitté
      accountName: Text('Username'), //TODO: add userName
      accountEmail: Text('Email'), //TODO: add email
      // TODO: Add saldo here
    );
    return SizedBox(
      width: MediaQuery.of(context).size.width * 0.7,
      child: Drawer(
        child: Container(
          color: HexColor('#000000'),
          child: ListView(
            padding: const EdgeInsets.all(8),
            children: <Widget>[
              drawerHeader,
              ListTile(
                title: const Text('Strecklista'), //TODO: add path
                leading: const Icon(Icons.apps),
                onTap: () {
                  Navigator.push<void>(
                    context,
                    MaterialPageRoute<void>(
                      builder: (BuildContext context) =>
                          MainPage(title: 'Strecklista', userID: userID),
                    ),
                  );
                },
              ),
              ListTile(
                title: const Text('Transaktioner'), //TODO: Add path
                leading: const Icon(Icons.credit_score),
                onTap: () {
                  Navigator.push<void>(
                    context,
                    MaterialPageRoute<void>(
                      builder: (BuildContext context) =>
                          TransactionPage(userID: userID),
                    ),
                  );
                },
              ),
              ListTile(
                title: const Text('Inventarier'), //TODO: Add path
                leading: const Icon(Icons.analytics),
                onTap: () {
                  Navigator.push<void>(
                    context,
                    MaterialPageRoute<void>(
                      builder: (BuildContext context) =>
                          InventoryPage(userID: userID),
                    ),
                  );
                },
              ),
              ListTile(
                title: const Text('Användare'), //TODO: Add path
                leading: const Icon(Icons.person),
                onTap: () {
                  Navigator.push<void>(
                    context,
                    MaterialPageRoute<void>(
                      builder: (BuildContext context) => UserPage(userID: userID),
                    ),
                  );
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}
