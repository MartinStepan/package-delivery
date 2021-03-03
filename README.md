How to run application: 

Run command "java -jar <path to jar>.jar <arg0> <arg1>"

For example: java -jar package-delivery.jar packages.txt fees.txt

arg0 - optional argument, always filename of file containing lines in same format as user can enter in command line
arg1 - optional argument, always filename of file containing information about fees related to package weight

If only one argument is specified, then is always filename of file containing lines in same format as user can enter in command line!
For exit program write "quit".

Input line format:
<weight: positive number, >0, maximal 3 decimal places, . (dot) as decimal separator><space><postal code: fixed 5 digits> 
