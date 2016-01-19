public class GBNode{
	
		//holds the actual gamebaord
		private GameBoard gb = null;
		
		//parent
		private GBNode parent = null;
		
		//node of left move
		private GBNode leftMove = null;
		
		//node of right move
		private GBNode rightMove = null;
		
		//node of up move
		private GBNode upMove = null;
		
		//node of down move
		private GBNode downMove = null;

		//creates node based on gameboard
		public GBNode(GameBoard gb){
			this.gb = gb;
		}

		//sets the parent
		public void setParent(GBNode parent){
			this.parent = parent;
		}
		
		//returns parent
		public GBNode getParent(){
			return this.parent;
		}
		
		//returns gameboard
		public GameBoard getGameBoard(){
			return this.gb;
		}
		
		//returns left node
		public GBNode getLeftMove(){
			return this.leftMove;
		}
		
		//sets left node
		public void setLeftMove(GBNode left){
			this.leftMove = left;
		}
		
		//returns right node
		public GBNode getRightMove(){
			return this.rightMove;
		}
		
		//sets right node
		public void setRightMove(GBNode right){
			this.rightMove = right;
		}
		
		//returns up move
		public GBNode getUpMove(){
			return this.upMove;
		}
		
		
		//sets up move
		public void setUpMove(GBNode up){
			this.upMove = up;
		}

		//returns down move
		public GBNode getDownMove(){
			return this.downMove;
		}
		
		
		//sets up move
		public void setDownMove(GBNode down){
			this.downMove = down;
		}
	}