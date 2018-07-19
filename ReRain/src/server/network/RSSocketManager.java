package server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

import common.RequestPacket;
import common.ResponsePacket;
import common.RequestPacket.SYNC_TYPE;

public class RSSocketManager extends Thread implements Runnable {
	private ObjectOutputStream outputStream;
	private ObjectInputStream inputStream;
	private String mManagerName;
	
	private boolean isConnected = true;
	
	private RSServerManager parent;
	
	public RSSocketManager(Socket socket, RSServerManager parent){
		this.parent = parent;
		
		try{
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void send(Object obj){
		try{
			outputStream.writeObject(obj);
			outputStream.flush();
		} catch(IOException e){
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(isConnected){
			try{
				Object obj = inputStream.readObject();
				RequestPacket requestPacket = (RequestPacket)obj;
				System.out.println("server get.."+ requestPacket.getmMethodName());
				
				if(mManagerName == null){
					mManagerName = requestPacket.getmClassName();
				}
				
				if(requestPacket.getmMethodName().equals("init")){
//					this.initPlayer( (String)requestPacket.getmArgs()[0]);
					continue;
				}
				
				Class<?> clz = ClassLoader.getSystemClassLoader().loadClass("server.controller."+mManagerName);
				Object classObj = clz.newInstance();
				Method[] methods = clz.getMethods();
				Method targetMethod = null;
				
				for(Method itemMethod : methods){
					if(itemMethod.getName().equals(requestPacket.getmMethodName())){
						targetMethod = itemMethod;
					}
				}
				
				if(requestPacket.getmSyncType() == SYNC_TYPE.SYNCHRONOUS){
					Object[] returnValue = (Object[]) targetMethod.invoke(classObj, requestPacket.getmArgs());
					ResponsePacket responsePacket = new ResponsePacket(requestPacket.getmMethodName(), returnValue);
					outputStream.writeObject(requestPacket);
				} else{
					targetMethod.invoke(classObj, requestPacket.getmArgs());
				}
				
			}catch(Exception e){
				System.out.println("클라이언트 접속종료:");
//				parent.getLobby().removeSocketManager(this);
				isConnected = false;
			}
		}
	}
	
//	private void initPlayer(String id){
//		RSLobby lobby = RSLobby.getLobby();
//		synchronized (lobby) {
//			lobby.moveManager(id,this);
//		}
//	}
	
	public String getSocketManagerName(){
		return mManagerName;
	}

}
