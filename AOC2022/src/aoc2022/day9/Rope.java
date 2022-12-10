package aoc2022.day9;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Rope {
	private static final int NUM_KNOTS = 10;

	Set<Pos> coordsVisited = new HashSet<>();
	Pos knots[] = new Pos[NUM_KNOTS];

	public static void main(String[] args) {
		new Rope();
	}

	public Rope() {

		for (int i = 0; i < knots.length; i++) {
			knots[i] = new Pos(0, 0);
		}

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
			for (int i2 = 0; i2 < knots.length; i2++) {
				if(i2 == 0) {
					knots[i2].x += x;
					knots[i2].y += y;
				} else {
					if (moveTail(0, i2) || moveTail(1, i2) || inDiagonal(1, i2)) {
	
					} else if (moveTail(2, i2)) {
						knots[i2].x += (knots[i2-1].x - knots[i2].x) == 0 ? 0 : (knots[i2-1].x - knots[i2].x) / 2;
						knots[i2].y += (knots[i2-1].y - knots[i2].y) == 0 ? 0 : (knots[i2-1].y - knots[i2].y) / 2;
					} else {
						knots[i2].x += (knots[i2-1].x - knots[i2].x > 0) ? 1 : -1;
						knots[i2].y += (knots[i2-1].y - knots[i2].y > 0) ? 1 : -1;
					}
				}
			}
			coordsVisited.add(new Pos(knots[NUM_KNOTS - 1].x, knots[NUM_KNOTS - 1].y));
		}
	}

	private boolean inDiagonal(int val, int tailId) {
		return (knots[tailId].x + val == knots[tailId-1].x && knots[tailId].y + val == knots[tailId-1].y)
				|| (knots[tailId].x + val == knots[tailId-1].x && knots[tailId].y - val == knots[tailId-1].y)
				|| (knots[tailId].x - val == knots[tailId-1].x && knots[tailId].y - val == knots[tailId-1].y)
				|| (knots[tailId].x - val == knots[tailId-1].x && knots[tailId].y + val == knots[tailId-1].y);
	}

	private boolean moveTail(int val, int tailId) {
		return (knots[tailId].x + val == knots[tailId-1].x && knots[tailId].y == knots[tailId-1].y) || (knots[tailId].x - val == knots[tailId-1].x && knots[tailId].y == knots[tailId-1].y)
				|| (knots[tailId].x == knots[tailId-1].x && knots[tailId].y + val == knots[tailId-1].y) || (knots[tailId].x == knots[tailId-1].x && knots[tailId].y - val == knots[tailId-1].y);
	}

	class Pos {
		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}

		int x;
		int y;

		@Override
		public boolean equals(Object obj) {
			Pos p = (Pos) obj;
			return p.x == x && p.y == y;
		}

	}

}
