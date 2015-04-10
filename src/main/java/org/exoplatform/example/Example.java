package org.exoplatform.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Example {

	public static void main(String[] args){
		//Some example from http://winterbe.com/posts/2014/07/31/java8-stream-tutorial-examples/
		List<Person> persons = Arrays.asList(
				new Person("Max", 18),
				new Person("Peter", 23),
				new Person("Pamela", 23),
				new Person("David", 12));
		Stream.of("d2", "a2", "b1", "b3", "c")
		.map(s -> s.toUpperCase())
		.filter(s -> s.startsWith("B"))
		.forEach(s -> System.out.println(s));
		//B1
		//B3
		
		Stream.of("d2", "a2", "b1", "b3", "c")
		.filter(s -> {
			System.out.println("Filting " + s);
			return s.startsWith("b");})
		.map(s -> {
			System.out.println("Mapping " + s);
			return s.toUpperCase();})
		.forEach(s -> System.out.println(s));
		

		Stream.of("d2", "a2", "b1", "b3", "c")
		.map(s -> {System.out.println(s);return s.toUpperCase();})
		.filter(s -> {System.out.println(s); return s.startsWith("B");});
		//don't print
		
		Arrays.asList("a1", "a2", "b1", "c2", "c1")
	    .parallelStream()
	    .filter(s -> {
	        System.out.format("filter: %s [%s]\n",
	            s, Thread.currentThread().getName());
	        return true;
	    })
	    .map(s -> {
	        System.out.format("map: %s [%s]\n",
	            s, Thread.currentThread().getName());
	        return s.toUpperCase();
	    })
	    .reduce((x,y) -> {System.out.format("reduce: %s + %s [%s]\n",
	        x,y, Thread.currentThread().getName()); return x + y;});

		Supplier<Stream<String>> streamSupplier =
				() -> Stream.of("d2", "a2", "b1", "b3", "c")
				.filter(s -> s.startsWith("a"));
		streamSupplier.get().anyMatch(s -> true);   
		streamSupplier.get().noneMatch(s -> true);
		//friends = stream(friends).sorted().toArray(String[]::new);

		String[] names = {"Sam","Pamela", "Dave", "Pascal", "Erik"};

		//Filter
		Object[] filteredNames =  Arrays.stream(names)
				.filter(c -> c.contains("am"))
				.toArray();
		
		int index = 0;
		for(String s : names){
			if(s.contains("am")){
				filteredNames[index++] = s;
			}
		}

		//Mapping
		Arrays.stream(names)
		.map(s -> "Hello " + s)
		.forEach(System.out::println);

		for(String s : names){
			System.out.println("Hello " + s);			
		}

		//Select distinct
		Object[] tempDistinct = Arrays.stream(names).distinct()
				.toArray();

		List<String> result = new ArrayList<String>();
		for(String s : names)
			if(!result.contains(s)) result.add(s);

		//Reduce
		Optional<String> reduceString = Arrays.stream(names)
				.reduce((x,y) -> x + " " + y);
		
		String resultReduceString = "";
		for(String s : names) 
			resultReduceString += " " + s;
		
		//Sorted
		Object[] resultSorted = Arrays.stream(names).sorted().toArray();

		String[] names_1 = names.clone();
		Arrays.sort(names_1);
		
		//Average
		Double averageAge = persons
			    .stream()
			    .collect(Collectors.averagingInt(p -> p.age));
		
		Double totalAge = (double) 0;
		for(Person p : persons){
			totalAge += p.age;
		}
		averageAge = totalAge/persons.size();

		//Grouping
		Map<Integer, List<Person>> personsByAge = persons
				.stream()
				.collect(Collectors.groupingBy(p -> p.age));
		
		personsByAge = new HashMap<Integer, List<Person>>();
		for(Person p : persons){
			if(personsByAge.containsKey(p.age)) {
				personsByAge.get(p.age).add(p);
			}else{
				ArrayList<Person> listPerson = new ArrayList<Person>();
				listPerson.add(p);
				personsByAge.put(p.age, listPerson);
			}
		}
		
		personsByAge
		.forEach((age, p) -> System.out.format("age %s: %s\n", age, p));
		
		//Partition
		Map<Boolean, List<Person>> partitionByPage = persons.stream()
		.collect(Collectors.partitioningBy(p -> p.age > 20));
		
		partitionByPage = new HashMap<Boolean, List<Person>>();
		partitionByPage.put(true, new ArrayList<Person>());
		partitionByPage.put(false, new ArrayList<Person>());
		for(Person p : persons){
			partitionByPage.get(p.age > 20).add(p);
		}
		//Joining 
		String phrase = persons
			    .stream()
			    .filter(p -> p.age >= 18)
			    .map(p -> p.name)
			    .collect(Collectors.joining(" and ", "In Germany ", " are of legal age."));
		
		StringBuilder sb = new StringBuilder();
		sb.append("In Germany ");
		for(Person p : persons){
			if(p.age >= 18) {
				sb.append(" and " + p.name);
			}
		}
		sb.append(" are of legal age.");
		phrase = sb.toString();
		
		System.out.println(phrase);
	}
}
