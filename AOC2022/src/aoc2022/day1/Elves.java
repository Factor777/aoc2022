package aoc2022.day1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Elves {
	HashMap<Integer, Integer> cals = new HashMap<Integer, Integer>();
	List<Map.Entry<Integer, Integer>> sorted;

	Elves(){
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day1\\calories1.txt"))) {
			String line;
			int elfId = 0;
			int totCals = 0;
			while ((line = br.readLine()) != null) {
				if (line.isBlank()) {
					elfId++;
					cals.put(elfId, totCals);
					totCals = 0;
				} else {
					totCals += Integer.parseInt(line);
				}
			}
			sorted = new ArrayList<>(cals.entrySet());
			sorted.sort(new Comparator<Map.Entry<Integer, Integer>>() {
				@Override
				public int compare(Map.Entry<Integer, Integer> m1, Map.Entry<Integer, Integer> m2) {
					return -m1.getValue().compareTo(m2.getValue());
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private int getMaxCalories(int topNum) {
		int sumCals = 0;
		for(int i = 0; i < topNum; i++) {
			sumCals += sorted.get(i).getValue();
		}
		return sumCals;
	}

	public static void main(String[] args) {
		Elves elves = new Elves();
		System.out.println(elves.getMaxCalories(1));
		System.out.println(elves.getMaxCalories(3));
	}

}
