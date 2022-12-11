package aoc2022.day11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jdk.jshell.JShell;
import jdk.jshell.SnippetEvent;

public class Monkey {

	private static final int NUM_ROUNDS = 20;

	static private Map<Integer, Monkey> monkeyes = new HashMap();
	static JShell js = JShell.create();

	private List<Integer> items = new ArrayList<>();
	private int id;
	private String operator;
	private int divisible;
	private Integer monkeyeTrue;
	private Integer monkeyeFalse;
	private int numInpsected;

	public Monkey(Integer id) {

		this.id = id;
	}

	private void setItems(String[] split) {
		for (String val : split) {
			items.add(Integer.valueOf("" + val.trim()));
		}
	}

	private void setOperator(String operator) {
		this.operator = operator;
	}

	private void setDivisible(Integer divisible) {
		this.divisible = divisible;
	}

	private void throwItems() {
		for (Integer integer : items) {
			String op = operator.replace("old", String.valueOf(integer)).trim();
			List<SnippetEvent> eval2 = js.eval(op);
			SnippetEvent snippetEvent = eval2.get(0);
			int newValue = Integer.valueOf(snippetEvent.value().trim()) / 3;
			if (newValue % divisible != 0) {
				monkeyes.get(monkeyeTrue).send(newValue);
			} else {
				monkeyes.get(monkeyeFalse).send(newValue);
			}
			numInpsected++;
		}
		items.clear();
	}

	private void send(int newValue) {
		items.add(newValue);
	}

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day11\\input.txt"))) {
			String line;
			Monkey current;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				switch (split[0]) {
				case "Monkey":
					current = new Monkey(Integer.valueOf("" + split[1].replace(":", "")));
					current.setItems(br.readLine().replace("Starting items:", "").split(","));
					current.setOperator(br.readLine().replace("Operation: new = ", ""));
					current.setDivisible(Integer.valueOf(br.readLine().replace("Test: divisible by", "").trim()));
					current.setMonkeyeTrue(
							Integer.valueOf(br.readLine().replace("If true: throw to monkey", "").trim()));
					current.setMonkeyeFalse(
							Integer.valueOf(br.readLine().replace("If false: throw to monkey", "").trim()));
					monkeyes.put(current.getId(), current);
					break;
				default:
					break;
				}
			}
			for (int i = 0; i < NUM_ROUNDS; i++) {
				for (Integer key : monkeyes.keySet()) {
					monkeyes.get(key).throwItems();
				}
			}
			for(Monkey m : monkeyes.values()) {
				System.out.println(m.getId() + " Inpected: " + m.getInspected());
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int getInspected() {
		return numInpsected;
	}

	private Integer getId() {
		return id;
	}

	private void setMonkeyeFalse(Integer monkeyeTrue) {
		this.monkeyeTrue = monkeyeTrue;
	}

	private void setMonkeyeTrue(Integer monkeyeFalse) {
		this.monkeyeFalse = monkeyeFalse;
	}

}
