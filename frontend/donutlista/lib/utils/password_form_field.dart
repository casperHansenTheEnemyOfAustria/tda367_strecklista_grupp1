import 'package:flutter/material.dart';

class PasswordFormField extends StatefulWidget {
  const PasswordFormField({Key? key}) : super(key: key);

  @override
  State<PasswordFormField> createState() => _PasswordFormFieldState();
  get textString => _PasswordFormFieldState()._text;
}

class _PasswordFormFieldState extends State<PasswordFormField> {
  final _formKey = GlobalKey<FormState>();
  var _text = 'TESTPASS';

  @override
  Widget build(BuildContext context) {
    return Form(
      key: _formKey,
      child: TextFormField(
        obscureText: true,
        decoration: const InputDecoration(
          border: OutlineInputBorder(),
          hintText: 'Enter your password',
        ),
        validator: (value) {
          if (value == null || value.isEmpty) {
            return 'Please enter your password';
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
