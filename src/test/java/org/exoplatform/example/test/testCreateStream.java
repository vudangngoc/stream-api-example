package org.exoplatform.example.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Test;

public class testCreateStream {
	@Test
	public void testCreate(){
		Random rand = new Random();

		long start_time, end_time,running_time = 0;
		long numberRound = 1000;
		System.out.println(", serial stream, parallel stream, active iterator ");
		
		for(double arrSize = 1; arrSize < 8000000; arrSize *= 1.25){
			List<Integer> listIn = new ArrayList<Integer>();
			for (int i = 0; i < arrSize; i++) {
				listIn.add(rand.nextInt(10000));
			}
			System.out.print(listIn.size() + ",");
			listIn.stream();
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				listIn.stream();	
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.print(running_time/numberRound + ",");
			
			running_time = 0;
			listIn.parallelStream();
			for(int round = 0; round < numberRound; round ++){
				start_time = System.nanoTime();
				listIn.parallelStream();	
				end_time = System.nanoTime();
				running_time += (end_time - start_time);
			}
			System.out.println(running_time/numberRound);
		}
	}
}
