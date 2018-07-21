package client.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.LinkedList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import client.gui.RCContentPane;

public class RCJoinPanel extends JPanel {
	private Image mBackground; //배경이지

	//componenet
//	private LinkedList<RCAvatar> mAvatarImage; //아바타 이미지 리스트
	private JPanel mAvatarPanel; //아바타패널
	private JButton mOkButton; //ok button
	private JButton mCancelButton; // cancel button
	private JButton mSliderLeftButton; //avatar left button
	private JButton mSliderRightButton; //avatar right button
	private JTextField mIdField; //id
	private JPasswordField mPwField; //pw field
	private JPasswordField mPwField2; //pw2 field
	private JTextField mNameField; //name field
	private String mAvatarName; //avatar name
//	private RCJoinContoller mJoinController; //join controller
	private RCContentPane mParent; //상위 패널
//	private RCSound mSound; //sound
	
	//constructor
	public RCJoinPanel(RCContentPane parent){
		super();
		mParent = parent;
		mBackground = Toolkit.getDefaultToolkit().getImage("images/section.png");
//		mAvatarImage = new LinkedList<RCAvatar>();
//		for(String avatarName : RCGUIConstant.AVATAR_NAME){
//			Image avImg = Toolkit.getDefaultToolkit().getImage("charater/"+avatarName+".png");
//			RCAvatar avatar = new RCAvatar(avImg, avatarName, this);
//			mAvatarImage.add(avatar);
//		}
		
		mOkButton = new JButton("가입");
		mCancelButton = new JButton("취소");
		mSliderLeftButton = new JButton();
		mSliderRightButton = new JButton();
		mIdField = new JTextField();
		mPwField = new JPasswordField();
		mPwField2 = new JPasswordField();
		mNameField = new JTextField();
//		mSound = new RCSound();
//		mAvatarName = RCGUIConstant.AVATAR_NAME[0];
	}
	
	public void init(String serverIP){
		this.initButton(mOkButton);
		this.initButton(mCancelButton);
		this.initSliderButton(mSliderLeftButton, "left");
		this.initSliderButton(mSliderRightButton, "right");
		this.setLayout(new BorderLayout());
		this.add(this.createMainPanel(), BorderLayout.CENTER);
		this.setOpaque(false);
		
//		mJoinController = new RCJoinContoller(serverIP, this);
//		mJoinController.start();
//		this.initEventHandler();
	}
	
	// 버튼 초기화 함수
	public void initButton(JButton btn){
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setIcon(new ImageIcon("images/button_brown.png"));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setHorizontalTextPosition(JButton.CENTER);
		btn.setForeground(Color.white);
		btn.setFont(new Font("돋움", Font.BOLD, 12));
		btn.setPreferredSize(new Dimension(120, 40));
		btn.setMargin(new Insets(10,10,10,10));
	}
	
	// 아바타 선택 버튼 초기화 함수
	private void initSliderButton(JButton btn, String direction) {
		btn.setContentAreaFilled(false);
		btn.setBorder(null);
		btn.setIcon(new ImageIcon("images/arrow_" + direction + ".png"));
		btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btn.setPreferredSize(new Dimension(40, 140));
	}
	
	// 메인패널 생성 함수
	private JPanel createMainPanel() {
		JPanel panel = new JPanel() {
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
				g.drawImage(mBackground, 200, 0, 500,
						600, this);
				super.paintComponent(g);
			}
		};

		panel.setPreferredSize(new Dimension(300, 580));
		panel.setBorder(new EmptyBorder(new Insets(170, 230, 30,230 )));
		panel.setOpaque(false);
		panel.setLayout(new BorderLayout());
		panel.add(this.createJoinForm(), BorderLayout.CENTER);
		panel.add(this.createBottonPanel(), BorderLayout.SOUTH);
		
		return panel;
	}

	// 버튼패널 생성 함수
	private JPanel createBottonPanel() {
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(0, 50));
		panel.setOpaque(false);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 0));
		panel.add(mOkButton);
		panel.add(mCancelButton);
		return panel;
	}
	
	// 회원가입 양식
	private JPanel createJoinForm() {
		JPanel panel = new JPanel();
		Box box = Box.createVerticalBox();
		box.add(this.createInputLine("아이디", mIdField));
		box.add(this.createInputLine("암호", mPwField));
		box.add(this.createInputLine("암호 재입력", mPwField2));
		box.add(this.createInputLine("이름", mNameField));
		Border padding = BorderFactory.createEmptyBorder(0, 0, 0, 60);
		box.setBorder(padding);
		
		
		panel.setOpaque(false);
		
		panel.setLayout(new BorderLayout());
		panel.add(box, BorderLayout.EAST);
		JPanel avatarPanel = new JPanel();
		avatarPanel.setLayout(new BorderLayout());
//		avatarPanel.add(this.createAvatarSlider(),BorderLayout.CENTER);
		avatarPanel.add(mSliderLeftButton, BorderLayout.WEST);
		avatarPanel.add(mSliderLeftButton, BorderLayout.EAST);
		
		panel.add(avatarPanel, BorderLayout.SOUTH);
		return panel;
	}

	// 입력필드 생성 함수
	private JPanel createInputLine(String title, JTextField input) {
		JPanel panel = new JPanel();
		JLabel label = new JLabel(title);

		label.setPreferredSize(new Dimension(80, 30));
		label.setFont(new Font("돋움", Font.BOLD, 12));
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(JLabel.LEFT);
		
		input.setPreferredSize(new Dimension(220, 30));
		input.setFont(new Font("돋움", Font.BOLD, 20));
		input.setForeground(Color.BLACK);
		input.setOpaque(false);
		input.setHorizontalAlignment(JTextField.CENTER);
		input.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		panel.setLayout(new FlowLayout(FlowLayout.LEFT, 15, 0));
		panel.setOpaque(false);
		panel.add(label);
		panel.add(input);
	
		return panel;
	}
}
