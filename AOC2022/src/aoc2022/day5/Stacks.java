package aoc2022.day5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Stacks {

	private static final Integer WIDTH = 4;

	public static void main(String[] args) {
		new Stacks();
	}

	public Stacks() {
		String line;
		Map<Integer, Deque<String>> stacks = new HashMap<>();
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day5\\input.txt"))) {
			while ((line = br.readLine()) != null) {
				if (!line.startsWith("move")) {
					parseStackData(line, stacks);
				} else {
					String[] split = line.replace("move", "").replace("from", ",").replace("to", ",").split(",");
					Integer amount = Integer.valueOf(split[0].trim());
					Integer from = Integer.valueOf(split[1].trim());
					Integer to = Integer.valueOf(split[2].trim());
					Deque<String> tempDeque = new ArrayDeque<String>();
					for (int i = 0; i < amount; i++) {
//						stacks.get(to).push(stacks.get(from).pop());
						tempDeque.push(stacks.get(from).pop());
					}
					for (int i = 0; i < amount; i++) {
						stacks.get(to).push(tempDeque.pop());
					}
				}
			}
			StringBuilder res = new StringBuilder(); 
			for (Integer key : stacks.keySet()) {
				res.append(stacks.get(key).peek());
			}
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void parseStackData(String line, Map<Integer, Deque<String>> stacks) {
		int stackId = 1;
		for (int i = 0; i < line.length(); i += WIDTH) {
			Deque<String> stack = stacks.get(stackId);
			if (stack == null) {
				stack = new ArrayDeque<String>();
			}
			String crateId = line.substring(i + 1, i + 2).trim();
			if (!crateId.isEmpty()) {
				stack.add(crateId);
			}
			stacks.put(stackId++, stack);
		}
	}

}
