package client.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JPanel;

import client.btn.RCExitButton;
import client.panel.RCGameRoomPanel;
import client.panel.RCJoinPanel;
import client.panel.RCLobbyPanel;
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
	private RCLoginPanel mLoginPanel; // 로그인화면
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
		mContent.add(mJoinPanel, mJoingPanel.getClass().getName());
		mContent.add(mLobbyPanel, mLobbyPanel.getClass().getName());
		mContent.add(mGameRoomPanel, mGameRoomPanel.getClass().getName());
		
		mFrame.setGlassPane(mGlassPane);
		
		this.setPreferredSize(new Dimension(RCMainFrame.FRAME_WIDTH,RCMainFrame.FRAME_HEIGHT));
		this.setLayout(new BorderLayout());
		this.add(mTopPanel, BorderLayout.NORTH);
		this.add(createEmptyPanel(0,50), BorderLayout.SOUTH);
		this.add(createEmptyPanel(50,0), BorderLayout.EAST);
		this.add(createEmptyPanel(50,0), BorderLayout.WEST);
		this.add(mContent, BorderLayout.CENTER);
		
		this.setOpaque(false);
		this.initEventHandler();
				
	}
	
	//이벤트 핸들러 초기화 함수 
	public void initEventHandler(){
		this.mExitButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showExitDialog();
			}
		});
		
		this.addMouseMotionListener(new MouseMotionAdapter() {
			Point point;
			
			@Override
			public void mouseDragged(MouseEvent e){
				super.mouseDragged(e);
				if(mIsFullScreen){
					
				} else{
					if(point == null){
						point = e.getPoint();
					}
					mFrame.setLocation(e.getLocationOnScreen().x - point.x, e.getLocationOnScreen().y - point.y);
					
				}
			}
			@Override
			public void mouseMoved(MouseEvent e){
				super.mouseMoved(e);
				point = null;
			}
		});
	}
	//배경 그리기
	public void drawBackgournd(Graphics g){
		Graphics2D g2d = (Graphics2D) g;
		Paint oldPaint = g2d.getPaint();
		
		GradientPaint gradient = new GradientPaint(0, 0, Color.YELLOW, 0, this.getHeight(), Color.YELLOW);
		g2d.setPaint(gradient);
		g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

		g2d.setPaint(oldPaint);
		g2d.drawImage(this.mTitleImage, 35, 10, this.mTitleImage.getWidth(this), this.mTitleImage.getHeight(this), this);
	}
	
	public void addPanel(Component comp, String panelName){
		this.mContent.add(comp, panelName);
	}
	
	private JPanel createEmptyPanel(int width, int height){
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		panel.setOpaque(false);
		return panel;
	}
	
	
}
