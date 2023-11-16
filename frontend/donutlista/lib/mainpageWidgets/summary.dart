import 'package:flutter/material.dart';

/* Summary list */

class SummaryList extends StatelessWidget {
  final List<String> itemList = ['Item 1','Item 2','Item 3',
  ];

  @override
  Widget build(BuildContext context) {
    return Container(
      child: ListView.builder(
        itemCount: itemList.length,
        itemBuilder: (context, index) {
          return ListTile(
            title: Text(itemList[index]),
          );
        },
      ),
    );
  }
}
