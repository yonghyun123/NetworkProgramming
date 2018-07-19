package server;

import common.Constant;
import server.network.RSServerManager;

public class ServerMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RSServerManager con = new RSServerManager();;
		con.startServer();
	}

}
