package com.main.java;

import java.io.File;
import java.io.IOException;
import java.security.KeyStore.Entry;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.main.java.model.Person;

public class CSVFileReader implements FileReader{

	public List<Person> persList = new ArrayList<>();
	private CompareByCItyAndGender compared = new CompareByCItyAndGender();
	
	private static final double USD_INR  = 66.00;
	private static final double USD_GBP  = 0.67;
	private static final double USD_SGD  = 1.5;
	private static final double USD_HKD  = 8;
	
	@Override
	public String readFile(File file) {
		List<String> lines;
		try {
			lines = FileUtils.readLines(file);
			for(String s : lines) {
				formatCSVData(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;
	}

	private void formatCSVData(String s) {
		String[] row  =  s.split(":");
		//Hyderabad:India:M:INR:20000
		if(!row[0].isEmpty()) {
			Person pers = new Person(row[0], row[1], row[2],row[3], Integer.parseInt(row[4]));
			persList.add(pers);
			
		}
		
		
	}

	public void calculateAverage() {

		Collections.sort(persList, compared);
		for(Map.Entry<Person, Integer> pair : compared.getMap().entrySet()) {
			Person pers  =pair.getKey();
			
			double totalAvIncome = pers.getAvIncome();
			String curr = pers.getCurrency();
			switch(curr) {
			
			case "INR" :
				totalAvIncome = totalAvIncome/USD_INR;
				break;
				
			case "GBP" :
				totalAvIncome = totalAvIncome/USD_GBP;
				break;
			case "SGD" :
				totalAvIncome = totalAvIncome/USD_SGD;
				break;
			case "HKD" :
				totalAvIncome = totalAvIncome/USD_HKD;
				break;
				
				
			
			}
			double calculatedAv = totalAvIncome/pair.getValue();
			pair.getKey().setAvIncome(calculatedAv);
			
		}
		
	}

	public void showAverage() {
		
		for(Map.Entry<Person, Integer> pair : compared.getMap().entrySet()) {
			Person pers = pair.getKey();
			System.out.println(pers.getCity()+" : "+pers.getCountry()+" : "+pers.getGender()+" : "+pers.getAvIncome());
			
		}
		
	}

}
