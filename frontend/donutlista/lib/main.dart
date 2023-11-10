import 'package:flutter/material.dart';
import 'package:hexcolor/hexcolor.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget{
  const MyApp({super.key});
  @override
  Widget build(BuildContext context){
    return const HomeScreen();
  }
}

class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      theme: ThemeData(
        colorScheme: const ColorScheme.highContrastDark().copyWith(
          secondary: HexColor("#09cdda")
        )
        
      ),
      title: 'StecklistIT',
      home: Scaffold(
        appBar: AppBar(
          backgroundColor: const Color.fromARGB(255, 63, 63, 63),
          actionsIconTheme: const IconThemeData(
          size: 30.0,
          color: Colors.white,
          opacity: 10.0
  ),
          title: Container(child: titleSection)

          ),
        ),
    );
  }
}

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
            Text( /* TODO: Write listener code here */
              'User',
              style: TextStyle(
                fontWeight: FontWeight.bold,
              ),
            ),
            Text( /* TODO: Write listener code here */
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


class MainItemGrid extends StatelessWidget{
  const MainItemGrid({super.key});
  @override
  Widget build(BuildContext context){
      return  GridView.count(
      // Create a grid with 2 columns. If you change the scrollDirection to
      // horizontal, this produces 2 rows.
      crossAxisCount: 2,
      // Generate 100 widgets that display their index in the List.
      children: List.generate(100, (index) {
        return Center(
          child: Text(
            'Item $index',
            style: Theme.of(context).textTheme.headlineSmall,
          ),
        );
      }),
    );

  }
}

// Menu bar setup 

const List<String> list = <String>['Strecklista','Inventarier','Transaktioner', 'Användare', 'Inställningar'];

class DropdownMenuMain extends StatefulWidget {
  const DropdownMenuMain({super.key});

  @override
  State<DropdownMenuMain> createState() => _DropdownMenuMainState();
}

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



