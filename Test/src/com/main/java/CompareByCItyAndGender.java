package com.main.java;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.main.java.model.Person;

public class CompareByCItyAndGender implements Comparator<Person> {

	private Map<Person, Integer> map = new HashMap<>();

	@Override
	public int compare(Person o1, Person o2) {
		int result = o1.getCity().compareTo(o2.getCity());
		result += o1.getGender().compareTo(o2.getGender());
		result += o1.getCountry().compareTo(o2.getCountry());

		if (result == 0) {
			int count =0;
			Person temp= null;
			if (map.containsKey(o1)) {
				
				for(Map.Entry<Person, Integer> pair : map.entrySet()) {
					if(pair.getKey().equals(o1)) {
						temp = pair.getKey();
						count = pair.getValue();
						count++;
						temp.setAvIncome(temp.getAvIncome() + o1.getAvIncome());
					}
					
				}
				

			}
			else {
				try {
					temp = (Person) o1.clone();
					temp.setAvIncome(o1.getAvIncome() + o2.getAvIncome());
					count = 2;
					
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			map.put(temp, count);
		}
		return result;
	}

	public Map<Person, Integer> getMap() {
		return map;
	}

	public void setMap(Map<Person, Integer> map) {
		this.map = map;
	}
	
	

}
