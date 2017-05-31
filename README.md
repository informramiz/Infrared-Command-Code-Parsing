# Infrared-Command-Code-Parsing
Given an infrared string code representing infrared commands, parse it to object and back.

Below is an example of code format for representing infrared commands.

````
String code = "UIR.FREQ.38000.REPEAT.2.DELAY.1000.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161";
````

The code requires the following parts:

- Must start with the `UIR` prefix
- Must include a frequency: `FREQ.<hz>`
- Must contain at least 1 sequence: `SEQ.<ms>.<ms>...`

The code can also include the following parts:

- An optional repeat count `REPEAT.<count>`
- An optional delay `DELAY.<ms>`

All of the parts (except for the prefix) can appear in any order.

This repository provides an Encoder that can:

1) Parse the code string into an object representation: `(String) -> (obj)`

2) Stringify the object representation `(obj) -> (String)`

## Getting Started Guide

```
String code1 = "UIR.FREQ.38000.REPEAT.2.DELAY.1000.SEQ.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161.SEQ.20.20.20.165.20.205.40.40.40.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.165.20.205.20.20.20.5161";

Command command1 = Encoder.decode(code1);
System.out.println("Code-1 decoded: " + command1);

String command1Encoded = Encoder.encode(command1);
System.out.println("Command1 Encoded: " + command1Encoded);

//let's test it
System.out.println(code1.equals(command1Encoded) == true);

```

## Class Details

- **Command.java:** A plain Old Java Object (POJO) to hold command data in object form.
- **Encoder.java:** Class to Encode and Decoded infrared commands code.
- **MyUtils.java:** A simple utils class of my own. Although there are good libraries available to do the same but as my requirement was very simple so I did not use any.
- **Main.java:** Class containing `main` function and code I used to test this class.
