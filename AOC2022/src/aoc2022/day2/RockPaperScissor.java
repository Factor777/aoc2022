package aoc2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RockPaperScissor {

	Map<String, Integer> scoreShapes = new HashMap();
	Map<String, Integer> scoreRound = new HashMap();
	int totalScore = 0;

	public static void main(String[] args) {
		RockPaperScissor rockPaperScissor = new RockPaperScissor();
		int score = rockPaperScissor.getTotalScore();
		System.out.println(score);
	}

	public RockPaperScissor() {
		scoreShapes.put("X", 1); // Rock
		scoreShapes.put("Y", 2); // Paper
		scoreShapes.put("Z", 3); // Scissor
		scoreRound.put("AX", 3);
		scoreRound.put("AY", 6);
		scoreRound.put("AZ", 0);
		scoreRound.put("BX", 0);
		scoreRound.put("BY", 3);
		scoreRound.put("BZ", 6);
		scoreRound.put("CX", 6);
		scoreRound.put("CY", 0);
		scoreRound.put("CZ", 3);

		totalScore = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day2\\strategy.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				totalScore += calcPoints(line.charAt(0), line.charAt(2));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int calcPoints(char otherHand, char myHand) {
		int myScore = scoreShapes.get("" + myHand);
		int roundScore = scoreRound.get("" + otherHand + myHand);
		return myScore + roundScore;
	}

	private int getTotalScore() {
		return totalScore;
	}

}
