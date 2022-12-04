package aoc2022.day3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

		Map<Character, Integer> rucksack = new HashMap();
		List<Set<Character>> rucksacks = new ArrayList();
		List<Set<Character>> group = new ArrayList();

		totPrioScore = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day3\\input.txt"))) {
			String line;
			int row = 0;
			while ((line = br.readLine()) != null) {
				row++;
				compartment1.clear();
				compartment2.clear();
				rucksack.clear();
				group.clear();
				int length = line.length() / 2;
				checkCompartment(compartment1, line, 0, length);
				checkCompartment(compartment2, line, length, length * 2);
				rucksacks.add(compartment1.keySet());
				rucksacks.add(compartment2.keySet());
				checkCompartment(rucksack, line, 0, length * 2);
				group.add(rucksack.keySet());
				Character item = getCommonItem(0, compartment1.keySet(), rucksacks);
				if(row % 3 == 0) {
					item = getCommonItem(0, compartment1.keySet(), group);
				}
				if (item != null) {
					int prio = (int) item;
					if (item.isLowerCase(item)) {
						prio -= (int) 'a';
						prio += 1;
					} else {
						prio -= (int) 'A';
						prio += 27;
					}
					totPrioScore += prio;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getTotPrioScore() {
		return totPrioScore;
	}

	private void checkCompartment(Map<Character, Integer> compartment, String line, int start, int length) {
		for (int i = start; i < length; i++) {
			Integer val = compartment.get(line.charAt(i));
			if (val == null) {
				val = 1;
			} else {
				val++;
			}
			compartment.put(line.charAt(i), val++);
		}
	}

	private Character getCommonItem(int idx, Set<Character> currentChars, List<Set<Character>> compartments) {
		Set<Character> currentChars2 = new HashSet<>();
		if (++idx < compartments.size()) {
			Set<Character> commonItems = compartments.get(idx);
			for (Character character : currentChars) {
				if (commonItems.contains(character)) {
					currentChars2.add(character);
				}
			}
			return getCommonItem(idx, currentChars2, compartments);
		}
		return currentChars.iterator().next();
	}

}
