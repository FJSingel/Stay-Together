package backend.test;

import java.awt.Dimension;

import javax.swing.JPanel;


public class TPanel extends JPanel {

	public TPanel(int w, int h){
		Dimension d = new Dimension(w, h);
		setMinimumSize(d);
		setMaximumSize(d);
		setSize(d);
		setPreferredSize(d);
	}
	
	
}
