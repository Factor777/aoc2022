package aoc2022.day2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RockPaperScissor {

	Map<String, Integer> scoreShapes = new HashMap();
	Map<String, Integer> scoreRound = new HashMap();

	Map<String, String> shapeToSelect = new HashMap();
	Map<String, Integer> scoreRound2 = new HashMap();
	int totalScore = 0;
	int totalScore2 = 0;

	public static void main(String[] args) {
		RockPaperScissor rockPaperScissor = new RockPaperScissor();
		int score = rockPaperScissor.getTotalScore();
		System.out.println(score);
		int score2 = rockPaperScissor.getTotalScore2();
		System.out.println(score2);
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

		shapeToSelect.put("AX", "Z"); 
		shapeToSelect.put("AY", "X");
		shapeToSelect.put("AZ", "Y");
		shapeToSelect.put("BX", "X"); 
		shapeToSelect.put("BY", "Y");
		shapeToSelect.put("BZ", "Z");
		shapeToSelect.put("CX", "Y"); 
		shapeToSelect.put("CY", "Z");
		shapeToSelect.put("CZ", "X");
		scoreRound2.put("AX", 0);
		scoreRound2.put("AY", 3);
		scoreRound2.put("AZ", 6);
		scoreRound2.put("BX", 0);
		scoreRound2.put("BY", 3);
		scoreRound2.put("BZ", 6);
		scoreRound2.put("CX", 0);
		scoreRound2.put("CY", 3);
		scoreRound2.put("CZ", 6);

		totalScore = 0;
		totalScore2 = 0;
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day2\\strategy.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				totalScore += calcPoints(line.charAt(0), line.charAt(2));
				totalScore2 += calcPoints2(line.charAt(0), line.charAt(2));
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

	private int calcPoints2(char otherHand, char myHand) {
		int myScore = scoreShapes.get(shapeToSelect.get("" + otherHand + myHand));
		int roundScore = scoreRound2.get("" + otherHand + myHand);
		return myScore + roundScore;
	}

	private int getTotalScore() {
		return totalScore;
	}

	private int getTotalScore2() {
		return totalScore2;
	}

}
