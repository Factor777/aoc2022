package aoc2022.day10;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CathodeRay {
	private static final int NUM_KNOTS = 10;
	private static final int SCREEN_WIDTH = 40;
	private static final int SCREEN_HEIGHT = 6;

	Integer row = 0;
	int column = 0;
	Integer checkPos = 0;
	Integer sum = 0;
	List<List<Character>> display = new ArrayList<>();

	public static void main(String[] args) {
		new CathodeRay();
	}

	public CathodeRay() {
		
		for(int i = 0; i < SCREEN_HEIGHT; i++) {
			display.add(new ArrayList<>());
		}

		int cycle = 0;
		int register = 1;
		int checks[] = new int[] { 20, 60, 100, 140, 180, 220, 500 };
		List<Integer> res = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day10\\input.txt"))) {
			String line;
			while ((line = br.readLine()) != null && checkPos < checks.length && row < SCREEN_HEIGHT) {
				String[] split = line.split(" ");
				switch (split[0]) {
				case "noop":
					for (int i = 0; i < 1; i++) {
						cycle = updateCycle(cycle, register, checks, res);
					}
					break;
				case "addx":
					for (int i = 0; i < 2; i++) {
						cycle = updateCycle(cycle, register, checks, res);
					}
					register += Integer.valueOf("" + split[1]);
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for (List<Character> pixels : display) {
			System.out.println(pixels.toString());
		}
		
		System.out.println(res.toString());
		System.out.println(sum);
	}

	private int updateCycle(int cycle, int register, int[] checks, List<Integer> res) {
		if(register - 1 <= column && register + 1 >= column) {
			display.get(row).add('#');
		} else {
			display.get(row).add(' ');
		}
		cycle++;
		column++;
		if(cycle % SCREEN_WIDTH == 0) {
			row++;
			column = 0;
		}
		if(cycle == checks[checkPos]) {
			res.add(cycle * register);
			sum += cycle * register;
			checkPos++;
		}
		return cycle;
	}
}
