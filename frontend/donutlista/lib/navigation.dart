import 'package:donutlista/pageMain.dart';
import 'package:donutlista/pageInventory.dart';
import 'package:donutlista/pageTransaction.dart';
import 'package:donutlista/pageUser.dart';


import 'package:flutter/material.dart';

//Inspo: https://gallery.flutter.dev/#/demo/nav_drawer

/* Press the Navigation Drawer button to the left of AppBar 
to show a simple Drawer with two items. */

class NavDrawer extends StatelessWidget{
  const NavDrawer({super.key});

  @override
  Widget build(BuildContext context) {
    // var localization = adress.of(context)!; //TODO: Add PATH from database as variable
    final drawerHeader = UserAccountsDrawerHeader(
      accountName: Text('Username'), //localization.demoNavigationDrawerUserName,
      accountEmail: Text('Email'),   //localization.demoNavigationDrawerUserEmail,
      // TODO: Add saldo here
    );
    final drawerItems = ListView(
      children: [
        drawerHeader,
        ListTile(
          title: Text(
            //localization.NavigationDrawerToPageMain,
            'Strecklista'
          ),
          leading: const Icon(Icons.apps),
          onTap: () {
            Navigator.push<void>(
              context,
                MaterialPageRoute<void>(
                  builder: (BuildContext context) => MainPage(title: 'Strecklista'),
                ),
            );
          },
        ),
        ListTile(
          title: Text(
            //localization.NavigationDrawerToPageTransactions,
            'Transaktioner'
          ),
          leading: const Icon(Icons.credit_score),
          onTap: () {
            Navigator.push<void>(
              context,
                MaterialPageRoute<void>(
                  builder: (BuildContext context) => TransactionPage(),
                ),
            );
          },
        ),
        ListTile(
          title: Text(
            //localization.NavigationDrawerToPageInventory,
            'Inventarier'
          ),
          leading: const Icon(Icons.analytics),
          onTap: () {
            Navigator.push<void>(
              context,
                MaterialPageRoute<void>(
                  builder: (BuildContext context) => InventoryPage(),
                ),
            );
          },
        ),
        ListTile(
          title: Text(
            //localization.NavigationDrawerToPageUser,
            'Användare'
          ),
          leading: const Icon(Icons.person),
          onTap: () {
            Navigator.push<void>(
              context,
                MaterialPageRoute<void>(
                  builder: (BuildContext context) => UserPage(),
                ),
            );
          },
        ),

      ],
    );
    return drawerItems;
  }
}