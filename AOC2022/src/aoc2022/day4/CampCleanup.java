package aoc2022.day4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CampCleanup {

	private static final int MIN_1 = 0;
	private static final int MAX_1 = 1;
	private static final int MIN_2 = 2;
	private static final int MAX_2 = 3;

	public static void main(String[] args) {
		new CampCleanup();
	}

	public CampCleanup() {
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day4\\input.txt"))) {
			String line;
			int row = 0;
			int score = 0;
			while ((line = br.readLine()) != null) {
				List<Integer> values = getLimits(line);
				if (overlaps(values)) {
					score++;
				}
			}
			System.out.println(score);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean overlaps(List<Integer> values) {
		return (values.get(MIN_1) <= values.get(MIN_2) && values.get(MAX_1) >= values.get(MAX_2)
				|| values.get(MIN_1) >= values.get(MIN_2) && values.get(MAX_1) <= values.get(MAX_2));
	}

	private List<Integer> getLimits(String line) {
		List<Integer> values = new ArrayList<>();
		String[] split = line.split(",");
		for (String pairs : split) {
			String[] ranges = pairs.split("-");
			for (String range : ranges) {
				values.add(Integer.valueOf(range));
			}
		}
		return values;
	}

	class Limit {

	}
}
