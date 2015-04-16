package org.exoplatform.example;

import java.util.List;

public abstract class MyRunnable implements Runnable {

	private String data;
	protected List<String> outputList;

	public MyRunnable(List<String> filtedList, String data){
		this.data = data;
		this.outputList = filtedList;
	}
	

}
