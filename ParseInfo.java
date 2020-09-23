package upg4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ParseInfo {
	
	
	public static String COMMA_DELIMITER = ",";
	
	
	public static void main(String[] args) {
		List<List<String>> data = new ArrayList<>();
		List<String> dataColumn = new ArrayList<String>(); 
		try (Scanner scanner = new Scanner(new File("sample.csv"));) {
			while (scanner.hasNextLine()) {
				data.add(getDataLine(scanner.nextLine()));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for(List<String> row:data){ 
			dataColumn.add(row.get(1));
			dataColumn.add(row.get(2));
		}
		countA(dataColumn);
		countDupDates(data, dataColumn);
		countAndroids(data);

	}

	private static void countAndroids(List<List<String>> records) {
		List<String> androidColumn = new ArrayList<String>();
		int count = 0;
		for(List<String> row : records){ 
			androidColumn.add(row.get(6));
		}

		for(String android : androidColumn) {
			if (android.equals("Android App")){
				count++;
			}	
		}
		System.out.println(count * 2 + " work with Android"); 
	}
	
	private static void countA(List<String> nameColumn) {
		int count = 0;
		for(int i=1; i<nameColumn.size(); i++) {
			if ((nameColumn.get(i).contains("a") || nameColumn.get(i).contains("A")) && !nameColumn.get(i).equals("Name group member #2")) {
				count++;
			}
		}
		System.out.println(count + " names contain \"a\".");
	}
	
	private static void countDupDates(List<List<String>> records, List<String> nameColumn) {
		Map<Integer, String> uniqueDates = new HashMap<>();
		Map<Integer, String> duplicateDates = new HashMap<>();

		List<String> dateColumn = new ArrayList<String>();
		

		int z = 0;
		for(List<String> row : records){ 
			dateColumn.add(row.get(0));
			if(uniqueDates.containsValue(dateColumn.get(z)) && !dateColumn.get(z).equals("")) {
				duplicateDates.put(z, dateColumn.get(z));
			}
			else {
				uniqueDates.put(z, dateColumn.get(z));
			}
			z++;
		}

		for (Map.Entry<Integer, String> entry : duplicateDates.entrySet()) {
			for(int i=1; i<dateColumn.size(); i++) {
				if (duplicateDates.get(entry.getKey()).equals(dateColumn.get(i)) && !dateColumn.get(i).equals("") && !nameColumn.get(entry.getKey() * 2).equals(nameColumn.get(i * 2))) {
					System.out.println(nameColumn.get(entry.getKey() * 2) + " and " + nameColumn.get(entry.getKey() * 2 + 1) + " have the same date as " + nameColumn.get(i * 2)  + " and " + nameColumn.get(i * 2 + 1));
				}
			}
		}
	}
			
		private static List<String> getDataLine(String line) {
			List<String> values = new ArrayList<String>();
			try (Scanner rowScanner = new Scanner(line)) {
				rowScanner.useDelimiter(COMMA_DELIMITER);
				while (rowScanner.hasNext()) {
					values.add(rowScanner.next());
				}
			}
			return values;
	}

}
