package gui.session.java;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PageFilter implements Filter {

	private static final String MANAGED_BEAN_NAME = "userSession";
	private static final String COOKIE_NAME = "pageFilter";
	private static final int COOKIE_MAX_AGE = 31536000; // 60*60*24*365 seconds; 1 year.

	public void init(FilterConfig filterConfig) {
        // Just do your DAO thing. You can make the databaseName a context init-param as well.
        // Also see the DAO tutorial.
      
    }
	public void destroy() {
		// TODO Auto-generated method stub
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession sess  = httpRequest.getSession();
		UserSession userSession = (UserSession) sess.getAttribute(MANAGED_BEAN_NAME);
		
		String pathInfo = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (pathInfo.startsWith("/resources")) {
            chain.doFilter(request, response);
            return;
        }
        
        
		if(userSession==null){
			HashMap<String, UserSession> map = (HashMap<String, UserSession>) UserSessionDao.getObject().getSessionMap();
			//No user session found in httpRequest
			
			//Getting ip address of user for case in which user is one time user.
			InetAddress ip2  = Inet4Address.getLocalHost();
			String resolvedIp = ip2.getHostAddress();
			//Create cookie id for new user
			String cookieId = getCookieValue(httpRequest);
			if(cookieId!=null){
				//Try to find userSession from cookie id stored in HashMap
				userSession = map.get(cookieId);
			}
			if(userSession==null){
				
				//Cookie not found, may be it is expired( if user Session not found) or hackers(if cookie does not exits)! So generate one.
				String newCookieId = UUID.randomUUID().toString();
				//newCookieId +=resolvedIp;
				
				userSession = new UserSession(newCookieId, resolvedIp);
				// Put ID in cookie.
               
                setCookieValue(httpResponse, COOKIE_NAME, cookieId, COOKIE_MAX_AGE);
				//Store in HashMap this userSession in cookieId
				map.put(newCookieId, userSession);
			}
			
			//Set user session in current http request
			sess.setAttribute(MANAGED_BEAN_NAME, userSession);
		}
		
		
		//If user session is found 
		String reqURI = httpRequest.getRequestURI();
		if (reqURI.indexOf("/admin") >= 0 && !(reqURI.indexOf("/adminLogin")>=0)){
			
			if(userSession.getAdminUser() ==null){
				httpResponse.sendRedirect(httpRequest.getContextPath() + "/pages/public/allProducts.xhtml");
			}
			 
		}
		String newCurrentPage = ((HttpServletRequest) request).getServletPath();
		if (sess.getAttribute("currentPage") == null) {
			sess.setAttribute("lastPage", newCurrentPage);
			sess.setAttribute("currentPage", newCurrentPage);
			
		} else {
			String oldCurrentPage = sess.getAttribute("currentPage").toString();
			if (!oldCurrentPage.equals(newCurrentPage)) {
				sess.setAttribute("lastPage", oldCurrentPage);
				sess.setAttribute("currentPage", newCurrentPage);
			}
		}
		chain.doFilter(request, response);
	}

	private void setCookieValue(HttpServletResponse response, String name, String cookieId,
			int maxAge) {
		Cookie cookie = new Cookie(name, cookieId);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
		
	}

	private String getCookieValue(HttpServletRequest httpRequest) {
		Cookie[] cookies = httpRequest.getCookies();
		for(Cookie cookie : cookies){
			if (cookie!=null && cookie.getName().equals(COOKIE_NAME)){
				return cookie.getValue();
			}
			
		}
		return null;
	}

}
