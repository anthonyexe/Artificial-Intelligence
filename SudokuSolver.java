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
			
			for (nextLine = bReader.readLine(); (nextLine != null && rows < 9); nextLine = bReader.readLine(), rows++) {
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
		for (int i = 0, count = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0) {
					for (int k = 0; k < 9; k++) {
						if (puzzle[k][j] != 0) {
							availabilityLists.get(count).remove(puzzle[k][j]);
						}
						if (puzzle[i][k] != 0) {
							availabilityLists.get(count).remove(puzzle[i][k]);
						}
					}
					if (i <= 2 && j <= 2) {
						
					}
					else if ((i <= 2) && (j >= 3 && j <= 5)) {
						
					}
					else if ((i <= 2) && (j >= 6 && j <= 8)) {
						
					}
					else if ((i >= 3 && i <= 5) && j <= 2) {
						
					}
					else if ((i >= 3 && i <= 5) && (j >= 3 && j <= 5)) {
						
					}
					else if ((i >= 3 && i <= 5) && (j >= 6 && j <= 8)) {
						
					}
					else if ((i >= 6 && i <= 8) && j <= 2) {
						
					}
					else if ((i >= 6 && i <= 8) && (j >= 3 && j <= 5)) {
						
					}
					else {
						
					}
					count++;
				}
			}
		}
		System.out.println();
		System.out.println(availabilityLists);
		System.out.println(availabilityLists.size());
	}
}
