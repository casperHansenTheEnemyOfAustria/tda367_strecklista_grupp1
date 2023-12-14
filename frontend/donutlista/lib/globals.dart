library donutlista.globals;

import 'dart:convert';
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

const apiUrl = "localhost:8080";

String sessionID = "";

final summaryOfThingsInCart = ValueNotifier<List<Map<String, String>>>(List.filled(1, {}));

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