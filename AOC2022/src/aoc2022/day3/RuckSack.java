package aoc2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class RuckSack {

	int totPrioScore = 0;
	private static RuckSack ruckSack;

	public static void main(String[] args) {
		ruckSack = new RuckSack();
		System.out.println(ruckSack.getTotPrioScore());
	}
	
	public RuckSack() {
		Map<Character, Integer> compartment1 = new HashMap();
		Map<Character, Integer> compartment2 = new HashMap();

		List<Map<Character, Integer>> group = new ArrayList();

		totPrioScore = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day3\\input.txt"))) {
			String line;
			int groupIdx = 0;
			while ((line = br.readLine()) != null) {
				compartment1.clear();
				compartment2.clear();
				int length = line.length() / 2;
				checkCompartment(compartment1, line, 0, length);
				checkCompartment(compartment2, line, length, length * 2);
				Character item = getCommonItem(compartment1, compartment2);
				if(item != null) {
					int prio = (int) item;
					if(item.isLowerCase(item)) {
						prio -= (int)'a';
						prio += 1;
					} else {
						prio -= (int)'A';
						prio += 27;
					}
					totPrioScore += prio;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void checkGroups() {
	}

	private int getTotPrioScore() {
		return totPrioScore;
	}

	private void checkGroup(List<Map <Character, Integer>> rucksack, String line, int start, int length) {
		for (Iterator iterator = rucksack.iterator(); iterator.hasNext();) {
			Map<Character, Integer> map = (Map<Character, Integer>) iterator.next();
		}
	}

	private void checkCompartment(Map<Character, Integer> compartment, String line, int start, int length) {
		for(int i = start; i < length; i++) {
			Integer val = compartment.get(line.charAt(i));
			if(val == null) {
				val = 1;
			} else {
				val++;
			}
			compartment.put(line.charAt(i), val++);
		}
	}

	private Character getCommonItem(Map<Character, Integer> compartment1, Map<Character, Integer> compartment2) {
		for (Map.Entry<Character, Integer> entry : compartment1.entrySet()) {
			if(compartment2.containsKey(entry.getKey())) {
				return entry.getKey();
			}
		}
		return null;
	}

}
