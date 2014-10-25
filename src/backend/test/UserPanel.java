package backend.test;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import backend.base.CompletionListener;
import backend.user.User;
import backend.user.UserData;
import backend.user.UserPData;


public class UserPanel extends TPanel {

	private static final long serialVersionUID = 1L;

	private JTextField user = new JTextField();
	private JButton button = new JButton(new AbstractAction() {
		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent e) {
			data.setText(" ");
			pdata.setText(" ");
			User u = new User(user.getText());
			u.getUserDataNonBlocking(new CompletionListener() {
				@Override
				public void completed(Object obj) {
					UserData d = (UserData) obj;
					if (d == null)
						data.setText("User does not exist");
					else
						data.setText(d.name);
				}
			});
			u.getUserPDataNonBlocking(new CompletionListener() {
				@Override
				public void completed(Object obj) {
					UserPData p = (UserPData) obj;
					if (p != null)
						pdata.setText(p.gids.size() + " groups");
				}
			});
		}
	});
	private JLabel data = new JLabel(" ", SwingConstants.CENTER);
	private JLabel pdata = new JLabel(" ", SwingConstants.CENTER);

	public UserPanel() {
		super(200, 150);
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		c.gridy = 0;
		c.gridheight = 1;
		add(new JLabel("User Lookup", SwingConstants.CENTER), c);

		c.gridy = 1;
		c.gridheight = 1;
		add(user, c);

		c.gridy = 2;
		c.gridheight = 1;
		add(button, c);

		c.gridy = 3;
		c.gridheight = 1;
		add(data, c);

		c.gridy = 4;
		c.gridheight = 1;
		add(pdata, c);

		System.out.println(this);
	}
	/*
	 * public void paint(Graphics g) {
	 * 
	 * }
	 */
}
