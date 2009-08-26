package org.zootella.cheat.user.widget;

import java.awt.Rectangle;

import javax.swing.JLabel;

public class ClearLabel {
	
	public ClearLabel(Rectangle place) {
		label = new JLabel();
		label.setLayout(null);
		label.setBounds(place);
	}
	
	public final JLabel label;
}
