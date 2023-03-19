//Anthony D'Alessandro

import java.io.*;
import java.util.*;
public class SudokuSolver {
	private String fileName;
	static int[][] puzzle;
	
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
				}
			}
			bReader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
