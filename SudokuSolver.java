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
						availabilityLists.get(count).add(new ArrayList<Integer>(Arrays.asList(i, j)));
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
	
	public void printSudoku() {
		for (int i = 0; i < 9; i++) {
			System.out.println();
			for (int j = 0; j < 9; j++) {
				System.out.print(puzzle[i][j] + " ");
			}
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
					else if ((i <= 2) && (j >= 6 && j <= 8)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 6; c <= 8; c++) {
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
					else if ((i >= 6 && i <= 8) && (j >= 6 && j <= 8)){
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
	}
	
	public boolean forwardPropagation() {
		boolean singleValueRemaining = false;
		
			for (int i = 0, count = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					if (availabilityLists.get(count).get(0).size() == 1) {
						singleValueRemaining = true;
						int value = availabilityLists.get(count).get(0).get(0);
						puzzle[i][j] = value;
						availabilityLists.get(count).get(0).clear();
					}
					count++;
				}
			}
			availabilityCalc3D();
			
			return singleValueRemaining;
	}
	
	public void forwardPropagationCall() {
		boolean bool = forwardPropagation();
		
		do
			bool = forwardPropagation();
		while (bool);
	}
	
	public boolean checkDuplicates(int row, int col, int value) {
		boolean duplicates = false;
		
		for (int k = 0; k < 9; k++) {
			if (row != k && puzzle[k][col] == value) {
				duplicates = true;
			}
			if (col != k && puzzle[row][k] == value) {
				duplicates = true;
			}
		}
		if (row <= 2 && col <= 2) {
			for (int r = 0; r <= 2; r++) {
				for (int c = 0; c <= 2; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
			
		}
		else if ((row <= 2) && (col >= 3 && col <= 5)) {
			for (int r = 0; r <= 2; r++) {
				for (int c = 3; c <= 5; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else if ((row <= 2) && (col >= 6 && col <= 8)) {
			for (int r = 0; r <= 2; r++) {
				for (int c = 6; c <= 8; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else if ((row >= 3 && row <= 5) && col <= 2) {
			for (int r = 3; r <= 5; r++) {
				for (int c = 0; c <= 2; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else if ((row >= 3 && row <= 5) && (col >= 3 && col <= 5)) {
			for (int r = 3; r <= 5; r++) {
				for (int c = 3; c <= 5; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else if ((row >= 3 && row <= 5) && (col >= 6 && col <= 8)) {
			for (int r = 3; r <= 5; r++) {
				for (int c = 6; c <= 8; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else if ((row >= 6 && row <= 8) && col <= 2) {
			for (int r = 6; r <= 8; r++) {
				for (int c = 0; c <= 2; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else if ((row >= 6 && row <= 8) && (col >= 3 && col <= 5)) {
			for (int r = 6; r <= 8; r++) {
				for (int c = 3; c <= 5; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		else {
			for (int r = 6; r <= 8; r++) {
				for (int c = 6; c <= 8; c++) {
					if ((row != r && col != c) && puzzle[r][c] == value) {
						duplicates = true;
					}
				}
			}
		}
		
		return duplicates;
	}
	
	public void removeDuplicates(int row, int col, int value) {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] != puzzle[row][col]) {
					for (int k = 0; k < 9; k++) {
						if (puzzle[k][j] == value) {
							puzzle[k][j] = 0;
						}
						if (puzzle[i][k] == value) {
							puzzle[i][k] = 0;
						}
					}
					if (row <= 2 && col <= 2) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
						
					}
					else if ((row <= 2) && (col >= 3 && col <= 5)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else if ((row <= 2) && (col >= 6 && col <= 8)) {
						for (int r = 0; r <= 2; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else if ((row >= 3 && row <= 5) && col <= 2) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else if ((row >= 3 && row <= 5) && (col >= 3 && col <= 5)) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else if ((row >= 3 && row <= 5) && (col >= 6 && col <= 8)) {
						for (int r = 3; r <= 5; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else if ((row >= 6 && row <= 8) && col <= 2) {
						for (int r = 6; r <= 8; r++) {
							for (int c = 0; c <= 2; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else if ((row >= 6 && row <= 8) && (col >= 3 && col <= 5)) {
						for (int r = 6; r <= 8; r++) {
							for (int c = 3; c <= 5; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
					else {
						for (int r = 6; r <= 8; r++) {
							for (int c = 6; c <= 8; c++) {
								if (puzzle[r][c] == value) {
									puzzle[r][c] = 0;
								}
							}
						}
					}
				}
			}
		}
		puzzle[row][col] = 0;
		availabilityCalc3D();
	}
	
	public ArrayList<ArrayList<Integer>> leastRemaining(int num) {
		ArrayList<ArrayList<Integer>> leastValues = new ArrayList<ArrayList<Integer>>();
		leastValues.add(new ArrayList<Integer>());
		leastValues.add(new ArrayList<Integer>());
		for (int i = 0; i < availabilityLists.size(); i++) {
			if (availabilityLists.get(i).get(0).size() == num) {
				for (int j = 0; j < num; j++) {
					leastValues.get(0).add(availabilityLists.get(i).get(0).get(j));
				}
				leastValues.get(1).add(availabilityLists.get(i).get(1).get(0));
				leastValues.get(1).add(availabilityLists.get(i).get(1).get(1));
				//leastValues = availabilityLists.get(i);
				break;
			}
		}
		return leastValues;
	}
	
	public ArrayList<ArrayList<ArrayList<Integer>>> copyAvailabilityList() {
		ArrayList<ArrayList<ArrayList<Integer>>> copy = new ArrayList<ArrayList<ArrayList<Integer>>>(81);
		for (int i = 0; i < availabilityLists.size(); i++) {
			copy.add(new ArrayList<ArrayList<Integer>>());
			copy.get(i).add(new ArrayList<Integer>());
			copy.get(i).add(new ArrayList<Integer>());
			for (int j = 0; j < availabilityLists.get(i).get(0).size(); j++) {
				copy.get(i).get(0).add(availabilityLists.get(i).get(0).get(j));
			}
			copy.get(i).get(1).add(availabilityLists.get(i).get(1).get(0));
			copy.get(i).get(1).add(availabilityLists.get(i).get(1).get(1));
		}
		return copy;
	}
	
	public ArrayList<ArrayList<ArrayList<Integer>>> leastRemainingSet(int num) {
		ArrayList<ArrayList<ArrayList<Integer>>> set = new ArrayList<ArrayList<ArrayList<Integer>>>();
		for (int i = 0; i < availabilityLists.size(); i++) {
			if (availabilityLists.get(i).get(0).size() == num) {
				set.add(availabilityLists.get(i));
			}
		}
		return set;
	}
	
	public boolean remainingValuesSize(int size) {
		boolean result = false;
		for (int i = 0; i < availabilityLists.size(); i++) {
			if (availabilityLists.get(i).get(0).size() == size) {
				result = true;
			}
		}
		return result;
	}
	
	public int remainingValuesCount(int size, ArrayList<ArrayList<ArrayList<Integer>>> list) {
		int count = 0;
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).get(0).size() == size)
				count++;
		}
		return count;
	}
	
	public boolean remainingZeros() {
		boolean zeros = false;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0)
					zeros = true;
			}
		}
		return zeros;
	}
	//Returns true if the puzzle is complete
	public boolean checkPuzzle() {
		boolean result = true;
		if (!remainingZeros()) {
			for (int i = 0; i < 9; i++) {
				for (int j = 0; j < 9; j++) {
					int value = puzzle[i][j];
					if (checkDuplicates(i, j, value))
						result = false;
				}
			}
		}
		else
			result = false;
		return result;
	}
	//Returns true if there are no duplicates in the entire puzzle even if it is not finished
	public boolean checkPuzzleWithZeros() {
		boolean result = true;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] != 0) {
					int value = puzzle[i][j];
					if (checkDuplicates(i, j, value))
						result = false;
				}
			}
		}
		return true;
	}
	
	public int zeroCount() {
		int zeros = 0;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (puzzle[i][j] == 0) {
					zeros++;
				}
			}
		}
		return zeros;
	}
	
	public boolean testing3() {
		for (int i = 1; i <= 9; i++) {
			for (int j = 0; j < availabilityLists.size(); j++) {
				if (availabilityLists.get(j).get(0).size() == i) {
					int row = availabilityLists.get(j).get(1).get(0);
					int col = availabilityLists.get(j).get(1).get(1);
					for (int k = 0; k < i; k++) {
						int value = availabilityLists.get(j).get(0).get(k);
						if (!checkDuplicates(row, col, value)) {
							puzzle[row][col] = value;
							availabilityLists.get(j).get(0).clear();
							availabilityCalc3D();
							if (testing3()) {
								return true;
							}
							else {
								puzzle[row][col] = 0;
								availabilityLists.get(j).get(0).remove(value);
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	public boolean testing2() {
		boolean result = false;
		
		for (int i = 2; i < 9; i++) {
			for (int j = 0; j < availabilityLists.size(); j++) {
				if (availabilityLists.get(j).get(0).size() == i) {
					int row = availabilityLists.get(j).get(1).get(0);
					int col = availabilityLists.get(j).get(1).get(1);
					for (int k = 0; k < i; k++) {
						int value = availabilityLists.get(j).get(0).get(k);
						if (puzzle[row][col] == 0 && !checkDuplicates(row, col, value)) {
							puzzle[row][col] = value;
							availabilityLists.get(j).get(0).clear();
							availabilityCalc3D();
							//forwardPropagation();
							break;
						}
						else if (checkDuplicates(row, col, value) && k != i - 1) {
							continue;
						}
						else if (checkDuplicates(row, col, value) && k == i - 1) {
							continue;
						}
						else if (checkPuzzle()) {
							result = true;
						}
					}
				}
			}
		}
		return result;
	}
	
	public void testing() {
		for (int i = 2; i < 9; i++) {
			ArrayList<ArrayList<ArrayList<Integer>>> copy = new ArrayList<ArrayList<ArrayList<Integer>>>();
			copy = copyAvailabilityList();
			//for (int j = 0; j < remainingValuesCount(i, copy); j++) {
				for (int k = 0; k < copy.size(); k++) {
					if (copy.get(k).get(0).size() == i) {
						System.out.println(copy.get(k));
						int row = copy.get(k).get(1).get(0);
						int col = copy.get(k).get(1).get(1);
						for (int v = 0; v < i; v++) {
							int value = copy.get(k).get(0).get(v);
							System.out.println(value);
							puzzle[row][col] = value;
							if (!checkDuplicates(row, col, value)) {
								
							}
						}
					}
				}
			//}
		}
	}
	
	public boolean DFS() {
		//ArrayList<ArrayList<Integer>> leastValues = new ArrayList<ArrayList<Integer>>();
		boolean result = false;
		
		if (checkPuzzle()) {
			result = true;
			return result;
		} 
		/*
		ArrayList<ArrayList<ArrayList<Integer>>> copy = new ArrayList<ArrayList<ArrayList<Integer>>>(81);
		copy = copyAvailabilityList();
			for (int i = 2; i <= 9; i++) {
				
				//System.out.println(copy);
					for (int k = 0; k < copy.size(); k++) {
						if (copy.get(k).get(0).size() == i) {
							int row = copy.get(k).get(1).get(0);
							int col = copy.get(k).get(1).get(1);
							//int index = (9 * row) + col;
							for (int v = 0; v < i; v++) {
								int value = copy.get(k).get(0).get(v);
								puzzle[row][col] = value;
								if (checkPuzzle()) {
									//copy.get(k).get(0).clear();
									result = true;
									return result;
								}
								else if (checkDuplicates(row, col, value)) {
									puzzle[row][col] = 0;
									DFS();
								}
							}
						}
					}
			}
			*/
		//return result;
		
		
		for (int i = 2; i < 9; i++) {
			ArrayList<ArrayList<ArrayList<Integer>>> leastValuesSet = new ArrayList<ArrayList<ArrayList<Integer>>>();
			leastValuesSet = leastRemainingSet(i);
			System.out.println(leastValuesSet);
			if (leastValuesSet.size() > 0) {
				for (int j = 0; j < leastValuesSet.size(); j++) {
					int row = leastValuesSet.get(j).get(1).get(0);
					int col = leastValuesSet.get(j).get(1).get(1);
					for (int k = 0; k < i; k++) {
						int value = leastValuesSet.get(j).get(0).get(k);
						puzzle[row][col] = value;
						if (checkPuzzle()) {
							result = true;
							return result;
						}
						else if (!checkDuplicates(row, col, value)) {
							int index = (9 * row) + col;
							availabilityLists.get(index).get(0).clear();
							availabilityCalc3D();
						}
						else {
							puzzle[row][col] = 0;
						}
					}
				}
			}
		}
		return result;
		/*
		if (checkPuzzle())
			return true;
		else
			return false;*/
	}
	
	public void solve() {
		
		System.out.println("---------------------------------------------------------------------");
		availabilityCalc3D();
		forwardPropagationCall();
		testing3();
		printSudoku();
		System.out.println();
		System.out.println(checkPuzzle());
		System.out.println(checkPuzzleWithZeros());
		System.out.println(zeroCount());
		
	}
}
