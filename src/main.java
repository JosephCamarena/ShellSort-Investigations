// Joseph Camarena
// 0588321
// jc0588321@swccd.edu

import java.io.FileNotFoundException;
import java.util.Collections;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;

public class main {

	public static void main(String[] args) throws IOException {

		argCheck(args.length);
		String fileNameRead = args[0];
		Scanner inFile = inFileCheck(fileNameRead);
		int numberOfTrials = getNumTrials(args[1]);

		int dataSetSize= Files.readAllLines(Paths.get(fileNameRead)).size();
		Double[] data = new Double[ dataSetSize ];
		data = populateArray(inFile, data);
		inFile.close();
		
		printHeader(fileNameRead, numberOfTrials);

		int upperVal = dataSetSize / 2;
		int[] h1 = populateH1(upperVal);
		int[] h2 = populateH2(upperVal);
		int[] h3 = populateH3(upperVal);
		int[] h4 = populateH4(upperVal);

		// sort array data of n items with each sequence j trial, timing each:
		runTrials(data, h1, h2, h3, h4, numberOfTrials);

	}//end main

	public static void argCheck (int numArgs) {

		if (numArgs != 2) {
			System.err.println("Invalid # of arguments: " + numArgs
					+ "\nArguments required:     2"
					+ "\nExiting Program.");
			System.exit(0);  
		}

	}

	public static Scanner inFileCheck (String fileNameRead) {

		Scanner inFile = null;
		try {
			inFile = new Scanner(new File(fileNameRead));
		} catch (FileNotFoundException e) {
			System.err.println ("Error: File " + fileNameRead + " (No such file or directory)"
					+ "\nExiting Program.");
			System.exit(1);
		}
		return inFile;
	}

	public static int getNumTrials(String trials) {
		int numberOfTrials = 0;
		try {
			numberOfTrials = Integer.parseInt(trials);
		}
		catch (NumberFormatException nfe) {
			System.err.println("Error: The second argument must be an integer."
					+ "\nExiting Program.");
			System.exit(1);
		}
		return numberOfTrials;
	}

	public static Double[] populateArray (Scanner inFile, Double[] data) {

		int i = 0;
		while( inFile.hasNext() ) {
			data[i] = Double.parseDouble( inFile.next() );
			i++;
		}
		return data;
	}

	public static void printHeader(String fileRead, int numberOfTrials) {
		System.out.println( fileRead + ", avg from " + numberOfTrials + " runs follows:");
		System.out.println();
		System.out.println("Number of trials: " + numberOfTrials);
		System.out.println();
	}

	public static int[] populateH1 (int upperVal) {

		System.out.println("seq 1:");

		ArrayList<Integer> h1Raw = new ArrayList<Integer>();
		for (int i = 0; Math.pow(2,i) < upperVal; i++) {

			h1Raw.add( (int)Math.pow(2,i) );
		}
		Collections.sort(h1Raw, Collections.reverseOrder());

		System.out.print(" " + h1Raw);
		System.out.println();
		System.out.println();
		
		return h1Raw.stream().mapToInt(i->i).toArray();
	}

	public static int[] populateH2 (int upperVal) {

		System.out.println("seq 2:");

		ArrayList<Integer> h2Raw = new ArrayList<Integer>();
		for (int i = 0; (Math.pow(4,i) + 3 * Math.pow(2,(i-1) ) + 1) < upperVal; i++) {

			if(i==0) h2Raw.add(1);
			else {
				h2Raw.add( (int)Math.pow(4,i) + 3 * (int)Math.pow(2,(i-1) ) + 1 );
			}
		}
		Collections.sort(h2Raw, Collections.reverseOrder());

		System.out.print(" " + h2Raw);
		System.out.println();
		System.out.println();
		
		return h2Raw.stream().mapToInt(i->i).toArray();
	}

	public static int[] populateH3 (int upperVal) {

		System.out.println("seq 3:");
		ArrayList<Integer> h3Raw = new ArrayList<Integer>();
		for (int i = 0; (int)Math.pow(2,i) < upperVal; i++) {

			for (int j=0; ((int)Math.pow(2,i) * (int)Math.pow(3,j)) < upperVal; j++) {

				h3Raw.add ( (int)Math.pow(2,i) * (int)Math.pow(3,j) );
			}
		}
		Collections.sort(h3Raw, Collections.reverseOrder());

		System.out.print(" " + h3Raw);
		System.out.println();
		System.out.println();
		
		return h3Raw.stream().mapToInt(i->i).toArray();
	}

	public static int[] populateH4 (int upperVal) {

		System.out.println("seq 4:");

		ArrayList<Integer> h4Raw = new ArrayList<Integer>();
		for (int i = 1; Math.pow(2, i) - 1 < upperVal; i++) {

			h4Raw.add( (int)Math.pow(2,i) - 1 );
		}
		Collections.sort(h4Raw, Collections.reverseOrder());

		System.out.print(" " + h4Raw);
		System.out.println();
		System.out.println();
		
		return h4Raw.stream().mapToInt(i->i).toArray();
	}

