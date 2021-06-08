# knapsack-final-cs215 by Revaz Bakuradze and Aleksandr Molchagin

File included in the project: The project submission includes write-up, analyzing the algorithms, input_data folder, containing test cases for the program, and com.finalProject package, containing the classes and the code to run these three algorithms.

How to run the code: To run the code on the desired input file, change the file variable declaration and pass the address or the name of the file. If the file is inside the input_data file, passing only the name will make the code work, otherwise, specify the file location in the machine. 

How the code works: the main method uses a scanner class to read the input from the file. The number of items and the weight limit are stored in the variables. These variables are passed to these three algorithms as parameters later on. Afterward, the loop which reads input and stores values is run the number of items times, reading input about items, and storing them in an item array.

After the program is run,  the main method calls the three algorithms to output the optimal solution. Furthermore, there is a static boolean debugging variable for testing purposes. 