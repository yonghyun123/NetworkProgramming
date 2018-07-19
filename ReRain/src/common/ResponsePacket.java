package common;

import java.io.Serializable;

public class ResponsePacket implements Serializable{
	protected static final long serialVersionUID = 1L;
	
	private Object[] mArgs;
	private String mResponseType;
	
	public ResponsePacket(){}
	public ResponsePacket(String responseType, Object[] args){
		mResponseType = responseType;
		mArgs = args;
	}
	public Object[] getmArgs() {
		return mArgs;
	}
	public void setmArgs(Object[] mArgs) {
		this.mArgs = mArgs;
	}
	public String getmResponseType() {
		return mResponseType;
	}
	public void setmResponseType(String mResponseType) {
		this.mResponseType = mResponseType;
	}

}
