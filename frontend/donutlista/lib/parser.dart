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

/* //https://docs.flutter.dev/ui/navigation
@override
  Widget build(BuildContext context) {
    // set json file directory
    // default value is ['lib/i18n']
    LocalJsonLocalization.delegate.directories = ['lib/i18n'];
    
    //or set a map of translations
    MapLocalization.delegate.translations = {
      Locale('en', 'US'): {
        'welcome-text': 'This text is in english',
      },
      Locale('es', 'ES'): {
        'welcome-text': 'Este texto esta en español',
      },
      Locale('pt', 'BR'): {
        'welcome-text': 'Este texto está em português',
      },
    };


    return MaterialApp(
      localizationsDelegates: [
        // delegate from flutter_localization
        GlobalMaterialLocalizations.delegate,
        GlobalWidgetsLocalizations.delegate,
        GlobalCupertinoLocalizations.delegate,
        
        // delegate from localization package.
        //json-file
        LocalJsonLocalization.delegate,
        //or map
        MapLocalization.delegate,
      ],
      home: HomePage(),
    );
  }
  */