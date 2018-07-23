package server.network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import common.Constant;

public class RSServerManager {
	private ServerSocket mServerSocket;
//	private RSLobby mLobby;
	
	private boolean isRunning = true;
	
	public RSServerManager(){
//		mLobby = RSLobby.getLobby();
		try{
			// 두번째 인자는 backlog값 일반적으로 요청대기 상태인 클라이언트를 큐에 대기시킴 큐에 들어갈 수 있는 개수
			mServerSocket = new ServerSocket(Constant.SERVER_PORT,10);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void startServer(){
		System.out.println("running server...");
		
		while(isRunning){
			try{
				Socket socket = mServerSocket.accept();
				RSSocketManager manager = new RSSocketManager(socket,this);
//				mLobby.addSocketManager(manager);
				manager.start();
			} catch(IOException e){
				e.printStackTrace();
				isRunning = false;
			}
		}
	}
	
//	public RSLobby getLobby(){
//		resturn
//	}

}
