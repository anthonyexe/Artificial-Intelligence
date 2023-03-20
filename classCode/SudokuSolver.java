//Anthony D'Alessandro

import java.io.*;
import java.util.*;
public class SudokuSolver {
	private String fileName;
	private int[][] puzzle;
	private int zeroCount = 0;
	
	public SudokuSolver(String fName) {
		fileName = fName;
		puzzle = new int[9][9];
	}
	
	public void initializePuzzle() {
		try {
			FileReader fReader = new FileReader(fileName);
			BufferedReader bReader = new BufferedReader(fReader);
			String nextLine;
			int rows = 0;
			
			for (nextLine = bReader.readLine(); nextLine != null; nextLine = bReader.readLine(), rows++) {
				String[] lineValues = nextLine.split(" ");
				
				for (int i = 0; i < 9; i++) {
					puzzle[rows][i] = Integer.parseInt(lineValues[i]);
				}
				
			}
			for (int i = 0; i < 9; i++) {
				System.out.println();
				for (int j = 0; j < 9; j++) {
					System.out.print(puzzle[i][j] + " ");
					if (puzzle[i][j] == 0)
						zeroCount++;
				}
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void availabilityCalc() {
		Random rand = new Random();
		ArrayList<HashSet<Integer>> availabilityLists = new ArrayList<HashSet<Integer>>(81);
		for (int i = 0; i < zeroCount; i++) {
			availabilityLists.add(i, new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
		}
		for (int i = 0, count = 0; i < 9; i++, count++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0) {
					availabilityLists.get(count).remove(puzzle[i][j]);
					for (int k = 0; k < 9; k++) {
						if (puzzle[k][j] != 0) {
							availabilityLists.get(count).remove(puzzle[k][j]);
						}
						if (puzzle[i][k] != 0) {
							availabilityLists.get(count).remove(puzzle[i][k]);
						}
					}
					/*
					for (int r = 0; r < 9; r++) {
						if ((puzzle[i][r] != puzzle[i][j]) && (puzzle[i][r] != 0)) {
							availabilityLists.get(count).add(puzzle[i][r]);
						}
					} */
				}
			}
		}
		
		System.out.println(availabilityLists);
		System.out.println(availabilityLists.size());
	}
}