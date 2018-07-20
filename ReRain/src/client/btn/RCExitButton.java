package client.btn;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RCExitButton extends JButton {
	public RCExitButton(){
		super();
		this.setContentAreaFilled(false);
		this.setBorder(null);
		this.setIcon(new ImageIcon("images/Exit.png"));;
		this.setRolloverIcon(new ImageIcon("images/Exit_over.png"));
		this.setPressedIcon(new ImageIcon("images/Exit_pressed.png"));
		this.setSize(30,30);
	}
}
