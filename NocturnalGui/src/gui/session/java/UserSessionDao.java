package gui.session.java;

import java.util.HashMap;
import java.util.Map;

public class UserSessionDao {

	private static UserSessionDao userSessionDao;
	private final static Object obj = new Object();
	private Map<String, UserSession> sessionMap;
	
	private UserSessionDao(){
		sessionMap = new HashMap<String, UserSession>();
	}
	
	public static UserSessionDao getObject(){
		if(userSessionDao==null){
			synchronized (obj) {
				if(userSessionDao==null){
					userSessionDao = new UserSessionDao();
				}
			}
		}
		return userSessionDao;
	}

	public Map<String, UserSession> getSessionMap() {
		return sessionMap;
	}

	public void setSessionMap(Map<String, UserSession> sessionMap) {
		this.sessionMap = sessionMap;
	}
	
}
