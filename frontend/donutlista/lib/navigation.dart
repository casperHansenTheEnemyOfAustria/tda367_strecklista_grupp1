import 'package:donutlista/pageMain.dart';
import 'package:donutlista/pageInventory.dart';
import 'package:donutlista/pageTransaction.dart';
import 'package:donutlista/pageUser.dart';

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

//Inspo: https://gallery.flutter.dev/#/demo/nav_drawer

/* Press the Navigation Drawer button to the left of AppBar 
to show a simple Drawer with two items. */

class NavDrawer extends StatelessWidget {
  final String userID;
  const NavDrawer({super.key, required this.userID});

  @override
  Widget build(BuildContext context) {
    final drawerHeader = UserAccountsDrawerHeader(
      accountName: Text('Username'), //TODO: add username
      accountEmail: Text('Email'), //TODO: add email
      // TODO: Add saldo here
    );
    return Container(
      width: MediaQuery.of(context).size.width * 0.7,
      child: Drawer(
        child: Container(
          color: HexColor('#000000'),
          child: ListView(
            padding: const EdgeInsets.all(8),
            children: <Widget>[
              drawerHeader,
              ListTile(
                title: Text('Strecklista'), //TODO: add path
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
                title: Text('Transaktioner'), //TODO: Add path
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
                title: Text('Inventarier'), //TODO: Add path
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
                title: Text('Användare'), //TODO: Add path
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
