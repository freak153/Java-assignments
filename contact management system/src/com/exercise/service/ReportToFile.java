package com.exercise.service;

import java.io.FileWriter;
import java.io.IOException;

public class ReportToFile {

	FileWriter csv;

	public ReportToFile(String fileName) {
		super();
		try {
			this.csv = new FileWriter(fileName);
		} catch (IOException e) {
			System.out.println("Couldn't create the file.");
			e.printStackTrace();
		}
	}
	
	public void addData(String data) {
		try {
			System.out.println("Reported data to the file.");
			csv.append(data);
			csv.flush();
			csv.close();
			
		} catch (IOException e) {
			System.out.println("Couldn't report to the file...");
			e.printStackTrace();
		}
		
		
	}
}
