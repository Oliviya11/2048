package com.mitinyova.game;

import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
	JFrame window = new JFrame();
	window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	window.setSize(new Dimension(400, 640));
	window.add(new Game(window.getWidth(), window.getHeight()));
	window.setLocationRelativeTo(null);
	window.setResizable(false);
	window.setVisible(true); 

	}

}
