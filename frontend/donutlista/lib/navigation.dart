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
      //currentAccountPicture: const CircleAvatar(
        //child: Image.asset('assets/images/smurf.png')),
    );
    final drawerItems = ListView(
      children: [
        drawerHeader,
        ListTile(
          title: Text(
            //localization.demoNavigationDrawerToPageOne,
            'Strecklista'
          ),
          leading: const Icon(Icons.apps),
          onTap: () {
            Navigator.pop(context);
          },
        ),
        ListTile(
          title: Text(
            //localization.demoNavigationDrawerToPageTwo,
            'Transaktioner'
          ),
          leading: const Icon(Icons.credit_score),
          onTap: () {
            Navigator.pop(context);
          },
        ),
        ListTile(
          title: Text(
            //localization.demoNavigationDrawerToPageTwo,
            'Inventarier'
          ),
          leading: const Icon(Icons.analytics),
          onTap: () {
            Navigator.pop(context);
          },
        ),
        ListTile(
          title: Text(
            //localization.demoNavigationDrawerToPageTwo,
            'Användare'
          ),
          leading: const Icon(Icons.person),
          onTap: () {
            Navigator.pop(context);
          },
        ),

      ],
    );
    return drawerItems;
  }
}