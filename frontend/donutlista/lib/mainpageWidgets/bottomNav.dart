import 'package:flutter/material.dart';

/* Navigation to purchase or delete order */

class PurchaseButtons extends StatelessWidget {
  const PurchaseButtons({super.key, 
  });
  
  @override
  Widget build(BuildContext context) {
    
    
    return FractionallySizedBox(
      widthFactor: 1.0,
      heightFactor: 0.07,
      
      child: Row(
        children: [
          Expanded(
            child: TextButton(
              
              style: ButtonStyle(
                backgroundColor: MaterialStatePropertyAll<Color>(const Color.fromARGB(255, 183, 110, 0)),
                foregroundColor: MaterialStateProperty.all<Color>(Colors.white)
                
                ),
                onPressed: () { },
              child: Text('Cancel'),
            ),
          ), 
          
          Expanded(
            child:TextButton(
              style: ButtonStyle(
                backgroundColor: MaterialStatePropertyAll<Color>(Colors.green),
                foregroundColor: MaterialStateProperty.all<Color>(Colors.white),
              ),
              onPressed: () { },
              child: Text('Buy'),
            )
          )
        ],

      )
      
        );
        
  }
}
