package backend.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

public class Window extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private JFrame frame;
	private boolean maximized;

	public Window() {
		mapActions();
		setMaximized(true);
		setBackground(Color.black);
	}
	
	public void setMaximized(boolean maximized) {
		if (this.maximized != maximized) {
			this.maximized = maximized;
			rebuildFrame();
		}
	}

	private void rebuildFrame() {
		if (frame != null)
			frame.dispose();
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(800, 600));

		frame.setUndecorated(maximized);
		if (maximized)
			frame.setExtendedState(frame.getExtendedState()
					| JFrame.MAXIMIZED_BOTH);
		else
			frame.setExtendedState(frame.getExtendedState()
					& ~JFrame.MAXIMIZED_BOTH);

		frame.setContentPane(this);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	@SuppressWarnings("serial")
	protected void mapActions() {
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_F11, 0),
				"togglemaximize");
		getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
				"close");
		getActionMap().put("togglemaximize", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setMaximized(!maximized);
			}
		});
		getActionMap().put("close", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (frame != null)
					frame.dispatchEvent(new WindowEvent(frame,
							WindowEvent.WINDOW_CLOSING));
			}
		});
	}

}
