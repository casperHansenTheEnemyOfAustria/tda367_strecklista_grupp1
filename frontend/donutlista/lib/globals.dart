library donutlista.globals;

import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

const apiUrl = "localhost:8080";

String sessionID = "";

final summaryOfThingsInCart = ValueNotifier<List<Map<String, String>>>(List.filled(1, {}));
final transactions = ValueNotifier<Future<List<Map<String, String>>>>(getTransactions());

Future<Map<String, String>> sendGetCartRequest() async {
    var content = {
      "sessionID": sessionID,
    };
    final response = await http.post(
      Uri.http(apiUrl, '/getCart'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    if(response.statusCode == 200){
      var preMap = jsonDecode(response.body);
      Map<String, String> map = {};
      preMap.forEach((key, value) { 
        map[key] = value;
      });
      return map;
    }else{
        return {"error": "error"};
    }
  }

Future<Map<String, String>> sendGetProductRequest(String productId) async {
    var content = {
      "sessionID": sessionID,
      "data": {
        "productID": productId,
      }
    };
    final response = await http.post(
      Uri.http(apiUrl, '/getProduct'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    if(response.statusCode == 200){
      var preMap = jsonDecode(response.body);
      Map<String, String> map = {};
      preMap.forEach((key, value) { 
        map[key] = value;
      });
      return map;
    }else{
        return {"error": "error"};
    }
  }

  Future<List<Map<String,String>>> getTransactions() async{
    var content = {
      "sessionID": sessionID,
    };
    final response = await http.post(
      Uri.http(apiUrl, '/getOrderHistory'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    if(response.statusCode == 200){
      var preMap = jsonDecode(response.body);
      List<Map<String, String>> map = [{}];
      preMap.forEach((element) {
        Map<String, String> tempMap = {};
        element.forEach((key, value) {
          tempMap[key] = value;
        });
        map.add(tempMap);
      });
      return map;
    }else{
        return [{"error": "error"}];
    }
  }
  updateTransactionList(){
    transactions.value = getTransactions();
  }

 Future<String> getNameFromID(String userID) async {
    // Get the text from the forms
    var content = {"sessionID": userID};
    const apiUrl = "localhost:8080";
    final response = await http.post(
      Uri.http(apiUrl, '/getName'),
      headers: <String, String>{
        'Content-Type': 'application/json; charset=UTF-8',
      },
      body: jsonEncode(content),
    );
    if (response.statusCode == 200) {
      return response.body;
    } else {
      return 'error';
    }
  }

  getUserGroup() async {
  var content = {
    "sessionID": sessionID,
  };
  final response = await http.post(
    Uri.http(apiUrl, '/getUserGroup'),
    headers: <String, String>{
      'Content-Type': 'application/json; charset=UTF-8',
    },
    body: jsonEncode(content),
  );
  return response.statusCode == 200;
}
