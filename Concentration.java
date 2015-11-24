// Team Blah -- Shamaul Dilmohamed, Shanjeed Ali
// APCS1 pd10
// HW36 -- Some Folks Call it a Memory
// 2015-11-24

import cs1.Keyboard;  //if comfortable with Scanner, you may comment this out
		
public class Concentration {

    //instance variables
    private Tile[][] _board = new Tile[_numRows][4];     //storage for 4x4 grid of Tiles.
    private int _numberFaceUp = 0;   //count of Tiles with faces visible
    private String[] _words = {"boo", "doo", "moo", "noo", "poo", "loo", "hoo", "woo"};     //list of unique Strings used for Tile vals
    private static int _numRows = 4;
    

    //insert constructor and methods here
    public Concentration() {
	populate();
	scramble();
	System.out.println("A new game has begun!");
    }
    
    public void populate() {
	int wordctr = 0;
	for (int ctrR = 0; ctrR < _numRows; ctrR++) {
	    for (int ctrC = 0; ctrC < 4; ctrC++) {
		_board[ctrR][ctrC] = new Tile(_words[wordctr / 2]);
		wordctr += 1;
	    }
	}
    }

    public void scramble() {
	for (int ctrR = 0; ctrR < _numRows; ctrR++) {
	    for (int ctrC = 0; ctrC < 4; ctrC++) {
		int temp1 = (int)(Math.random() * 4);
		int temp2 = (int)(Math.random() * 4);
		Tile temp = _board[temp1][temp2];
		_board[temp1][temp2] = _board[ctrR][ctrC];
		_board[ctrR][ctrC] = temp;
	    }
	}
    }

    public String printT(Tile[][] s) {
	String ans = "";
	int row = 0;
	for (Tile[] t : s) {
	    ans += row;
	    for (Tile q : t) {
		ans += "\t" + q;
	    }
	    ans += "\n";
	    row += 1;
	}
	return ans;
    }
    
    public int count() {
	int up = 0;
	for (Tile[] t : _board) {
	    for (Tile q : t) {
		if (q.isFaceUp()) {
		    up += 1;
		}
	    }
	}
	return up;
    }

    public void turn() {
	int r1 = -1;
	int c1 = -1;
	int r2 = -1;
	int c2 = -1;
	System.out.println("  \t0\t1\t2\t3");
	System.out.println(printT(_board));
	System.out.println("What is the row (up and down) number, starting from zero, of the first tile you want to flip?"); //such concise instructions
	try {
	    r1 = Integer.parseInt(Keyboard.readString());
	}
	catch (Exception e) {
	    System.out.println("Invalid input, returning to beginning of turn.");
	    return;
	}
        System.out.println("What is the column (left and right) number, starting from zero, that it is located in?");
	try {
	    c1 = Integer.parseInt(Keyboard.readString());
	}
	catch (Exception e) {
	    System.out.println("Invalid input, returning to beginning of turn.");
	    return;
	}
	System.out.println("Flipping tile...");
	try {
	    _board[r1][c1].flip();
	}
	catch (Exception e) {
	    System.out.println("Not a chance. Beginning of turn.");
	    return;
	}
	System.out.println("  \t0\t1\t2\t3");
	System.out.println(printT(_board));
        System.out.println("What is the row (up and down) number, starting from zero, of the second tile you want to flip?");
	try {
	    r2 = Integer.parseInt(Keyboard.readString());
	}
	catch (Exception e) {
	    System.out.println("Invalid input, returning to beginning of turn.");
	    _board[r1][c1].flip();
	    return;
	}
        System.out.println("What is the column (left and right) number, starting from zero, that it is located in?");
	try {
	    c2 = Integer.parseInt(Keyboard.readString());
	}
	catch (Exception e) {
	    System.out.println("Invalid input, returning to beginning of turn.");
	    _board[r1][c1].flip();
	    return;
	}

	System.out.println("Flipping tile...");
	try {
	    if (r1 == r2 && c1 == c2) {
		System.out.println("No way you're flipping that over twice.");
		_board[r1][c1].flip();
		return;
	    }
	    else {
		_board[r2][c2].flip();
	    }
	}
	catch (Exception e) {
	    System.out.println("Error. Beginning of turn.");
	    _board[r1][c1].flip();
	    return;
	}
	System.out.println(printT(_board));
	try {
	    if (_board[r1][c1].equals(_board[r2][c2])) {
		System.out.println("Congrats! Tiles will remain flipped up.");
	    }
	    else {
		System.out.println("Loser. Can't even memorize cards. Flipping over.");
		_board[r1][c1].flip();
		_board[r2][c2].flip();
	    }
	}
	catch (Exception e) {
	    if (r1 == -1 || r2 == -1 || c1 == -1 || c2 == -1) {
		System.out.println("");
	    }
	    else {
		System.out.println("Invalid. No bueno.");
		return;
	    }
	}
    }
    
    public void play() {
	int flipped = 0;
	while (flipped != (_numRows * 4)) {
	    turn();
	    flipped = count(); 
	}
	System.out.println("We have a winner! Truly amazing. No sarcasm at all.");
    }
    //DO NOT MODIFY main()
    public static void main(String[] args){
	Concentration game = new Concentration();
	game.play();
    }
   
}//end class Concentration
