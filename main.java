// Joseph Camarena
// 0588321
// jc0588321@swccd.edu

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Arrays;
import java.util.List;
import java.io.File;

public class main {

	public static void main(String[] args) throws IOException {
		
		//read file name and number of runs from cmd line
		String fileRead = "";
		if (args.length > 1 && args.length < 3) {
		   fileRead = args[0];
		} else {
		   System.err.println("Invalid # of arguments: " + args.length
				   		  + "\nArguments required:     2"
				   		  + "\nExiting Program.");
		   System.exit(0);
		}
		
		int numberOfTrials = 0;
		try {
            numberOfTrials = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            System.err.println("Error: The second argument must be an integer."
            			   + "\nExiting Program.");
            System.exit(1);
        }
		
		//read in file of N random Doubles from cl
		Scanner inFile = null;
        try {
            inFile = new Scanner(new File(fileRead));
        } catch (FileNotFoundException e) {
            System.err.println ("File: " + fileRead + " (No such file or directory)");
            System.exit(1);
        }
        //store file in the array data[]
        List<String> numLinesFile = Files.readAllLines(Paths.get(fileRead));
        Double[] data = new Double[ numLinesFile.size() ];
		int i = 0;
		while( inFile.hasNext() ) {
			data[i] = Double.parseDouble( inFile.next() );
			i++;
		}
		
		System.out.println( fileRead + ", avg from " + numberOfTrials + " runs follows:");
		System.out.println();
		System.out.println("Number of trials: " + numberOfTrials);
		System.out.println();
        System.out.println("Number of lines in file: " + numLinesFile.size() );
		System.out.println();
			
		//Build increment sequence arrays for this data set
		int upperVal = numLinesFile.size() / 2;
		//h1
		i = 0;
		int j = 0;
		
		while(Math.pow(2, i) < upperVal) {
			i++;
		}
		
		int[] h1 = new int[i--];
		
		System.out.println("seq 1:");
		i=0;
		for(j = h1.length-1; j >= 0; j--) {
			h1[i] = (int) Math.pow(2, j);
			System.out.print(" " + h1[i]);
			++i;
		}
		System.out.println();
		System.out.println();
		
		//h2
		i = 0;
		while( (Math.pow(4,i) + 3 * Math.pow(2,(i-1) ) + 1) < upperVal) {
			i++;
		}

		int[] h2 = new int[i];
		System.out.println("seq 2:");

		i = 0;
		for(j = h2.length-1; j >= 0; j--) {
			
			if(j==0) h2[i] = 1;
			else {
				h2[i] = (int) Math.pow(4, (j) ) + 3 * (int) Math.pow(2,j-1) + 1;
				++i;	
			}
		}
				
		for(i = 0; i < h2.length; i++)
		{
			System.out.print(" " + h2[i]);
		}
		System.out.println();
		System.out.println();

		//h3
		j=0;
		int count = 0;
		for (i=0; ((int)Math.pow(2,i) * (int)Math.pow(3,j)) < upperVal; i++ ) {
		
			for (j=0; ((int)Math.pow(2,i) * (int)Math.pow(3,j)) < upperVal; j++) {
				count++;
		    }
			j = 0;
		}

		int [] h3 = new int[count];

		int index = 0;
		j = 0;
		for (i=0; ((int)Math.pow(2,i) * (int)Math.pow(3,j)) < upperVal; i++ ) {
			
			for (j=0; ((int)Math.pow(2,i) * (int)Math.pow(3,j)) < upperVal; j++) {
				
				h3[index] = (int)Math.pow(2,i) * (int)Math.pow(3,j);
				index++;
		    }
			j = 0;
		}
		
        Arrays.sort(h3);
        //array reversal
        for (i = 0; i < h3.length / 2; i++) {
            int temp = h3[i]; // swap numbers
            h3[i] = h3[h3.length - 1 - i];
            h3[h3.length - 1 - i] = temp;
        }
        
		System.out.println("seq 3:");
		
		for (i=0; i<h3.length; i++) {
			
			System.out.print(" " + h3[i]); 
		}
            
		System.out.println();
        System.out.println();
        
		//h4
		i = 0;
		while(Math.pow(2, i) - 1 < upperVal) {
			i++;
		}
		
		int[] h4 = new int[--i];
		
		System.out.println("seq 4:");
		i=0;
		for(j = h4.length; j > 0; j--) {
			
			if (j==0) h4[i] = 1;
			else {
				h4[i] = (int) Math.pow(2, j) - 1;
				++i;
			}
		}
		
		for(i = 0; i < h4.length; i++)
		{
			System.out.print(" " + h4[i]);
		}
        System.out.println();
        System.out.println();
        
		// sort array of N items with each sequence j trial, timing each:
		ShellSort study = new ShellSort( data );
		long start = 0L, duration1 = 0L, duration2 = 0L, duration3 = 0L, duration4 = 0L;
		long comparisons1 = 0L, comparisons2 = 0L, comparisons3 = 0L, comparisons4 = 0L;
		j = 0;
		long trialStart = System.nanoTime();
		while( j < numberOfTrials) {
			
			start = System.nanoTime();
			study.sortUsing( h1 );
			duration1 += (System.nanoTime() - start);
			comparisons1 += study.getComparisons();
			study.resetComparisons();
			
			start = System.nanoTime();
			study.sortUsing( h2 );
			duration2 += (System.nanoTime() - start);
			comparisons2 += study.getComparisons();
			study.resetComparisons();
			
			start = System.nanoTime();
			study.sortUsing( h3 );
			duration3 += (System.nanoTime() - start);
			comparisons3 += study.getComparisons();
			study.resetComparisons();
			
			start = System.nanoTime();
			study.sortUsing( h4 );
			duration4 += (System.nanoTime() - start);
			comparisons4 += study.getComparisons();
			
			study = new ShellSort( data );
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
		System.out.println("Average comparisons for Sequence 1 is : " + avgCompareSeq1);
		System.out.println("Average comparisons for Sequence 2 is : " + avgCompareSeq2);
		System.out.println("Average comparisons for Sequence 3 is : " + avgCompareSeq3);
		System.out.println("Average comparisons for Sequence 4 is : " + avgCompareSeq4);
		System.out.println("--------------------------------------------------------");

		System.out.println("Average Time:");
		System.out.println();
		//System.out.println("Average Time Using Sequence h[i] = 2^i for " + numberOfTrials + " trials is " + avgTimeSeq1 + " ms");
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
	}//end main
}//end class