	public static void runTrials(Double[] data, int[] h1, int[] h2, int[] h3, int[] h4, int numberOfTrials) {

		ShellSort study1 = new ShellSort( data );
		ShellSort study2 = new ShellSort( data );
		ShellSort study3 = new ShellSort( data );
		ShellSort study4 = new ShellSort( data );
		long start = 0L, duration1 = 0L, duration2 = 0L, duration3 = 0L, duration4 = 0L;
		long comparisons1 = 0L, comparisons2 = 0L, comparisons3 = 0L, comparisons4 = 0L;
		int j = 0;
		long trialStart = System.nanoTime();
		while( j < numberOfTrials) {

			start = System.nanoTime();
			study1.sortUsing( h1 );
			duration1 += (System.nanoTime() - start);
			comparisons1 += study1.getComparisons();
			study1.resetComparisons();

			start = System.nanoTime();
			study2.sortUsing( h2 );
			duration2 += (System.nanoTime() - start);
			comparisons2 += study2.getComparisons();
			study2.resetComparisons();

			start = System.nanoTime();
			study3.sortUsing( h3 );
			duration3 += (System.nanoTime() - start);
			comparisons3 += study3.getComparisons();
			study3.resetComparisons();

			start = System.nanoTime();
			study4.sortUsing( h4 );
			duration4 += (System.nanoTime() - start);
			comparisons4 += study4.getComparisons();

			study1 = new ShellSort( data );
			study2 = new ShellSort( data );
			study3 = new ShellSort( data );
			study4 = new ShellSort( data );
			j++;
		}
		double trialDuration = (System.nanoTime() - trialStart) / 1_000_000.0;

		//display results as an average
		long avgCompareSeq1 = comparisons1 / numberOfTrials;
		long avgCompareSeq2 = comparisons2 / numberOfTrials;
		long avgCompareSeq3 = comparisons3 / numberOfTrials;
		long avgCompareSeq4 = comparisons4 / numberOfTrials;

		double avgTimeSeq1 = (duration1 / numberOfTrials) / 1_000_000.0;
		double avgTimeSeq2 = (duration2 / numberOfTrials) / 1_000_000.0;
		double avgTimeSeq3 = (duration3 / numberOfTrials) / 1_000_000.0;
		double avgTimeSeq4 = (duration4 / numberOfTrials) / 1_000_000.0;

		System.out.println("Comparisons:");
		System.out.println();
		System.out.println("Average comparisons for Sequence 1 is: " + avgCompareSeq1);
		System.out.println("Average comparisons for Sequence 2 is: " + avgCompareSeq2);
		System.out.println("Average comparisons for Sequence 3 is: " + avgCompareSeq3);
		System.out.println("Average comparisons for Sequence 4 is: " + avgCompareSeq4);
		System.out.println("--------------------------------------------------------");

		System.out.println("Average Time:");
		System.out.println();
		System.out.printf("Average Time Using Sequence h[i] = 2^i               for %d trials is %f ms%n", numberOfTrials, avgTimeSeq1 );
		System.out.printf("Average Time Using Sequence h[i] = 4^i - 3 * 2^i + 1 for %d trials is %f ms%n", numberOfTrials, avgTimeSeq2 );
		System.out.printf("Average Time Using Sequence h[i] = 2^p * 3^q         for %d trials is %f ms%n", numberOfTrials, avgTimeSeq3 );
		System.out.printf("Average Time Using Sequence h[i] = 2^i - 1           for %d trials is %f ms%n", numberOfTrials, avgTimeSeq4 );
		System.out.println("--------------------------------------------------------");

		System.out.println("Ratios:");
		System.out.println();
		System.out.printf("T1/T2 = %-3.3f%n", avgTimeSeq1 / avgTimeSeq2 );
		System.out.printf("T1/T3 = %-3.3f%n", avgTimeSeq1 / avgTimeSeq3 );
		System.out.printf("T1/T4 = %-3.3f%n", avgTimeSeq1 / avgTimeSeq4 );
		System.out.printf("T2/T3 = %-3.3f%n", avgTimeSeq2 / avgTimeSeq3 );
		System.out.printf("T2/T4 = %-3.3f%n", avgTimeSeq2 / avgTimeSeq4 );
		System.out.printf("T3/T4 = %-3.3f%n", avgTimeSeq3 / avgTimeSeq4 );
		System.out.println("  ------------------------");

		System.out.println("Total Trial Run Time:");
		System.out.println();
		System.out.printf("%.4f ms or %.4f seconds%n", trialDuration, trialDuration / 1000);
	}//end runTrials
}//end class
