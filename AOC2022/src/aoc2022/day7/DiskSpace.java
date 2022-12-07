package aoc2022.day7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DiskSpace {

	public static void main(String[] args) {
		new DiskSpace();
	}

	public DiskSpace() {
		List<Node> nodes = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("Resources\\day7\\input.txt"))) {
			Node rootNode = new Node(null, br);
			rootNode.parse();
			
			int size = 0;
			rootNode.getNodes(nodes);
			for (Node node : nodes) {
				if(node.size <= 100000) {
					size += node.size;
				}
			}
			System.out.println("Tot:" + size);
			int sizeLeft = 70000000 - rootNode.size;
			int minSizeToFree = 30000000 - sizeLeft;
			Node smalestOk = nodes.get(0);
			for (Node node : nodes) {
				if(node.size >= minSizeToFree && node.size < smalestOk.size) {
					smalestOk = node;
				}
				System.out.println(node.size);
			}
			System.out.println(smalestOk.size);


		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class Node {
		int size;
		Node parentNode;
		Map<String, Node> childNodes = new HashMap<>();
		Set<AbstractMap.SimpleImmutableEntry<String, Integer>> files = new HashSet<>();
		BufferedReader br;

		Node(Node parentNode, BufferedReader br) {
			this.parentNode = parentNode;
			this.br = br;
		}

		public int getNodes(List<Node> nodes) {
			nodes.add(this);
			for (Map.Entry<String, Node> entry : childNodes.entrySet()) {
				this.size += entry.getValue().getNodes(nodes);
			}
			for (SimpleImmutableEntry<String, Integer> entry : files) {
				this.size += entry.getValue();
			}
			return this.size;
		}

		public void parse() {
			try {
				String line;
				while ((line = br.readLine()) != null) {
					String[] split = line.split(" ");
					switch (split[0]) {
					case "$":
						parseCommand(split);
						break;
					case "dir":
						Node node = childNodes.get(split[1]);
						if (node == null) {
							childNodes.put(split[1], new Node(this, br));
						}
						break;
					default:
						files.add(new AbstractMap.SimpleImmutableEntry<>(split[1], Integer.valueOf((split[0]))));
						break;
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void parseCommand(String[] split) {
			switch (split[1]) {
			case "cd":
				parseArg(split[2]);
				break;
			case "ls":
				parse();
				break;
			default:
				break;
			}
		}

		private void parseArg(String arg) {
			switch (arg) {
			case "/":
				if(parentNode != null) {
					parentNode.parse();
				}
				break;
			case "..":
				if(parentNode != null) {
					parentNode.parse();
				}
				break;
			default:
				childNodes.get(arg).parse();
				break;
			}
		}
	}
}
