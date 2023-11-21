import 'package:http/http.dart' as http;


/* Class for listeners to connect with database */

String BaseURI = 'http://localhost:8080';


class URLparser{
  static parseURL(String path) async { 
    var url = Uri.parse(BaseURI+path);
    var response = await http.get(url);
    return response;
  }
    
}  
    //URLparser.parseURL("/login");
