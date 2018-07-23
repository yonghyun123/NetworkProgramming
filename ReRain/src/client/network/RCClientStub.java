package client.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import common.Constant;

public class RCClientStub extends Thread{
	
	protected Socket mSocket;
	protected ObjectOutputStream outputStream;
	protected ObjectInputStream inputStream;
	
	protected boolean isConnected = true;
	
	public RCClientStub(String serverIP){
		try{
			mSocket = new Socket(serverIP, Constant.SERVER_PORT);
			outputStream = new ObjectOutputStream(mSocket.getOutputStream());
			inputStream = new ObjectInputStream(mSocket.getInputStream());
		}catch(UnknownHostException e){
			e.printStackTrace();
		}catch (IOException e) {
			 e.printStackTrace();
		}
	}
	
	public void send(Object obj){
		try{
			outputStream.writeObject(obj);
			outputStream.flush();
		} catch (Exception e){
			e.printStackTrace();
		}
	}


}
