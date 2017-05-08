package com.mitinyova.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/**
 * 
 * @author Anastasia Mitinova
 *
 */
public class Cell {
	static final int WIDTH = 70;
    private Font font;
	private int number = 0;
	
	/**
	 * This method used to give color to cell. 
	 * Color depends on number.
	 * @return a cell's color
	 */
	Color giveColor(){
		switch(number){
		 case 0: return new Color(238, 207, 161);
		 case 2: return new Color(255, 255, 240);
		 case 4: return new Color(255, 250, 205);
		 case 8: return new Color(255, 165, 0);
		 case 16: return new Color(255, 127, 36);
		 case 32: return new Color(255, 99, 71);
		 case 64: return new Color(255, 0, 0); 
		 case 128: return new Color(238, 238, 0);
		 case 256: return new Color(238, 173, 14);
		 case 512: return new Color(238, 180, 34);
		 case 1024: return new Color(218, 165, 32);
		 case 2048: return new Color(218, 165, 32);
		 default: return new Color(0, 0, 0);
		}
	}
	
    /**
     * This method used to draw cell.
     * @param g Graphics
     * @param x coordinate of cell's left corner
     * @param y coordinate of cell's left corner
     */
	void drawCell(Graphics g, int x, int y){
		
		g.setColor(giveColor());
		g.fillRoundRect(x, y, WIDTH, WIDTH, 10, 10);
		if (number != 0){
		String s = String.valueOf(number);
		if (number == 2 || number == 4)g.setColor(Color.darkGray);
		else g.setColor(Color.white);
		if (number > 1 && number < 65){
		  font = new Font ("Arial", 1, 32);
		}
		else if (number < 513 ) {
		  font = new Font ("Arial", 1, 28);
		}
		else {
		  font = new Font ("Arial", 1, 26);	
		}
	    g.setFont(font);
	    FontMetrics metrics = g.getFontMetrics(font);
		g.drawString(s, x+(WIDTH - metrics.stringWidth(s))/2  , y+(WIDTH - metrics.getHeight())/2 + metrics.getAscent());
		}
	}
	
	/**
	 * This method used to set new number to cell.
	 * @param number value to set
	 */
	void setNumber(int number) {
		this.number = number;
	}
	
	/**
	 * This method used to get number.
	 * @return number
	 */
	int getNumber() {
		return number;
	}
}
