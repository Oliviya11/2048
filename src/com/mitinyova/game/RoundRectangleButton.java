package com.mitinyova.game;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JButton;
/**
 * 
 * @author Anastasia Mitinova
 *
 */
public class RoundRectangleButton extends JButton{
	
	private static final long serialVersionUID = 1L;
	private int option;
	RoundRectangleButton(int option) {
		this.option = option;
	}
 /**
  * Paints rectangle button with round angles, change colors
  * depending from variable option.
  * 
  */
public void paintComponent(Graphics g) { 
	//if (!Game.lose && !Game.win){
	  if (getModel().isArmed()){
		  switch(option) {
		  case 1: g.setColor(new Color(255, 165, 0)); break;
		  case 2: g.setColor(new Color(255, 215, 0)); break;
		  default: break;
		  }
		
	  }
	  else {
		  switch(option) {
		  case 1:  g.setColor(new Color(255, 140, 0)); break;
		  case 2:  g.setColor(new Color(255, 255, 0)); break;
		  default: break;
		  }
	  }
	  g.fillRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 5, 5);
//	}
 }
/**
 * Paints button's borders depending from variable option.
 */
  protected void paintBorder(Graphics g) {
	//  if (!Game.lose && !Game.win){
	  switch(option) {
	  case 1: g.setColor(new Color(255, 140, 0)); break;
	  case 2: g.setColor(new Color(255, 255, 0)); break;
	  default: break;
	  }
	  g.drawRoundRect(0, 0, getSize().width - 1, getSize().height - 1, 5, 5);
	//  }
  }
}
