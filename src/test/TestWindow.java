package test;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JFrame;

public class TestWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public TestWindow(){
		super();
		setMinimumSize(new Dimension(800, 600));
		setLayout(new FlowLayout());
		add(new UserPanel());
		add(new RoomPanel());
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pack();
		setVisible(true);
	}
	
	
}
