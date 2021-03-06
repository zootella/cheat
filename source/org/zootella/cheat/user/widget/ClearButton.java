package org.zootella.cheat.user.widget;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JLabel;

import org.zootella.cheat.data.Text;
import org.zootella.cheat.process.Mistake;

public class ClearButton {
	
	/** text and tip are optional and can be null. */
	public ClearButton(Action action, Font font, Rectangle place, String text, String tip) {
		this.action = action;
		
		label = new JLabel();
		label.setLayout(null);
		label.setBounds(place);
		label.setFont(font);

		//actually not clear
		/*
		label.setBackground(new Color(0xffffff));
		label.setOpaque(true);
		*/
		
		
	    label.addMouseListener(new MyMouseListener());
	    label.setCursor(new Cursor(Cursor.HAND_CURSOR));
	    if (Text.is(text))
	    	label.setText(text);
	    if (Text.is(tip))
	    	label.setToolTipText(tip);
	}
	
	private final Action action;
	public final JLabel label;

    private class MyMouseListener extends MouseAdapter {
    	@Override public void mouseClicked(MouseEvent m) {
    		try {
    			action.actionPerformed(new ActionEvent(m, 0, m.toString()));
    		} catch (Exception e) { Mistake.stop(e); }
    	}
    }	
}
