import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'package:donutlista/globals.dart' as globals;

class TransactionList extends StatelessWidget {
  const TransactionList({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Scrollbar(
        child: ValueListenableBuilder(
          valueListenable: globals.transactions,
          builder: (context, value, child) {
            return FutureBuilder<List<Map<String, String>>>(
              future: value,
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.done) {
                  //   if (snapshot.hasError) {
                  //     return const Text('Error');
                  //   }
                  return ListView.builder(
                    itemCount: snapshot.data!.length,
                    itemBuilder: (context, index) {
                      return ListTile(
                        title: Text("Order: $index"),
                        subtitle:
                            Text(snapshot.data![index].entries.toString()),
                      );
                    },
                  );
                } else {
                  return const Center(
                    child: CircularProgressIndicator(),
                  );
                }
              },
            );
          },
        ),
      ),
    );
  }
}
