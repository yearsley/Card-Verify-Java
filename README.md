Credit Card Checker (Java)

This project is a Java console application that validates credit card numbers using the Luhn checksum algorithm and identifies the card issuer based on standard number prefixes and length checks. It reads card data from a user-provided .txt file and outputs a formatted table indicating each card’s issuer and validity status.

Features

Reads a list of credit card numbers from an input text file

Detects card issuer based on industry-standard prefix rules:

VISA

MasterCard

American Express

Discover

(Others are marked as unrecognized)

Implements the Luhn algorithm to validate card numbers

Prints aligned, human-readable output showing:

Card issuer

Card number

Validity result (valid or invalid checksum)

Technologies Used

Java

Scanner for file and console input

Luhn checksum implementation

Console formatting using System.out.format

How to Run

Ensure you have Java installed.

Compile the program:

javac Main.java


Run the program:

java Main


Enter the filename containing card numbers when prompted.
Example file format:

4556737586899855
6011111111111117
378282246310005

Example Output
Issuer          Card Number         Result
-----------------------------------------------
VISA            4556737586899855    valid
DISCOVER        6011111111111117    valid
AMEX            378282246310005     valid
UNKNOWN         1234567890123456    invalid checksum
