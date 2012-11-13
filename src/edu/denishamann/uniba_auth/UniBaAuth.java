package edu.denishamann.uniba_auth;

/*********************************************************************
 * class for checking a Uni Ba Login
 *
 * 
 * 
 * @author
 *    Denis Hamann
 * @version
 *    13.11.2012
 *********************************************************************/
 
public class UniBaAuth {

	public static void main(String[] args) {
		new UniBaAuth();
	}
	
	public UniBaAuth() {
		if(checkLogin("ba035847", "start123")){
			System.out.println("login ok");
		}else{
			System.out.println("login not ok");
		}
	}
	
	public boolean checkLogin(String sLogin, String sPWD){
		boolean bReturn=false;
		
		String sPuffer="";
		
		sPuffer=HttpHelperClass.getBasicAuthPage("https://flexnow.zuv.uni-bamberg.de/", sLogin, sPWD);
		
		if(sPuffer.contains("<TITLE>")){
			bReturn=true;
		}
		
		return bReturn;
	}
	
}
