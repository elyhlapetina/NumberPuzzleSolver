import static org.junit.Assert.*;

import org.junit.Test;

//testing for the main breadth and depth searches of the main class...
//this also tests most getters and setters of GameBoard class, although it does have its own test class
//This also tests GBNode...
public class EECSProgrammingAssignment3Test {
	
	//tests a 3x3 matrix. Makes one shift, then solves it.
	@Test
	public void testSimpleMove() {
		
		System.out.println("test simple move");
		GameBoard testBoard = new GameBoard();
		try{
			testBoard.shiftDown();
		} catch(Exception e){

		}

		EECSProgrammingAssignment3 testa = new EECSProgrammingAssignment3(testBoard);
		EECSProgrammingAssignment3 testb  = new EECSProgrammingAssignment3(testBoard);

		System.out.println("Depth");
		testa.depthFirst();
		testa.printWiningGameMoves();


		System.out.println("Breadth");
		testb.breadthFirst();
		testb.printWiningGameMoves();
		System.out.println("end test");
		
		

	}


	//test a 3x3 matrix.
	//makes no moves, returns answers
	//this also tests a 1x1 board
	@Test
	public void testNoMove() {
		System.out.println("test no move");
		GameBoard testBoard = new GameBoard(8);

		EECSProgrammingAssignment3 testa = new EECSProgrammingAssignment3(testBoard);
		EECSProgrammingAssignment3 testb  = new EECSProgrammingAssignment3(testBoard);

		System.out.println("Depth");
		testa.depthFirst();
		testa.printWiningGameMoves();


		System.out.println("Breadth");
		testb.breadthFirst();
		testb.printWiningGameMoves();
		
		//1x1 board with different size
		EECSProgrammingAssignment3 testc = new EECSProgrammingAssignment3(1);
		EECSProgrammingAssignment3 testd  = new EECSProgrammingAssignment3(1);

		System.out.println("Depth");
		testc.depthFirst();
		testc.printWiningGameMoves();


		System.out.println("Breadth");
		testd.breadthFirst();
		testd.printWiningGameMoves();
		System.out.println("end test");

	}


	
	//tests a 3x3 matrix
	//makes multiple KNOWN moves, then solves it.
	
	@Test
	public void testMultipleMoves() {
		
		//In this case, depth should require many moves, breadth should not...
		System.out.println("test multiple moves");
		GameBoard testBoard = new GameBoard(8);

		try {
			testBoard.shiftRight();
			testBoard.shiftDown();
			
		} catch (Exception e) {
			
		
		}

		EECSProgrammingAssignment3 testa = new EECSProgrammingAssignment3(testBoard);
		EECSProgrammingAssignment3 testb  = new EECSProgrammingAssignment3(testBoard);


		System.out.println("Depth");
		testa.depthFirst();
		testa.printWiningGameMoves();


		System.out.println("Breadth");
		testb.breadthFirst();
		testb.printWiningGameMoves();
		System.out.println("end test");
		
		
		
		//In this case, breadth should require many moves, depth should not...
		System.out.println("test multiple move2");
		GameBoard testBoard1 = new GameBoard();
		try{
			testBoard1.shiftRight();
			testBoard1.shiftRight();
			
		} catch(Exception e){

		}
		testBoard1.printBoard();
		EECSProgrammingAssignment3 testc = new EECSProgrammingAssignment3(testBoard1);
		EECSProgrammingAssignment3 testd  = new EECSProgrammingAssignment3(testBoard1);

		System.out.println("Depth");
		testc.depthFirst();
		testc.printWiningGameMoves();


		System.out.println("Breadth");
		testd.breadthFirst();
		testd.printWiningGameMoves();
		System.out.println("end test");
		
		
		//in this case, both should take a decent amount of moves...
		System.out.println("test multiple move3");
		GameBoard testBoard2 = new GameBoard();
		try{
			testBoard2.shiftRight();
			testBoard2.shiftDown();
			
		} catch(Exception e){

		}
		testBoard2.printBoard();
		
		EECSProgrammingAssignment3 teste = new EECSProgrammingAssignment3(testBoard2);
		EECSProgrammingAssignment3 testf  = new EECSProgrammingAssignment3(testBoard2);

		System.out.println("Depth");
		teste.depthFirst();
		teste.printWiningGameMoves();


		System.out.println("Breadth");
		testf.breadthFirst();
		testf.printWiningGameMoves();
		System.out.println("end test");
		

	}

	//creates a fake tree with predefined nodes.
	//tests to see if node are in the tree or not.
	@Test
	public void testExistsInTree(){


		EECSProgrammingAssignment3 testTree = new EECSProgrammingAssignment3(new GameBoard());

		GameBoard testBoard = new GameBoard();
		GameBoard testBoard2 = new GameBoard(testBoard);
		GameBoard testBoard3 = new GameBoard(testBoard2);
		GameBoard testBoard4 = new GameBoard(testBoard3);
		GameBoard testBoard5 = new GameBoard(testBoard4);
		GameBoard testBoard6 = new GameBoard(testBoard5);
		try{

			testBoard.shiftDown();

			testBoard2.shiftDown();
			testBoard2.shiftDown();

			testBoard3.shiftDown();
			testBoard3.shiftDown();
			testBoard3.shiftRight();

			testBoard4.shiftDown();
			testBoard4.shiftDown();
			testBoard4.shiftRight();
			testBoard4.shiftRight();

			testBoard5.shiftDown();
			testBoard5.shiftDown();
			testBoard5.shiftRight();
			testBoard5.shiftRight();
			testBoard5.shiftUp();

			testBoard6.shiftDown();
			testBoard6.shiftDown();
			testBoard6.shiftRight();
			testBoard6.shiftRight();
			testBoard6.shiftUp();
			testBoard6.shiftUp();
		} catch(Exception e){

		}
		testTree.getHead().setDownMove( new GBNode(testBoard));
		testTree.getHead().getDownMove().setDownMove( new GBNode(testBoard6));
		testTree.getHead().setLeftMove( new GBNode(testBoard2));
		testTree.getHead().setRightMove ( new GBNode(testBoard3));
		testTree.getHead().setUpMove ( new GBNode(testBoard4));

		if(!testTree.foundGameState(testBoard)){
			fail("Should have found board in second node...");
		}

		if(!testTree.foundGameState(testBoard2)){
			fail("Should have found board in second node...");
		}
		if(!testTree.foundGameState(testBoard3)){
			fail("Should have found board in second node...");
		}
		if(!testTree.foundGameState(testBoard4)){
			fail("Should have found board in second node...");
		}
		if(!testTree.foundGameState(testBoard6)){
			fail("Should have found board in second node...");
		}
		if(testTree.foundGameState(testBoard5)){
			fail("Should not have found board in second node...");
		}


	}


}
