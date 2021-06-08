# knapsack-final-cs215 by Revaz Bakuradze, Aleksandr Molchagin, and Duurenbayar Ulziiduuren

File included in the project: 
    The project submission includes write-up, analyzing the algorithms, input_data folder, containing test cases for the program, and com.finalProject package, containing the classes and the code to run these three algorithms.

How to run the code: 
    To run the code on the desired input file, change the file variable declaration and pass the address or the name of the file. If the file is inside the input_data file, passing only the name will make the code work, otherwise, specify the file location in the machine. 

How the code works: 
    the main method uses a scanner class to read the input from the file. The number of items and the weight limit are stored in the variables. These variables are passed to these three algorithms as parameters later on. Afterward, the loop which reads input and stores values is run the number of items times, reading input about items, and storing them in an item array.

After the program is run, the main method calls the three algorithms to output the optimal solution. Furthermore, there is a static boolean debugging variable abd a static boolean timer variable for testing purposes. 

Code bugs:
    1) Dynamic programming algorithm doesn't print the items correctly.
    2) Best-First branch and bound doesn't print the items correctly all the time. For example, for the instance t7.dat it prints 1 2 9 9 9... instead of 1 2 3 4 5... This issue disappears if we delete the line 241 in the main class (u = new Node(0,0,0);), but then other instances may stop working properly.

