import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class EECSProgrammingAssignment3 {

	//Queue class that is used for tree traversals
	private class SearchQueue<GBNode> extends LinkedList<GBNode> implements Queue<GBNode>{
	}

	//start of tree after randomization
	private GBNode head = null;
	
	//end of tree, once goal state is found
	private GBNode winningNode = null;
	
	//maximum iterations
	private int maxIterations = 6000;
	
	//counter for current iterations
	private int currentIterations = 0;;

	//sets head node based on desired game size
	public EECSProgrammingAssignment3(int gameSize){
		head = new GBNode(new GameBoard(gameSize));
	}
	
	//sets head node based on specific node
	public EECSProgrammingAssignment3(GameBoard inputGame){
		head = new GBNode(inputGame);
	}

	//sets head node based on desired game size and randomized moves
	public EECSProgrammingAssignment3(int gameSize, int moves){
		head = new GBNode(new GameBoard(gameSize, moves));

	}


	//sets head node based on desired game size and randomized moves
	public EECSProgrammingAssignment3(int[] inArray){
		head = new GBNode(new GameBoard(inArray));

	}
	
	//sets head node based on desired game size and randomized moves
	public EECSProgrammingAssignment3(int[] inArray, int moves){
		head = new GBNode(new GameBoard(inArray, moves));

	}
	
	//returns head of tree
	public GBNode getHead(){
		return this.head;
	}
	
	//performs level order traversal in order to check if game state has been reached already.
	public boolean foundGameState(GameBoard checkingBoard){
		
		//tracks current node
		SearchQueue<GBNode> nodeStack = new SearchQueue<GBNode>();
		nodeStack.add(head);

		do{
			GBNode currentNode = nodeStack.pop();
			//checks to see if the current node has a possible move on the left,right,up or down, places this node on the stack.
			//at each stage, it checks to see if 
			if(currentNode.getLeftMove() != null)
				nodeStack.add(currentNode.getLeftMove());
			if(currentNode.getRightMove() != null)
				nodeStack.add(currentNode.getRightMove());
			if(currentNode.getUpMove() != null)
				nodeStack.add(currentNode.getUpMove());
			if(currentNode.getDownMove() != null)
				nodeStack.add(currentNode.getDownMove());

			if(checkingBoard.checkMatch(currentNode.getGameBoard())){
				return true;
			}

		} while(!nodeStack.isEmpty());

		return false;
	}

	//************************//
	//this method performs level order traversal of tree for breadthFirst
	//each visited node, if possible and the game state hasn't been already reached, will create a new node
	public void breadthFirst(){

		//stack to track current node order for breadth first
		SearchQueue<GBNode> nodeStack = new SearchQueue<GBNode>();

		//initial node for queue
		nodeStack.add(head);

		do{
			GBNode currentNode = nodeStack.pop();
			//edge case if first board is winning move
			if(currentNode.getGameBoard().isWinningMove()){
				winningNode = currentNode;
			}
			//tries to create a game board that is shifted left.
			try{
				
				//duplicates board
				GameBoard newBoard = new GameBoard(currentNode.getGameBoard());
				newBoard.shiftLeft();
				//checks if game is winning move
				if(!newBoard.isWinningMove()){
					//checks if board is found already
					if(foundGameState(newBoard) == false){
						currentNode.setLeftMove (new GBNode(newBoard));
						currentNode.getLeftMove().setParent ( currentNode );
						nodeStack.add(currentNode.getLeftMove());
						currentIterations++;
					}
				} else {
					//executes if game state hit
					System.out.println("game state found!");
					winningNode = new GBNode(newBoard);
					winningNode.setParent( currentNode);
				}

			} catch(Exception e){

			}
			//tries to create a game board that is shift right
			try{
				GameBoard newBoard = new GameBoard(currentNode.getGameBoard());
				newBoard.shiftRight();
				//checks if we found winning move
				if(!newBoard.isWinningMove()){
					//checks if game board exists
					if(foundGameState(newBoard) == false){
						currentNode.setRightMove( new GBNode(newBoard));
						currentNode.getRightMove().setParent(currentNode);
						nodeStack.add(currentNode.getRightMove());
						currentIterations++;
					}
				} else {
					//executes if game hit
					System.out.println("game state found!");
					winningNode = new GBNode(newBoard);
					winningNode.setParent( currentNode );
				}
			} catch(Exception e){

			}

			//tries to create a game board that is shifted up.
			try{
				GameBoard newBoard = new GameBoard(currentNode.getGameBoard());
				newBoard.shiftUp();
				//checks if winning move hit
				if(!newBoard.isWinningMove()){
					//checks if game state hit already
					if(foundGameState(newBoard) == false){
						currentNode.setUpMove( new GBNode(newBoard));
						currentNode.getUpMove().setParent( currentNode );
						nodeStack.add(currentNode.getUpMove());
						currentIterations++;
					}
				} else {
					//executes if winning move found
					System.out.println("game state found!");
					winningNode = new GBNode(newBoard);
					winningNode.setParent( currentNode );
				}
			} catch(Exception e){

			}

			//tried to create a game board that is shift down.
			try{
				GameBoard newBoard = new GameBoard(currentNode.getGameBoard());
				newBoard.shiftDown();
				//checks if winning move
				if(!newBoard.isWinningMove()){
					//checks if game state found
					if(foundGameState(newBoard) == false){
						currentNode.setDownMove( new GBNode(newBoard));
						currentNode.getDownMove().setParent(currentNode);
						nodeStack.add(currentNode.getDownMove());
						currentIterations++;
					}		
				} else {
					//executes if winning move
					System.out.println("game state found!");
					winningNode = new GBNode(newBoard);
					winningNode.setParent( currentNode);
				}
			} catch(Exception e){

			}
		} while(!nodeStack.isEmpty() &&  currentIterations < maxIterations && winningNode == null);

	}

	//************************//
	//helper method for depth first.
	public void depthFirst(){
		depthFirst(head);
	}

	//depthFirst search to find goal state
	public void depthFirst(GBNode inNode){
 
		//checks to see if input node is the goal state, return it to field
		if(inNode.getGameBoard().isWinningMove()){
			System.out.println("Game State Found!");
			winningNode = inNode;
		} else {
			//attempts to move space left, does nothing if shift throws null pointer.
			//only executes if there is not winning node found
			if(inNode.getLeftMove()==null && currentIterations < maxIterations && winningNode == null){
				try{
					
					GameBoard newBoard = new GameBoard(inNode.getGameBoard());
					newBoard.shiftLeft();
					//checks if game state was previously hit
					if(foundGameState(newBoard) == false){
						inNode.setLeftMove( new GBNode(newBoard));
						inNode.getLeftMove().setParent( inNode );
						//recursive call on left
						currentIterations++;
						depthFirst(inNode.getLeftMove());
					}
				} catch(Exception E){

				} 
			}

			//attempts to move space right, does nothing if shift throws null pointer.
			//only executes if there is not winning node found
			if(inNode.getRightMove()==null && currentIterations < maxIterations  && winningNode == null){
				try{
					GameBoard newBoard = new GameBoard(inNode.getGameBoard());
					newBoard.shiftRight();
					//checks if game state was previously hit
					if(foundGameState(newBoard) == false){
						inNode.setRightMove( new GBNode(newBoard));
						inNode.getRightMove().setParent( inNode );
						//recursive call on right
						currentIterations++;
						depthFirst(inNode.getRightMove());
						
					}
					
				} catch(Exception E){

				} 
			}

			//attempts to move space right, does nothing if shift throws null pointer.
			//only executes if there is not winning node found
			if(inNode.getUpMove()==null && currentIterations < maxIterations  && winningNode == null){
				try{
					GameBoard newBoard = new GameBoard(inNode.getGameBoard());
					newBoard.shiftUp();
					//checks if game state was already present
					if(foundGameState(newBoard) == false){
						inNode.setUpMove( new GBNode(newBoard));
						inNode.getUpMove().setParent( inNode );
						//recursive call on up
						currentIterations++;
						depthFirst(inNode.getUpMove());
					}
					
				} catch(Exception E){

				} 
			}

			//attempts to move space right, does nothing if shift throws null pointer.
			//only executes if there is not winning node found
			if(inNode.getDownMove()==null && currentIterations < maxIterations  && winningNode == null){
				try{
					GameBoard newBoard = new GameBoard(inNode.getGameBoard());
					newBoard.shiftDown();
					//checks if game state was already present
					if(foundGameState(newBoard) == false){
						inNode.setDownMove(new GBNode(newBoard));
						inNode.getDownMove().setParent( inNode );
						//recursive call on down
						currentIterations++;
						depthFirst(inNode.getDownMove());
					}
					
				} catch(Exception E){

				} 
			}
		}
	}

	//************************//
	//prints moves from starting state to goal state
	public void printWiningGameMoves(){

		//tracks current move number
		int currentMoveNumber = 1;

		//checks if a final node was found
		if(winningNode != null){

			//creates stack to store moves
			Stack<GBNode> gameOrder = new Stack<GBNode>();

			//stores last node 
			GBNode currentNode = winningNode;

			gameOrder.add(currentNode);

			//adds each move, by moving up the tree from either traversal
			while(currentNode.getParent() != null){

				gameOrder.add(currentNode.getParent());
				currentNode = currentNode.getParent();

			}

			//prints out each node of the stack to get the answer
			while(!gameOrder.isEmpty()){

				System.out.println("Game move #" + currentMoveNumber);
				gameOrder.pop().getGameBoard().printBoard();
				currentMoveNumber++;
			}

		} else {

			System.out.println("No final state found within memory constraint!");
		}

	}
	
	public static void main(String args[]){
		
		/**Change code here!!!!*/
		int gameSize = 8;
		int randomMoves = 5;
		//another possible way to start
		//EECSProgrammingAssignment3 newGameBreadth = new EECSProgrammingAssignment3(new int[] {0,1,2,3,4,5,6,7,8,9});
		
		
		//Creates two identical boards, one to be solved via breadth and one to solved via depth
		EECSProgrammingAssignment3 newGameBreadth = new EECSProgrammingAssignment3(gameSize,randomMoves);
		EECSProgrammingAssignment3 newGameDepth = new EECSProgrammingAssignment3(newGameBreadth.getHead().getGameBoard());
		System.out.println("Game Board");
		newGameBreadth.getHead().getGameBoard().printBoard();
		
		//Depth first solution
		System.out.println("Depth First Solution: ");
		newGameDepth.depthFirst();
		newGameDepth.printWiningGameMoves();
		System.out.println("End of Search");
		System.out.println("");
		
		//Breadth first solution
		System.out.println("Breadth first solution: ");
		newGameBreadth.breadthFirst();
		newGameBreadth.printWiningGameMoves();
		System.out.println("End of Search");
		System.out.println("");
		
		
	}
	
}
