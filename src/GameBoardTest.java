import static org.junit.Assert.*;

import org.junit.Test;


public class GameBoardTest {

	@Test //Visual test to see if constructor constructs correct gameboard.
	public void testConstructor() {

		System.out.println("testConStructorStart");
		//testing constructor for nxn with N being 8...
		GameBoard test = new GameBoard(8);
		test.printBoard();

		//testing input array constructor with incorrect array size.
		GameBoard test2 = new GameBoard(new int[]{0,1,3,2,4,5,6,7,8,9,10,11,12,13,14,15,16,17});
		test2.printBoard();

		//testing input array constructor with correct array size
		GameBoard test5 = new GameBoard(new int[]{0,1,3,2,4,5,6,7,8});
		test5.printBoard();

		//testing constructor by passing another board
		GameBoard test3 = new GameBoard(test);
		test3.printBoard();

		//testing nxn with randomized moves
		GameBoard test4 = new GameBoard(4,4);
		test4.printBoard();
		System.out.println("test end");
	}

	@Test //creates boards and checks each exit point of the method. 
	//In the first test we check if the are the same, should return true.
	//In the second test we check if they are different, should return false.
	public void testWinChecker() {

		//testing a win
		GameBoard test = new GameBoard(8);
		if(!test.isWinningMove()){
			fail("Not a winning move...");
		}

		//testing a non win
		GameBoard test2 = new GameBoard(new int[]{0,1,3,2,4,5,6,7,8});
		if(test2.isWinningMove()){
			fail("Should be a winning move...");
		}
	}



	@Test  //checks to see if there is a winning match based on winning config field
	public void testMatchChecker() {
		GameBoard test = new GameBoard(new int[]{1,0,3,2,4,5,6,7,8});
		test.printBoard();

		//testing a match
		GameBoard test2 = new GameBoard(new int[]{1,0,3,2,4,5,6,7,8});
		test2.printBoard();
		if(!test2.checkMatch(test)){
			fail("should a match");
		}

		//testing a non match
		GameBoard test3 = new GameBoard(new int[]{1,0,4,2,3,5,6,7,8});
		if(test3.checkMatch(test)){
			fail("not a match");
		}
	}



	@Test //tests each shift method then compares to what the correct result should be
	public void testShifting() {
		GameBoard test = new GameBoard();
		//test shift right
		try{
			test.shiftRight();
		} catch(Exception e){

		}
		if(!test.checkMatch(new GameBoard(new int[]{1,0,2,3,4,5,6,7,8}))){
			fail("should match");
		}

		//test shift left
		try{
			test.shiftLeft();
		} catch(Exception e){
		}
		if(!test.checkMatch(new GameBoard())){
			fail("should match");
		}

		//test shift down
		try{
			test.shiftDown();
		} catch(Exception e){
		}
		if(!test.checkMatch(new GameBoard(new int[]{3,1,2,0,4,5,6,7,8}))){
			fail("should match");
		}
		//test shift up
		try{
			test.shiftUp();
		} catch(Exception e){
		}

		if(!test.checkMatch(new GameBoard())){
			fail("should match");
		}
		//Shift on an edge.
		try{
			test.shiftUp();
		} catch(Exception e){
		}

		if(!test.checkMatch(new GameBoard())){
			fail("should match");
		}
	}
}
