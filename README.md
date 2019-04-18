# ShellSort Investigations
Evaluates several increment sequences to be used in ShellSort. From the sequences required, the time required to sort the data set will be averaged over some number of runs. The number of runs will be a variable that a user can enter from the command line when starting the program.

# Problem Statement
Write a driver to compare performances of increment sequences for Shellsort. Use the following increments:

The following three input sequences are to be used to perform the Shellsort comparisons. The increments you use will start with a larger increment, with the subsequent increments getting smaller. For example, although sequence 2 is shown as progressing in the sequence 1,8,23,77,281, etc., the first increment you choose will be based on the number of elements in your data, N. If N = 300 items, start your increment at the first value below N/2, or here, since N/2 = 150, the sequence of values you should use are the values 77,23,8,1.

	1. h[]= 2^i: {1,2,4,8,16, . . .}
	2. h[]= 4^i + 3 · 2^(i − 1) + 1: {1,8,23,77,281, . . .}
	3. Successive terms of the form 2^p · 3^q: {1,2,3,4,6,8,9,12, . . .}
	4. Successive terms of the form 2^i − 1: {1,3,7,15,31,63, . . .}

The program takes its input from the command line:
• arg[0] = the name of the file containing the data
• arg[1] = the number of trials to be performed with each sequence (so that an average time can be determined)

The trials are then performed and the average run times from each sequence along with the ratios (see below) are output to the console window. Program reads in files of various random lengths and determines the time to complete the sort
routine for each sequence.
