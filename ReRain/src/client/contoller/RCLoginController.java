package client.contoller;

import client.network.RCClientStub;
import client.panel.RCLoginPanel;
import common.RequestPacket;
import common.ResponsePacket;
import common.RequestPacket.SYNC_TYPE;

public class RCLoginController extends RCClientStub{
	//componenet
	
	RCLoginPanel mLoginPanel; //login panel
	
	public RCLoginController(String serverIP, RCLoginPanel loginPanel){
		super(serverIP);
		mLoginPanel = loginPanel;
	}
	
	//login function
	public void login(String id, String pw){
//		mLoginPanel.getContentPane().showLoadingDialog("로그인 중입니다.");
		RequestPacket packet = new RequestPacket();
		packet.setmClassName("RSLoginContoller");
		packet.setmMethodName("login");
		packet.setmSyncType(SYNC_TYPE.SYNCHRONOUS);
		packet.setmArgs(new Object[] {id, pw});
		this.send(packet);
	}
	
	@Override
	public void run(){
		while(isConnected){
			try{
				Object obj = inputStream.readObject();
				ResponsePacket responsePacket = (ResponsePacket)obj;
				if(responsePacket.getmResponseType().equals("login")){
					System.out.println("서버에서 login으로 받음");
//					this.resultLogin(responsePacket);
				} else {
					// no one else throw
				}
			} catch(Exception e){
				e.printStackTrace();
				isConnected = false;
			}
		}
	}
}
