import 'package:flutter/material.dart';

/* Press the Navigation Drawer button to the left of AppBar 
to show a simple Drawer with two items. */

class NavDrawer extends StatelessWidget{
  const NavDrawer({super.key});

  @override
  Widget build(BuildContext context) {
    // var localization = //TODO: Add PATH from database as variable
    final drawerHeader = UserAccountsDrawerHeader(
      accountName: Text('Username'), 
      accountEmail: Text('Email'),
      currentAccountPicture: const CircleAvatar(
        child: Image.asset('assets/images/smurf.png')),
    )


  }
}