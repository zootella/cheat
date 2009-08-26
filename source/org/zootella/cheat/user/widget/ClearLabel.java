package org.zootella.cheat.user.widget;

import java.awt.Color;
import java.awt.Rectangle;

import javax.swing.JLabel;

import org.zootella.cheat.data.Text;

public class ClearLabel {
	
	public ClearLabel(Rectangle place, String s) {
		label = new JLabel();
		label.setLayout(null);
		label.setBounds(place);
		if (Text.is(s))
			label.setText(s);
		
		//actually not clear
		label.setBackground(new Color(0xcccccc));
		label.setOpaque(true);
		/*
		 */
		
		
	}
	
	public final JLabel label;
}