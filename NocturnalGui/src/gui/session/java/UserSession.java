package gui.session.java;

import java.util.Date;

import gui.admin.java.AdminUser;
import gui.main.model.java.CustomerDetails;

public class UserSession {
	
	private String cookieId;
	
    private CustomerDetails customer;
    private AdminUser adminUser;
    private Date creationDate;
    private Date lastVisit;
    private int hits;
    private String userIp;

    // Constructors -------------------------------------------------------------------------------

    /**
     * Default constructor. 
     */
    public UserSession() {
        // Keep it alive.
    }

    /**
     * Construct new usersession with given cookie ID. 
     * @param ip 
     */
    public UserSession(String cookieId, String ip) {
        this.cookieId = cookieId;
        this.creationDate = new Date();
        this.lastVisit = new Date();
        this.userIp=ip;
    }

    // Getters and setters ------------------------------------------------------------------------

    // Implement default getters and setters here the usual way.

    // Helpers ------------------------------------------------------------------------------------

    /**
     * Add hit (pageview) to the UserSession. Not necessary, but nice for stats.
     */
    public void addHit() {
        this.hits++;
        this.lastVisit = new Date();
    }

    /**
     * A convenience method to check if User is logged in.
     */
    
    
    public boolean isLoggedIn() {
        return adminUser != null || userIp !=null;
    }

	public String getUserIp() {
		return userIp;
	}

	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getCookieId() {
		return cookieId;
	}

	public void setCookieId(String cookieId) {
		this.cookieId = cookieId;
	}

	public CustomerDetails getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerDetails customer) {
		this.customer = customer;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getLastVisit() {
		return lastVisit;
	}

	public void setLastVisit(Date lastVisit) {
		this.lastVisit = lastVisit;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}


    
}
