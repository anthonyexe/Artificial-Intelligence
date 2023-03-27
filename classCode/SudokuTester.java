//Anthony D'Alessandro
import java.util.*;
public class SudokuTester {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String fileName;
		
		System.out.println("Enter the file name for the selected Sudoku Puzzle: ");
		fileName = scan.nextLine();
		
		SudokuSolver solver = new SudokuSolver(fileName);
		//int[][] puzzleArray = solver.initializePuzzle();
		//List<String> testing = solver.initializePuzzle();
		/*
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				System.out.print(puzzleArray[i][j] + " ");
			}
			System.out.println();
		}*/
		//System.out.println(testing);
		solver.initializePuzzle();
		solver.availabilityCalc3D();
		solver.forwardPropagationCall();
		solver.solve();
	}

}