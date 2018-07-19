package common;

import java.io.Serializable;


public class RequestPacket implements Serializable{
	protected static final long serialVersionUID = 1L;
	
	public static enum SYNC_TYPE{
		SYNCHRONOUS, ASYNCHRONOUS
	};
	
	private String mClassName;
	private String mMethodName;
	private SYNC_TYPE mSyncType;
	private Object[] mArgs;
	
	public RequestPacket(){}
	public RequestPacket(String className, String methodName, Object[] args ){
		this.mClassName = className;
		this.mMethodName = methodName;
		this.mArgs = args;
	}
	public String getmClassName() {
		return mClassName;
	}
	public void setmClassName(String mClassName) {
		this.mClassName = mClassName;
	}
	public String getmMethodName() {
		return mMethodName;
	}
	public void setmMethodName(String mMethodName) {
		this.mMethodName = mMethodName;
	}
	public SYNC_TYPE getmSyncType() {
		return mSyncType;
	}
	public void setmSyncType(SYNC_TYPE mSyncType) {
		this.mSyncType = mSyncType;
	}
	public Object[] getmArgs() {
		return mArgs;
	}
	public void setmArgs(Object[] mArgs) {
		this.mArgs = mArgs;
	}

}
