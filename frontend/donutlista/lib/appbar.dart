import 'package:flutter/material.dart';

/* Menu and information always present at the top of the app */

Widget titleSection = Container(
  padding: const EdgeInsets.all(32),
  child: const Row(
    children: [
      Expanded(
        /*1*/
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            /*2*/
            Text( /* TODO: Write listener code here, User should be changed according to database */
              'User',
              style: TextStyle(
                fontWeight: FontWeight.bold,
              ),
            ),
            Text( /* TODO: Write listener code here, saldo should be changed according to database */
              'Saldo: XX',
            )
          ],
        ),
      ),
      /*3*/
      DropdownMenuMain()
    ],
  ),
);

// Menu bar setup 

const List<String> list = <String>['Strecklista','Inventarier','Transaktioner', 'Användare', 'Inställningar']; // TODO: Make dynamic according to database? 

class DropdownMenuMain extends StatefulWidget {
  const DropdownMenuMain({super.key});

  @override
  State<DropdownMenuMain> createState() => _DropdownMenuMainState();
}


/* Dropdown menu to showcase different pages on the site */

class _DropdownMenuMainState extends State<DropdownMenuMain> {
  String dropdownValue = list.first;

  @override
  Widget build(BuildContext context) {
    return DropdownMenu<String>(
      initialSelection: list.first,
      onSelected: (String? value) {
        // This is called when the user selects an item.
        setState(() {
          dropdownValue = value!;
        });
      },
      dropdownMenuEntries: list.map<DropdownMenuEntry<String>>((String value) {
        return DropdownMenuEntry<String>(value: value, label: value);
      }).toList(),
    );
  }
}


