package com.mitinyova.game;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListCell {
  private int score=0, prevScore=0, action = 0;
  private final int step = 8;
  private List<Cell> cells = new LinkedList<Cell>();
  private int[] prevNumbers = new int[16];
  private Random random = new Random();
  private boolean b = false;
  private boolean checkWin = true;
  
  
  ListCell() {
	  fillListCells();
	  getNumbers();
  }

   private void fillListCells() {

	  for (int i = 0; i < 16; i++) {
		 cells.add(i, new Cell());
	  }
	  fillRand();
	  fillRand();
	   
   }
    void drawCells(Graphics g) {
      int width = step;
  	  int height = step;
  	  for (int i = 0; i < cells.size(); i++) {
  		 cells.get(i).drawCell(g, width, height);
  		 width = width + Cell.WIDTH + step;
  		 if (i == 3 || i == 11 || i == 7 || i == 15){
  			 width = step; 
  			 height = height + Cell.WIDTH + step;
  		 }
  	  }
    }
   private int giveRandom() {
	 return random.nextInt(16);
   }
   
   private int giveRandomNumber() {
	   if (random.nextInt(100) > 90) return 4;
	   else return 2;
	   
   }
   
  private void fillRand() {
	  b = false;
	  while (!b){
		 int index = giveRandom();
	   if (cells.get(index).getNumber() == 0){	
	       cells.get(index).setNumber(giveRandomNumber());
	       b = true;
	  }
	  }
  }
 

  void moveUpLeft(int step, int lineStep) {
	  if (checkUPLeft(step, lineStep)) {
	   action+=1;
	   if (action % 2 ==0) getNumbers(); 
	  
	   for (int k = 0; k < step*3 + 1; k+=step){
	  	for (int i = 0; i < 4; i++) {
	  		for (int j = k; j< k + lineStep*3; j+=lineStep){
	  			
	  			if (cells.get(j).getNumber() == cells.get(j+lineStep).getNumber() && cells.get(j).getNumber()!=0 && cells.get(j+lineStep).getNumber()!=0){
	  				cells.get(j+lineStep).setNumber(0);
	  				cells.get(j).setNumber(2*cells.get(j).getNumber());
	  				    score = score + cells.get(j).getNumber();
	  			}
	  			else if (cells.get(j).getNumber() == 0 && cells.get(j+lineStep).getNumber()!=0) {
	  				cells.get(j).setNumber(cells.get(j+lineStep).getNumber());
	  				cells.get(j+lineStep).setNumber(0);
	  			}
	  		}
	  		
	  	 }
	    }
	   fillRand(); 
	   if (checkWin) checkForWin();
	   if (!checkDownRight(step, lineStep) && !checkDownRight(lineStep, step) && !checkUPLeft(step, lineStep) && !checkUPLeft(lineStep, step)) 
		  Game.lose = true;
			  
	 }
 }
  void moveDownReight(int step, int lineStep) {
	 if (checkDownRight(step, lineStep)){
		action+=1;
	   if (action % 2 ==0) getNumbers();
	   for (int k = 15; k > 14 - 3*step; k-=step){
	  	for (int i = 0; i < 4; i++) { 
	  		for (int j = k; j> k-3*lineStep; j-=lineStep){
	  		 
	  			if (cells.get(j).getNumber() == cells.get(j-lineStep).getNumber() && cells.get(j).getNumber()!=0 && cells.get(j-lineStep).getNumber()!=0){
	  				cells.get(j-lineStep).setNumber(0);
	  				cells.get(j).setNumber(2*cells.get(j).getNumber());
	  				score = score + cells.get(j).getNumber();
	  				
	  	
	  			}
	  			else if (cells.get(j).getNumber() == 0 && cells.get(j-lineStep).getNumber()!=0) {
	  				cells.get(j).setNumber(cells.get(j-lineStep).getNumber());
	  				cells.get(j-lineStep).setNumber(0);
	  			}
	  			
	  		}
	  			
	  		}
	  	 }
	  fillRand(); 
	  if (checkWin) checkForWin();
	   if (!checkDownRight(step, lineStep) && !checkDownRight(lineStep, step) && !checkUPLeft(step, lineStep) && !checkUPLeft(lineStep, step))
		   Game.lose = true;
	 } 
 }
  public int getScore() {
	  return score;
  }

  
 
  
  private void checkForWin() {
	 for (Cell c: cells) {
		 if (c.getNumber() == 2048) {
			 Game.win = true;
			 break;
		 }
	 }
  }  
  private boolean checkUPLeft(int step, int lineStep) {
	  for (int k = 0; k < step*3 + 1; k+=step){
		  	for (int i = 0; i < 4; i++) {
		  		for (int j = k; j< k + lineStep*3; j+=lineStep){
		  			
		  			if (cells.get(j).getNumber() == cells.get(j+lineStep).getNumber() && cells.get(j).getNumber()!=0 && cells.get(j+lineStep).getNumber()!=0){
		  				return true;
		  			}
		  			else if (cells.get(j).getNumber() == 0 && cells.get(j+lineStep).getNumber()!=0) {
		  				return true;
		  			}
		  		}
		  		
		  	 }
		    }
	  return false;
  }
  
  private boolean checkDownRight(int step, int lineStep) {
	  for (int k = 15; k > 14 - 3*step; k-=step){
		  	for (int i = 0; i < 4; i++) { 
		  		for (int j = k; j> k-3*lineStep; j-=lineStep){
		  		 
		  			if (cells.get(j).getNumber() == cells.get(j-lineStep).getNumber() && cells.get(j).getNumber()!=0 && cells.get(j-lineStep).getNumber()!=0){
		  				return true;
		  			}
		  			else if (cells.get(j).getNumber() == 0 && cells.get(j-lineStep).getNumber()!=0) {
		  			    return true;
		  			}
		  			
		  		}
		  			
		  		}
		  	 }
	  return false;
  }
  
  public void makeNewGame() {
	  action = 0; 
	  for(Cell c: cells) {
		  c.setNumber(0);
	  }
	  
	  fillRand();
	  fillRand();
	  score = 0;
	  getNumbers();
	  Game.win = false;
	  Game.lose = false;
  }
  
  public void getNumbers() {
	 
	  for (int i = 0; i < 16; i++) {
		  prevNumbers[i] = cells.get(i).getNumber();
		  }
	  prevScore = score;
  }
  public void setNumbers() {
	  int i = 0;
	  for (Cell c: cells) {
		  c.setNumber(prevNumbers[i]);
		  i++;
	  }
	  score = prevScore;
	  Game.win = false;
	  Game.lose = false;
	  
  }
  public void setCheckWinFalse() {
	  checkWin = false;
  }

}
