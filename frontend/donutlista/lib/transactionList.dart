import 'package:flutter/material.dart';

class TransactionList extends StatelessWidget {
  const TransactionList({super.key,});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Scrollbar(
        child: ListView(
          restorationId: 'transaction_view',
          padding: const EdgeInsets.symmetric(vertical: 8),
          children: [
            for (int index = 1; index < 21; index++)
              ListTile(
                leading: ExcludeSemantics(
                  child: CircleAvatar(child: Text('$index')),
                ),
                title: 
                const //Remove later
                Text(
                  'då'
                ),
                subtitle: 
                const //Remove later
                Text('wawawa'),
              ),
          ],
        ),
      ),
    );
  }
}

