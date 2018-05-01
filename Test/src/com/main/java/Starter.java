package com.main.java;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Starter {
	
	public static final String filePath = "D:\\Workspace\\Test\\resources\\Data.txt";
	
	public static void main(String args[]) {
		CSVFileReader csv = new CSVFileReader();
		csv.readFile(new File(filePath));
		csv.calculateAverage();
		csv.showAverage();
		
		
	}

}
