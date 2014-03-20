package gvprojects.chess.presenter;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gvprojects.chess.model.Model;
import gvprojects.chess.model.Move;
import gvprojects.chess.view.GUI;

public class Presenter {

	/** model */
	private Model m;

	/** view */
	private GUI g;
	
	private boolean from;
	
	private int fromRow;
	private int fromCol;
	private int toRow;
	private int toCol;
	
	/*********************************************************************
	 * ActionListener and ActionPerformed for array
	 * 
	 * @return none
	 *********************************************************************/
	class boardListener implements ActionListener {

		private int buttonRow;
		private int buttonCol;
		
		boardListener(int buttonRow, int buttonCol) {
			this.buttonRow = buttonRow;
			this.buttonCol = buttonCol;
		}

		public void actionPerformed(ActionEvent e) {

			if(isFrom() && hasPiece(buttonRow, buttonCol)){
				
				setFromRow(buttonRow);
				setFromCol(buttonCol);
				System.out.println(getFromRow()+ " + "+ getFromCol());
				//g.getBoard()[buttonRow][buttonCol].setBackground(Color.BLUE);
				from = false;
			}
			else{
				
				setToRow(buttonRow);
				setToCol(buttonCol);
				System.out.println(getToRow()+ " + "+ getToCol());
				Move tempMove = new Move(fromRow, fromCol, toRow, toCol);
				m.move(tempMove);
				g.printBoard(m.getBoard());
				from = true;
			}
			
//			if (m.isComplete()) {
//				g.end();
//			}

//			// runs if the game has been won
//			if (engine.status() == GameStatus.X_WON || engine.status() == GameStatus.O_WON) {
//				// colors the buttons indicated by the HashMap
//				for (int i = 1; i <= engine.getLength(); i++) {
//					String colorMe = engine.getWin(i);
//					String[] strArray = colorMe.split(",");
//					int curRow = Integer.parseInt(strArray[0]);
//					int curCol = Integer.parseInt(strArray[1]);
//					window.color(curRow, curCol);
//				}
//			}
//
//			// pops up window if x won
//			if (engine.status() == GameStatus.X_WON) {
//				window.won(true);
//			}
//
//			// pops up window if o won
//			if (engine.status() == GameStatus.O_WON) {
//				window.won(false);
//			}
		}

	}
	
	public Presenter() {
		m = new Model();
		g = new GUI();
		setFrom(true);
		fromRow = 0;
		fromCol = 0;
		toCol = 0;
		toRow = 0;
		
		// adds action listeners to all the buttons in the array
		for (int i = 0; i < g.getBoard().length; i++) {
			for (int j = 0; j < g.getBoard()[0].length; j++) {
				g.addButtonListener(i, j, new boardListener(i,
						j));
			}
		}
	}
	
	/******************************************************************
	 * Updates the gameboard
	 * 
	 ******************************************************************/
	public final void update() {
		g.printBoard(m.getBoard());
		// if they put themselves in check
		if (m.isPutSelfInCheck()) {
			g.putSelfInCheck();
			m.setPutSelfInCheck(false);
		}
		// if they put the other player in check
		if (m.isOtherPlayerCheck()) {
			g.inCheck();
			m.setOtherPlayerCheck(false);
		}
		g.prompt(m.currentPlayer());
		//moveString = in.nextLine();

		// while the input is invalid
//		while (!isValidInput(moveString)) {
//			v.invalidInput();
//			v.prompt(m.currentPlayer());
//			moveString = in.nextLine();
//		}

//		int fromRow = Math
//				.abs(Integer.parseInt(moveString
//						.substring(1, 2)) - m.getBoard().length);
//		int fromCol = moveString.substring(0, 1)
//				.toUpperCase().charAt(0) - 'A';
//		int toRow = Math.abs(Integer.
//				parseInt(moveString.substring(4, 5)) 
//				- m.getBoard().length);
//		int toCol = moveString.substring(3, 4)
//				.toUpperCase().charAt(0) - 'A';
//		Move move = new Move(fromRow, fromCol, toRow, toCol);

//		m.move(move);
//		update();
	}
	
	public static void main(String[] args) {
		Presenter p = new Presenter();
		p.update();
	}
	
	public boolean hasPiece(int r, int c){
		if(m.getBoard()[r][c]!=null){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean isFrom() {
		return from;
	}

	public void setFrom(boolean from) {
		this.from = from;
	}
	
	public int getFromRow(){
		return fromRow;
	}
	
	public void setFromRow(int r){
		fromRow = r;
	}
	
	public int getFromCol(){
		return fromRow;
	}
	
	public void setFromCol(int c){
		fromCol = c;
	}
	
	public int getToRow(){
		return fromRow;
	}
	
	public void setToRow(int r){
		toRow = r;
	}
	
	public int getToCol(){
		return fromRow;
	}
	
	public void setToCol(int c){
		toCol = c;
	}
}


