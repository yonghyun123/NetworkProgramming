package client.gui;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class RCGlassPane extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//component
	private JComponent mComponent; //그리고자 하는 컴포넌트
	//constructor
	public RCGlassPane(){}
	//init
	public void init(){
		this.setPreferredSize(new Dimension(RCMainFrame.FRAME_WIDTH, RCMainFrame.FRAME_HEIGHT));
		this.setOpaque(false);
		this.initEventHandler();
	}
	
	private void initEventHandler(){
		this.addComponentListener(new ComponentAdapter(){
			public void componentShown(ComponentEvent e){
				requestFocusInWindow();
			}
		});
		
		this.addMouseListener(new MouseAdapter() {});
		this.addMouseMotionListener(new MouseMotionAdapter() {});
		this.addKeyListener(new KeyAdapter() {});
		this.setFocusTraversalKeysEnabled(false);
	}
	
	//컴포넌트 설정 함수
	public void setComponent(JComponent comp){
		if(mComponent != null){
			this.remove(mComponent);
		}
		mComponent = comp;
		int vgap = (this.getHeight()/2 - (int) (mComponent.getPreferredSize().getHeight())/2);
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 0, vgap));
		this.add(mComponent);
	}
	@Override
	protected void paintComponent(Graphics g){
		if(this.isVisible()){
			Graphics2D g2d = (Graphics2D) g;
			Composite oldComposite = g2d.getComposite();
			g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));;
			g2d.setColor(Color.white);
			g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
			g2d.setComposite(oldComposite);
		}
		super.paintComponent(g);
	}
	
	
	
	
}
