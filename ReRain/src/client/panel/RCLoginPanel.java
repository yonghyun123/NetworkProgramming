package client.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import client.btn.RCRectButton;
import client.contoller.RCLoginController;
import client.gui.RCContentPane;

public class RCLoginPanel extends JPanel {
	
	private Image mWelcomImage;// 환영메시지
	private Image mRightImage; //장식그림
	
	private JTextField mIdField;
	private JPasswordField mPwField;
	private RCRectButton mLoginButton; //로그인 버튼
	private RCRectButton mJoinButton; //회원가입 버튼
	private RCContentPane mParent; //상위패널
	
//	private RCLoginController mLoginController;
	
	public RCLoginPanel(RCContentPane parent){
		super();
		mParent = parent;
		this.mRightImage = Toolkit.getDefaultToolkit().getImage("images/login_image.png");
		this.mIdField = new JTextField();
		this.mPwField = new JPasswordField();
		this.mLoginButton = new RCRectButton("로그인");
		this.mJoinButton = new RCRectButton("회원가입");
		
	}
	
	public void init(String serverIP){
		JPanel topPanel = new JPanel();
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		Box inputBox = Box.createVerticalBox();
		Box btnBox = Box.createHorizontalBox();
		bottomPanel.add(inputBox);
		bottomPanel.add(btnBox);
		
		this.initTextField(mIdField);
		this.initTextField(mIdField);
		
		inputBox.add(this.mIdField);
		inputBox.add(Box.createVerticalStrut(10));
		inputBox.add(this.mPwField);
		
		btnBox.add(Box.createHorizontalStrut(10));
		btnBox.add(this.mLoginButton);
		btnBox.add(Box.createHorizontalStrut(10));
		btnBox.add(this.mJoinButton);
		
		topPanel.setOpaque(false);
		bottomPanel.setOpaque(false);
		
		this.setLayout(new BorderLayout());
		bottomPanel.setPreferredSize(new Dimension(0,150));
		this.add(bottomPanel, BorderLayout.SOUTH);
		
		this.setOpaque(false);
//		mLoginController = new RCLoginController(serverIP, this);
//		mLoginController.start();
		this.initEventHandler();
		mIdField.requestFocus();
	}
	
	private void initEventHandler(){
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = mIdField.getText();
				String pw = new String(mPwField.getPassword());
				if(id.trim().equals("")){
					mParent.showMessageDialog("input ID!!");
					return;
				}
				
				if(pw.trim().equals("")){
					mParent.showMessageDialog("input password!!");
					return;
				}
				
//				mLoginController.login(id,pw);
				
			}
		};
		
		mJoinButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String joinPanelName = mParent.getJoinPanel().getClass().getName();
				mParent.viewPanel(joinPanelName);
				mIdField.setText("");
				mPwField.setText("");
			}
		});
		
		mLoginButton.addActionListener(listener);
		mIdField.addActionListener(listener);
		mPwField.addActionListener(listener);
	}
	
	@Override
	public void paintComponent(Graphics g){
		g.drawImage(this.mRightImage, 300, 70, this.mRightImage.getWidth(this), this.mRightImage.getHeight(this), this);
		super.paintComponent(g);
	}
	
	public RCContentPane getContentPane(){
		return mParent;
	}

	// 텍스트 필드 초기화 함수
	private void initTextField(JTextField txt) {
		Font font = new Font("돋움", Font.PLAIN, 30);
		txt.setFont(font);
		txt.setBorder(null);
		txt.setBackground(Color.WHITE);
		txt.setHorizontalAlignment(JTextField.CENTER);
		txt.setPreferredSize(new Dimension(230, 40));
	}
}
