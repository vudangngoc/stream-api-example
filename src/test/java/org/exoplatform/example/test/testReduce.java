package org.exoplatform.example.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.exoplatform.example.Person;
import org.junit.Test;

public class testReduce {
	@Test
	public void test(){
		System.out.println();
		Random rand = new Random();

		long start_time, end_time,running_time = 0;
		long numberRound = 100;
		Integer result1=0,result2=0;
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
				result1 = intArray.stream().reduce((x,y) -> x + y).get();
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");
			
			running_time = 0;
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				result2 = intArray.parallelStream().reduce((x,y) -> x + y).get();
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");
			
			running_time = 0;
			int sum = 0;
			for(int round = 0; round < numberRound; round ++){
				sum = 0;
				start_time = System.nanoTime();
				for(int i : intArray) sum += i;
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.println(running_time/numberRound + ",");
			if(!result1.equals(result2) || !result2.equals(sum)) 
				System.out.println("Error!!! " + result1 + "," + result2+ "," + sum);
		}
	}
}
