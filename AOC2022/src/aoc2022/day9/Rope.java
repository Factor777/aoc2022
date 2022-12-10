package aoc2022.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Rope {
	Set<Pos> coordsVisited = new HashSet<>();
	Pos pos = new Pos(0, 0);
	Pos posTail = new Pos(0, 0);

	public static void main(String[] args) {
		new Rope();
	}

	public Rope() {

		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day9\\input.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				String[] split = line.split(" ");
				switch (split[0]) {
				case "U":
					move(0, 1, Integer.valueOf("" + split[1]));
					break;
				case "R":
					move(1, 0, Integer.valueOf("" + split[1]));
					break;
				case "D":
					move(0, -1, Integer.valueOf("" + split[1]));
					break;
				case "L":
					move(-1, 0, Integer.valueOf("" + split[1]));
					break;
				default:
					break;
				}
			}
			System.out.println(coordsVisited.size());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void move(int x, int y, int amount) {
		for (int i = 0; i < amount; i++) {
			pos.x += x;
			pos.y += y;
			if (moveTail(0) || moveTail(1) || inDiagonal(1)) {

			} else if (moveTail(2)) {
				posTail.x += x;
				posTail.y += y;
			} else {
				if (x == 0) {
					posTail.x = pos.x;
					posTail.y += y;
				} else {
					posTail.x += x;
					posTail.y = pos.y;
				}
			}
			coordsVisited.add(new Pos(posTail.x, posTail.y));
		}
	}

	private boolean inDiagonal(int val) {
		return (posTail.x + val == pos.x && posTail.y + val == pos.y) ||
				(posTail.x + val == pos.x && posTail.y - val == pos.y) ||
				(posTail.x - val == pos.x && posTail.y - val == pos.y) ||
				(posTail.x - val == pos.x && posTail.y + val == pos.y);
	}

	private boolean moveTail(int val) {
		return (posTail.x + val == pos.x && posTail.y== pos.y) ||
				(posTail.x - val == pos.x && posTail.y== pos.y) ||
				(posTail.x == pos.x && posTail.y + val == pos.y) ||
				(posTail.x == pos.x && posTail.y - val == pos.y);
	}

	class Pos {
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public int hashCode() {
			return Objects.hash(x,y); 
		}

		int x;
		int y;
		
		@Override
		public boolean equals(Object obj) {
			Pos p = (Pos)obj;
			return p.x == x && p.y == y;
		}

	}
	

}
