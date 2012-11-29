package applayer.core.controller;

public class SessionManager {
	
	private boolean isLoggedIn;
	private String userClass;
	private String userName;
	
	public String[] getSession() {
		String[] sessionInfo = { new Boolean(this.isLoggedIn).toString(), this.userClass, this.userName };
		return sessionInfo;
	}
	
	public void setSession() {
		// Set the session
	}

}
