package client.gui;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JPanel;

import client.btn.RCExitButton;
import client.panel.RCLoginPanel;

public class RCContentPane extends JPanel {
	//Attributes
	private CardLayout mCardLayout; //카드레이아웃
	private RCMainFrame mFrame; //메인프레임
	private JPanel mTopPanel; //위쪽패널
	private JPanel mContent; //내용
	private Image mTitleImage; //타이틀자리에 표시할 이미지
	private boolean mIsFullScreen = false; //풀스크린
	
	//Components
	private RCExitButton mExitButton; //x button
	private RCGlassPane mGlassPane; // glasspane
//	private RCLoginPanel mLoginPanel; // 로그인화면
//	private RCJoinPanel mJoinPanel; //회원가입 화면
//	private RCLobbyPanel mLobbyPanel; //로비화면
//	private RCGameRoomPanel mGameRoomPanel //방화면
	
	public RCContentPane(RCMainFrame parent){
		mFrame = parent;
		mContent = new JPanel();
		mTopPanel = new JPanel();
		
		mExitButton = new RCExitButton();
		mGlassPane = new RCGlassPane();
		mLoginPanel = new RCLoginPanel(this);
		mJoinPanel = new RCJoinPanel(this);
		mLobbyPanel = new RCLobbyPanel(this);
		mGameRoomPanel = new RCGameRoomPanel(this);
	}
	
	public void init(String serverIP){
//		mGlassPane.init();
		mFrame.getLoadingText().setText("glass panel 초기화..");
		mLoginPanel.init(serverIP);
		mFrame.getLoadingText().setText("login panel 초기화...");
		mJoinPanel.init(serverIP);
		mFrame.getLoadingText().setText("join panel 초기화...");
		mLobbyPanel.init(serverIP);
		mFrame.getLoadingText().setText("lobby panel 초기화..");
		mGameRoomPanel.init(serverIP);
		mFrame.getLoadingText().setText("game room 초기화...");
		
		mTitleImage = Toolkit.getDefaultToolkit().getImage("images/Team_Logo.png");
		
		mContent.setOpaque(false);
		mTopPanel.setOpaque(false);
		mTopPanel.setPreferredSize(new Dimension(0,100));;
		
		mTopPanel.setLayout(new FlowLayout(FlowLayout.RIGHT,0,0));
		mTopPanel.add(this.mExitButton);
		
		mCardLayout = new CardLayout();
		mContent.setLayout(this.mCardLayout);
		mContent.add(mLoginPanel, mLoginPanel.getClass().getName());
		
				
				
				
	}
}
