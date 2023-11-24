import 'package:flutter/material.dart';

/* Summary list of items in bag 
https://gallery.flutter.dev/#/demo/lists */

class SummaryList extends StatelessWidget {
  const SummaryList({super.key, 
  //required this.type
  });

  //final ListType type;

  @override
  Widget build(BuildContext context) {
    
    
    return FractionallySizedBox(
      widthFactor: 1.0,
      heightFactor: 0.35,
      
      child:
      ListView(
          restorationId: 'list_view',
          padding: const EdgeInsets.symmetric(vertical: 8),
          children: [
            for (int index = 1; index < 10; index++)
              ListTile(
                leading: ExcludeSemantics(
                ),
                title: Text('Insert item here'), //TODO: add path from itemCard name here
                subtitle: 
                //type == ListDemoType.twoLine ? 
                Text('insert price here') //TODO: add path from ItemCard * Price here
                //: null,
              ,)
          ],
              ),
          //TODO: Add incart summary
        );
        
  }
}

