import 'dart:js_interop';

import 'package:donutlista/page_main.dart';
import 'package:donutlista/page_inventory.dart';
import 'package:donutlista/page_transaction.dart';
import 'package:donutlista/page_user.dart';
import 'package:donutlista/globals.dart' as globals;

import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

/* Press the Navigation Drawer button to the left of AppBar 
to show a simple Drawer with two items. */

class NavDrawer extends StatelessWidget {
  final String userID;
  const NavDrawer({super.key, required this.userID});

  @override
  Widget build(BuildContext context) {
    
    return SizedBox(
      width: MediaQuery.of(context).size.width * 0.7,
      child: Drawer(
        child: Container(
          color: HexColor('#000000'),
          child: ListView(
            padding: const EdgeInsets.all(8),
            children: <Widget>[
              const DrawerHeader(child: DropDown(), 

              ),
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
              // ListTile(
              //   title: const Text('Inventarier'), //TODO: Add path
              //   leading: const Icon(Icons.analytics),
              //   onTap: () {
              //     Navigator.push<void>(
              //       context,
              //       MaterialPageRoute<void>(
              //         builder: (BuildContext context) =>
              //             InventoryPage(userID: userID),
              //       ),
              //     );
              //   },
              // ),
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

class DropDown extends StatefulWidget {
  const DropDown({super.key, 
  });

  @override
  State<DropDown> createState() => _DropDownState();
}

class _DropDownState extends State<DropDown> {
  var groupName = "";


  String? selectedValue = 'Humlan'; //TODO: Change to userID
  final _dropdownFormKey = GlobalKey<FormState>();
  _DropDownState(){
      globals.getUserGroup().then((value) {setState((){groupName = value.toString();});});

  }
  @override
  Widget build(BuildContext context) {
    globals.getUserGroup().then((value) {setState((){groupName = value.toString();});});

    return Form(
        key: _dropdownFormKey,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children:[ 
            DropdownButtonFormField(
                decoration: InputDecoration(
                  enabledBorder: OutlineInputBorder(
                    borderSide: const BorderSide(color: Colors.blue, width: 2),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  border: OutlineInputBorder(
                    borderSide: const BorderSide(color: Colors.blue, width: 2),
                    borderRadius: BorderRadius.circular(20),
                  ),
                  //filled: true,
                  //fillColor: Colors.blueAccent,
                ),
                validator: (value) => value == null ? "Select a country" : null,
                //dropdownColor: Colors.blueAccent,
                value: selectedValue,
                onChanged: (newValue) {
                  setState(() {
                    selectedValue = newValue!;
                  });
                },
                items: (dropdownItems),
            ),

            Text(
              
              groupName,
            ),

            ]
        )
      );
  }

  List<DropdownMenuItem<dynamic>> get dropdownItems{

  List<DropdownMenuItem<dynamic>> menuItems = [
    DropdownMenuItem(value: "Humlan",child: Text("Humlan"),)
    
  ];
  return menuItems;
}


}