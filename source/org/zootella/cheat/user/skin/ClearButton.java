package org.zootella.cheat.user.skin;

import java.awt.Cursor;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Action;
import javax.swing.JLabel;

import org.zootella.cheat.process.Mistake;

public class ClearButton {

	public ClearButton(Action action, Rectangle place) {
		this.action = action;
		
		label = new JLabel();
		label.setLayout(null);
		label.setBounds(place);
	    label.addMouseListener(new MyMouseListener());
	}
	
	private final Action action;
	public final JLabel label;

    private class MyMouseListener extends MouseAdapter {
    	
    	@Override public void mouseEntered(MouseEvent m) {
    		try {
    			label.setCursor(new Cursor(Cursor.HAND_CURSOR));
    		} catch (Exception e) { Mistake.stop(e); }
    	}
    	
    	@Override public void mouseExited(MouseEvent m) {
    		try {
    			label.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    		} catch (Exception e) { Mistake.stop(e); }
    	}

    	@Override public void mouseClicked(MouseEvent m) {
    		try {
    			action.actionPerformed(new ActionEvent(m, 0, m.toString()));
    		} catch (Exception e) { Mistake.stop(e); }
    	}
    }	
}
