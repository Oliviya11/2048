package com.mitinyova.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JPanel;
/**
 * 
 * @author Anastasia Mitinova
 *
 */
@SuppressWarnings("serial")
public class Game extends JPanel {
  static boolean win = false, lose = false;
  private Dimension gameField;
  private int width, height;
  private ListCell c = new ListCell();
  private JButton backButton, newGameButton;
  /**
   * Creates instance of the class Game,
   * to attach buttons on the panel.
   * @param width of frame used to set the field's width
   * @param height of frame used to set the field's height
   */
   Game(int width, int height) {
	  gameField = new Dimension(height/2, height/2);
	  setSize(new Dimension(width, height));
      setFocusable(true);
      setLayout(null);
      paintButtons();
      manage();
	  
   }
   
  /**
   * This method used to draw elements on the panel.
   */
  public void paint (Graphics g){
	  super.paint(g);
	 
	  try {
		Thread.sleep(10);
	} catch (InterruptedException e1) {
		e1.printStackTrace();
	}
	  width = (int) ((getWidth() - gameField.getWidth())/2);
	  height = (int) ((getHeight() - gameField.getHeight())/2) + 40;
	  g.translate(width, height);
	  g.setColor(new Color(205, 179, 139));
	  g.fillRoundRect(0, 0, (int)gameField.getWidth(), (int)gameField.getHeight(), 10, 10);
	  c.drawCells(g);
	  g.setColor(Color.darkGray);
	  g.setFont(new Font ("Arial", 1, 80));
	  g.drawString("2048", 0, -40);
	  g.setColor(new Color(205, 179, 139));
	  g.fillRoundRect(215, -100, 100, 70, 10, 10);
	  g.setColor(Color.white);
	  g.setFont(new Font ("Arial", 1, 20));
	  g.drawString("Score:", 220, -70);
	
	  String score = String.valueOf(c.getScore());
  	  g.drawString(score, 220, -45);
  	  if (win) {
  	     g.setColor(new Color(255, 215, 0, 128));
  		 g.fillRect(-width, -height, getWidth() , getHeight());
  		 Font font = new Font("Arial", 1 , 100);
  		 String you = "YOU";
  		 String win = "WIN!";
  		 g.setFont(font);
  		 g.setColor(new Color(255, 255, 255, 210));
  		 g.drawString(you, 50, 85);
  		 g.drawString(win, 48, 205);
  		 font = new Font("Arial", 1 , 20);
  		 g.setFont(font);
  		 g.drawString("press \"Enter\" to continue ", 40, 370);
  		 
  	  }
  	  
  	  if (lose) {
  	     g.setColor(new Color(255, 255, 255, 128));
 		 g.fillRect(-width, -height, getWidth() , getHeight());
 		 Font font = new Font("Arial", 0 , 100);
 		 String game = "GAME";
 		 String over = "OVER!";
 		 g.setFont(font);
 		 g.setColor(new Color(139, 119, 101, 220));
 		 g.drawString(game, 5, 100);
 		 g.drawString(over, 5, 220);
 		 /*
 		 font = new Font("Arial", 1 , 20);
  		 g.setFont(font);
  		 g.drawString("press \"Enter\" to start new game ", 12, 370);
  		 */
  	  }
	
  }
  /**
   * This method used to manage program, when user pressed 
   * something on keyboard.
   */
  private void manage(){
	  this.addKeyListener(new KeyAdapter() {
		 
		    public void keyPressed(KeyEvent e) {
		    
		    if (!win && !lose) {
		     if (e.getKeyCode() == KeyEvent.VK_UP){
		   		c.moveUpLeft(1, 4);
		   	} 
		   	else if (e.getKeyCode() == KeyEvent.VK_DOWN){
		   		c.moveDownReight(1, 4);
		   	}
		   	else if (e.getKeyCode() == KeyEvent.VK_RIGHT){
		   		c.moveDownReight(4, 1);
		   	}
		   	else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		   	    c.moveUpLeft(4, 1);
		   		
		   	}
		    }
		    
		   	else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		   		/*
		   		if (lose) { 
		   			lose = false;
		   			c.makeNewGame();
		   			paintButtons();
		   		}
		   	  */
		   		if(win) {
		   			win = false;
		   			c.setCheckWinFalse();
		   			paintButtons();
		   		}
		   	}
		   	
		     repaint();
		    }
		             
		});
  }
  /**
   * This method used to create buttons (backButton and newGameButton)
   * backButton - used to back on one step
   * newGameButton - used to start new game
   */
 private void paintButtons() {
	 
	 newGameButton = new RoundRectangleButton(2);
     newGameButton.setSize(20, 20);
     newGameButton.setLocation(300,160);
     newGameButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		//	if (!win && !lose){
			c.makeNewGame();
			repaint();
		//	}
			}
		
   	  
     });
     add(newGameButton);
     newGameButton.setToolTipText("Start new game");
     newGameButton.setFocusable(false);
	 
    backButton = new RoundRectangleButton(1);
   
     backButton.setSize(20, 20);
     backButton.setLocation(335,160);
     backButton.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
		//	if (!win && !lose){
			c.setNumbers();
			repaint();
		//	}
		}
   	  
     });
     add(backButton);
     backButton.setToolTipText("Back one step");
     backButton.setFocusable(false);
}

}
