package org.exoplatform.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class testFilter {

	@Test
	public void testIntegerStreamFilter(){
		System.out.println();
		Random rand = new Random();

		long start_time, end_time,running_time = 0;
		long numberRound = 10;
		System.out.println(", serial stream, parallel stream, active iterator ");
		
		for(double arrSize = 1000; arrSize < 8000000; arrSize *= 1.25){
			List<Integer> listIn = new ArrayList<Integer>();
			for (int i = 0; i < arrSize; i++) {
				listIn.add(rand.nextInt(10000));
			}
			System.out.print(listIn.size() + ",");
			for(int round = 0; round < numberRound; round ++){
				start_time = System.currentTimeMillis();
				listIn.stream().filter(i -> i > 200).count();		
				end_time = System.currentTimeMillis();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");

			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.currentTimeMillis();
				listIn.parallelStream().filter(i -> i > 200).count();		
				end_time = System.currentTimeMillis();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");

			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.currentTimeMillis();
				int count = 0;
				for(int i : listIn){
					if(i > 200) count++;
				}	
				end_time = System.currentTimeMillis();
				running_time += (end_time - start_time);
			}
			System.out.println(running_time/numberRound);

		}
	}
}
