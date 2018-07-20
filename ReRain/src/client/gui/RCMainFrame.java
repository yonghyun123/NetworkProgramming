package client.gui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Robot;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class RCMainFrame extends JFrame{

	//Attributes
	public static final int FRAME_WIDTH = 700;
	public static final int FRAME_HEIGHT = 500;
	private static final String FRAME_TITLE = "RAIN";
	private static final Dimension SCR_DIM = Toolkit.getDefaultToolkit()
			.getScreenSize();
	
	private RCContentPane mRootPanel; //root panel
	private JTextField mLoadingText; // 로딩 메시지
	private JPanel mLoadingPanel; //로딩 패널
	
	public RCMainFrame(){
		super(RCMainFrame.FRAME_TITLE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setLayout(new BorderLayout());
		this.loading();
		this.restoreScreen();
	}
	
	public void init(String serverIP) throws AWTException {
		mRootPanel.init(serverIP);
		this.remove(mLoadingPanel);
		this.add(mRootPanel, BorderLayout.CENTER);
		
		int mDelayTime;
		mDelayTime = 2*1000;
		Robot robot = new Robot();
		robot.delay(mDelayTime);
		
		this.validate();
		this.repaint();
	}
	
	//로딩화면 표시함수
	public void loading(){
		mLoadingText = new JTextField();
		mLoadingPanel = new JPanel() {
			private Image mBg = Toolkit.getDefaultToolkit().getImage("images/loading_kakao.png");
			
			protected void paintComponent(Graphics g) {
				g.drawImage(mBg, 0, 0, 700, 500, this);
				super.paintComponent(g);
			}
		};
		
		mLoadingText.setOpaque(false);
		mLoadingText.setEditable(false);
		mLoadingText.setEnabled(false);
		mLoadingText.setBorder(null);
		mLoadingText.setFont(new Font("돋움", Font.BOLD, 30));
		mLoadingText.setHorizontalAlignment(JTextField.CENTER);
		mLoadingText.setPreferredSize(new Dimension(0, 50));

		mLoadingPanel.setOpaque(false);
		mLoadingPanel.setPreferredSize(new Dimension(400, 300));
		mLoadingPanel.setLayout(new BorderLayout());
		mLoadingPanel.add(mLoadingText, BorderLayout.SOUTH);

		this.add(mLoadingPanel);
		this.setSize(700, 500);
		this.setVisible(true);
	}
	
	public void viewPanel(String panelName){
		this.mRootPanel.viewPanel(panelName);
	}
	
	public void addPanel(Component comp, String panelName){
		this.mRootPanel.addPanel(comp, panelName);
	}
	
	public void restoreScreen(){
		this.setSize(RCMainFrame.FRAME_WIDTH, RCMainFrame.FRAME_HEIGHT);
		this.setLocation(SCR_DIM.width/2-RCMainFrame.FRAME_WIDTH/2,
				SCR_DIM.height/2 -RCMainFrame.FRAME_HEIGHT/2);
		mRootPanel.updateUI();
	}
	
	//getter and setter
	public RCContentPane getChild(){
		return mRootPanel;
	}
	
	public JTextField getLoadingText(){
		return mLoadingText;
	}
	
	public void setLoadingText(JTextField mLoadingText){
		this.mLoadingText = mLoadingText;
	}
	
	
}
