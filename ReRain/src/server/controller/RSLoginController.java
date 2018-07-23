package server.controller;

public class RSLoginController {
	
	public RSLoginController(){}
	
	//login function
	public Object[] login(String id, String pw){
//		RSLobby lobby = RSLobby.getLobby();
		boolean loginValid = true;
		boolean valid = false;
//		synchronized (lobby) {
			if(id.equals("1") && pw.equals("1")){
				valid = true;
			}
//		}
		return new Object[] { new Boolean(valid), id, pw };
	}
}
