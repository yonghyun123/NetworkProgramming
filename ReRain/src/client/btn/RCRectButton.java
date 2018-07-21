package client.btn;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class RCRectButton extends JButton {

	//Constructor
	public RCRectButton(String title){
		super(title);
		this.setContentAreaFilled(false);
		this.setBorder(null);
		this.setIcon(new ImageIcon("images/Button_brown2.png"));
		this.setRolloverIcon(new ImageIcon("images/recbutton_rollover2.png"));
		this.setPressedIcon(new ImageIcon("images/recbutton_rollover2.png"));
		this.setCursor(new Cursor(Cursor.HAND_CURSOR));
		this.setHorizontalTextPosition(CENTER);
		this.setForeground(Color.white);
		this.setFont(new Font("돋움", Font.BOLD,15));
		this.setSize(85,85);
	}
}
