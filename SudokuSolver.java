//Anthony D'Alessandro

import java.io.*;
import java.util.*;
public class SudokuSolver {
	private String fileName;
	private int[][] puzzle;
	private ArrayList<ArrayList<ArrayList<Integer>>> availabilityLists = new ArrayList<ArrayList<ArrayList<Integer>>>(81);
	
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
			for (int i = 0, count = 0; i < 9; i++) {
				System.out.println();
				for (int j = 0; j < 9; j++) {
					System.out.print(puzzle[i][j] + " ");
					if (puzzle[i][j] == 0) {
						availabilityLists.add(new ArrayList<ArrayList<Integer>>());
						availabilityLists.get(count).add(new ArrayList<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
						availabilityLists.get(count).add(new ArrayList<Integer>(Arrays.asList(i, j)));
					}
					else {
						availabilityLists.add(new ArrayList<ArrayList<Integer>>());
						availabilityLists.get(count).add(new ArrayList<Integer>());
					}
					count++;
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
		ArrayList<HashSet<Integer>> availabilityLists = new ArrayList<HashSet<Integer>>(81);
		/*for (int i = 0; i < zeroCount; i++) {
			availabilityLists.add(i, new HashSet<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9)));
		}*/
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
						for (int r = 0; r <= 2; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
						
					}
					else if ((i <= 2) && (j >= 3 && j <= 5)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else if ((i <= 2) && (j >= 5 && j <= 7)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 5; c <= 7; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else if ((i >= 3 && i <= 5) && j <= 2) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else if ((i >= 3 && i <= 5) && (j >= 3 && j <= 5)) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else if ((i >= 3 && i <= 5) && (j >= 6 && j <= 8)) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else if ((i >= 6 && i <= 8) && j <= 2) {
						for (int r = 6; r <= 8; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else if ((i >= 6 && i <= 8) && (j >= 3 && j <= 5)) {
						for (int r = 6; r <= 8; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					else {
						for (int r = 6; r <= 8; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).remove(puzzle[r][c]);
							}
						}
					}
					count++;
				}
			}
		}
		System.out.println();
		System.out.println(availabilityLists);
		System.out.println(availabilityLists.size());
	}
	
	public void availabilityCalc3D() {
		for (int i = 0, count = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0) {
					for (int k = 0; k < 9; k++) {
						if (puzzle[k][j] != 0) {
							availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[k][j]));
						}
						if (puzzle[i][k] != 0) {
							availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[i][k]));
						}
					}
					if (i <= 2 && j <= 2) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
						
					}
					else if ((i <= 2) && (j >= 3 && j <= 5)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else if ((i <= 2) && (j >= 5 && j <= 7)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 5; c <= 7; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else if ((i >= 3 && i <= 5) && j <= 2) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else if ((i >= 3 && i <= 5) && (j >= 3 && j <= 5)) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else if ((i >= 3 && i <= 5) && (j >= 6 && j <= 8)) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else if ((i >= 6 && i <= 8) && j <= 2) {
						for (int r = 6; r <= 8; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else if ((i >= 6 && i <= 8) && (j >= 3 && j <= 5)) {
						for (int r = 6; r <= 8; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
					else {
						for (int r = 6; r <= 8; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] != 0)
									availabilityLists.get(count).get(0).remove(Integer.valueOf(puzzle[r][c]));
							}
						}
					}
				}
				count++;
			}
		}
		System.out.println();
		System.out.println(availabilityLists);
		System.out.println(availabilityLists.size());
	}
	
	public void forwardPropagation() {
		for (int i = 0, count = 0; i < 9; i++) {
			System.out.println();
			for (int j = 0; j < 9; j++) {
				if (availabilityLists.get(count).get(0).size() == 1) {
					int row = availabilityLists.get(count).get(1).get(0);
					int col = availabilityLists.get(count).get(1).get(1);
					puzzle[row][col] = availabilityLists.get(count).get(0).get(0);
					System.out.print(puzzle[i][j] + " ");
				}
			}
		}
		availabilityCalc3D();
	}
}
