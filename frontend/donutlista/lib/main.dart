import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget{
  const MyApp({super.key});
  @override
  Widget build(BuildContext context){
    return const HomeScreen();
  }
}

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
class HomeScreen extends StatelessWidget {
  const HomeScreen({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Menubar',
      home: Scaffold(
        appBar: AppBar(title: const Text('Flutter layout demo')),
        body: const DropdownMenuMain()
      ),
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



