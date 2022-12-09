package aoc2022.day8;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tree {
	List<char[]> matrix = new ArrayList<>();

	public static void main(String[] args) {
		new Tree();
	}

	public Tree() {

		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day8\\input.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				char[] charArray = line.toCharArray();
				matrix.add(charArray);
			}
			int width = matrix.get(0).length;
			int height = matrix.size();
			int viewProd = 0;
			int numHidden = 0;

			for (int x = 1; x < width - 1; x++) {
				for (int y = 1; y < height - 1; y++) {
					int treeHeight = Integer.valueOf("" + matrix.get(y)[x]);
					boolean hidden = isHidden(treeHeight, x, x, 0, y - 1)
							&& isHidden(treeHeight, x + 1, width - 1, y, y)
							&& isHidden(treeHeight, x, x, y + 1, height - 1) && isHidden(treeHeight, 0, x - 1, y, y);
					numHidden += hidden ? 1 : 0;

					int val = getViewNeg(treeHeight, x, x, 0, y - 1);
					int val2 = getView(treeHeight, x + 1, width - 1, y, y);
					int val3 = getView(treeHeight, x, x, y + 1, height - 1);
					int val4 = getViewNeg(treeHeight, 0, x - 1, y, y);

					int newProd = val * val2 * val3 * val4;
					if (newProd > viewProd) {
						viewProd = newProd;
					}
				}
			}
			
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					int treeHeight = Integer.valueOf("" + matrix.get(y)[x]);
					boolean hidden = isHidden(treeHeight, x, x, 0, y - 1)
							&& isHidden(treeHeight, x + 1, width - 1, y, y)
							&& isHidden(treeHeight, x, x, y + 1, height - 1) && isHidden(treeHeight, 0, x - 1, y, y);
					numHidden += hidden ? 1 : 0;

					int val = getViewNeg(treeHeight, x, x, 0, y - 1);
					int val2 = getView(treeHeight, x + 1, width - 1, y, y);
					int val3 = getView(treeHeight, x, x, y + 1, height - 1);
					int val4 = getViewNeg(treeHeight, 0, x - 1, y, y);

					int newProd = val * val2 * val3 * val4;
					if (newProd > viewProd) {
						viewProd = newProd;
					}
				}
			}

			int tot = (width * height) - numHidden;
			System.out.println(tot);
			System.out.println(viewProd);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean isHidden(int treeHeight, int x1, int x2, int y1, int y2) {
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				int th = Integer.valueOf("" + matrix.get(y)[x]);
				if (th >= treeHeight) {
					return true;
				}
			}
		}
		return false;
	}
	
	private int getViewNeg(int treeHeight, int x1, int x2, int y1, int y2) {
		int dist = 0;
		for (int x = x2; x >= x1; x--) {
			for (int y = y2; y >= y1; y--) {
				dist++;
				int th = Integer.valueOf("" + matrix.get(y)[x]);
				if (th >= treeHeight) {
					return dist;
				}
			}
		}
		return dist;
	}

	private int getView(int treeHeight, int x1, int x2, int y1, int y2) {
		int dist = 0;
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				dist++;
				int th = Integer.valueOf("" + matrix.get(y)[x]);
				if (th >= treeHeight) {
					return dist;
				}
			}
		}
		return dist;
	}

}
