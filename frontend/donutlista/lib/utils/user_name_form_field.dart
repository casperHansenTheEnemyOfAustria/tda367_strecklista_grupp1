import 'package:flutter/material.dart';

class UserNameFormField extends StatefulWidget {
  const UserNameFormField({Key? key}) : super(key: key);

  @override
  State<UserNameFormField> createState() => _UserNameFormFieldState();
  get textString => _UserNameFormFieldState()._text;
}

class _UserNameFormFieldState extends State<UserNameFormField> {
  final _formKey = GlobalKey<FormState>();
  var _text = 'TEST';

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: TextFormField(
        decoration: const InputDecoration(
          border: OutlineInputBorder(),
          hintText: 'Enter your username',
        ),
        validator: (value) {
          if (value == null || value.isEmpty) {
            return 'Please enter your username';
          }
          return null;
        },
        onChanged: (text) {
          _text = text;
        },
      ),
    );
  }
}
