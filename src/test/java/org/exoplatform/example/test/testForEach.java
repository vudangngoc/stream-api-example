package org.exoplatform.example.test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class testForEach {

	@Test
	public void test(){
		System.out.println();
		Random rand = new Random();

		long start_time, end_time,running_time = 0;
		long numberRound = 100;
		System.out.println(",serial stream,parallel stream,active iterator ");

		for(double arrSize = 1000; arrSize < 8000000; arrSize *= 1.25){
			List<Integer> intArray = new ArrayList<Integer>();
			for (int i = 0; i < arrSize; i++) {
				intArray.add(rand.nextInt(10000));
			}
			System.out.print(intArray.size() + ",");
			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				intArray.stream().forEach(s -> {int i = 0;});
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");

			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				intArray.parallelStream().forEach(s -> {int i = 0;});
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");

			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();				
				for(int j : intArray){
					int i = 0;
				}
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.println(running_time/numberRound + ",");
		}
	}
}
