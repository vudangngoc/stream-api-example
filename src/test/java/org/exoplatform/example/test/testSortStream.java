package org.exoplatform.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class testSortStream {


	

	@Test
	public void testIntegerStreamSort(){
		System.out.println();
		Random rand = new Random();
		long start_time, end_time,running_time = 0;
		long numberRound = 10;
		System.out.println(", serial stream, parallel stream, active iterator ");

		for(double arrSize = 1000; arrSize < 8000000; arrSize *= 1.25){
			List<Integer> listIn = new ArrayList<Integer>();
			int[] arr = new int[(int) Math.round(arrSize)];
			for (int i = 0; i < arr.length; i++) {
				int temp = rand.nextInt(10000);
				listIn.add(temp);
			}
			System.out.print(listIn.size() + ",");
			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				listIn.stream().sorted().count();		
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");

			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				listIn.parallelStream().parallel().sorted().count();		
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");

			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				sort(listIn);		
				int count = 0;
				for(int i = 0; i < listIn.size(); i++) count++;
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.println(running_time/numberRound);

		}
	}
	public void sort(List<Integer> inputArr) {

		if (inputArr == null || inputArr.size() == 0) {
			return;
		}
		int length = inputArr.size();
		quickSort(inputArr,0, length - 1);
	}

	private void quickSort(List<Integer> inputArr,int lowerIndex, int higherIndex) {

		int i = lowerIndex;
		int j = higherIndex;
		// calculate pivot number, I am taking pivot as middle index number
		int pivot = inputArr.get(lowerIndex+(higherIndex-lowerIndex)/2);
		// Divide into two arrays
		while (i <= j) {
			/**
			 * In each iteration, we will identify a number from left side which
			 * is greater then the pivot value, and also we will identify a number
			 * from right side which is less then the pivot value. Once the search
			 * is done, then we exchange both numbers.
			 */
			while (inputArr.get(i) < pivot) {
				i++;
			}
			while (inputArr.get(j) > pivot) {
				j--;
			}
			if (i <= j) {
				exchangeNumbers(inputArr,i, j);
				//move index to next position on both sides
				i++;
				j--;
			}
		}
		// call quickSort() method recursively
		if (lowerIndex < j)
			quickSort(inputArr,lowerIndex, j);
		if (i < higherIndex)
			quickSort(inputArr,i, higherIndex);
	}

	private void exchangeNumbers(List<Integer> inputArr,int i, int j) {
		int temp = inputArr.get(i);
		inputArr.set(i,inputArr.get(j));
		inputArr.set(j,temp);
	}
}
