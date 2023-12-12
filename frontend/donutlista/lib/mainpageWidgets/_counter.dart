import 'package:flutter/material.dart';

class CounterWidget extends StatefulWidget {
  CounterWidget({Key? key, required this.child}) : super(key: key);

  final Widget child;

  @override
  _CounterState createState() => _CounterState();
}

class _CounterState extends State<CounterWidget> {
  late int count;

  void incrementCount() {
    setState(() {
      ++count;
    });
  }

   void decrementCount() {
    setState(() {
      if (count == 0) {
        count = 0;
      } else {
        count--;
      } 
    });
   }

  @override
  void initState() {
    super.initState();
    count = 0;
  }

  @override
  Widget build(BuildContext context) {
    return _InheritedCount(
      state: this,
      child: widget.child,
    );
  }
}

class _InheritedCount extends InheritedWidget {
  _InheritedCount({Key? key, required this.state, required Widget child})
      : super(key: key, child: child);

  final _CounterState state;

  @override
  bool updateShouldNotify(_InheritedCount old) => true;
}