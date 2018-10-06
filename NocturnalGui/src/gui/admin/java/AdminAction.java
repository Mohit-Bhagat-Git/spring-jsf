package gui.admin.java;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gui.application.exceptions.GuiException;
import gui.main.java.DbConnector;
import gui.session.java.UserSession;
import nocturnal.host.exception.HostException;
import nocturnal.host.exception.UserLoginException;

@ManagedBean
@SessionScoped
public class AdminAction {
	
	private static final Logger logger = LogManager.getLogger(AdminAction.class.getName());
	@ManagedProperty(value = "#{adminUser}")
	private AdminUser admin;
	
	@ManagedProperty(value = "#{dbConnector}")
	private DbConnector dbConn;

	
	public String login(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			 admin = dbConn.adminLogin(admin);
			
			HttpSession session  = (HttpSession) context.getExternalContext().getSession(false);
			UserSession userSession = (UserSession) session.getAttribute("userSession");
			
			//We must get userSession to ensure session activity.
			if(userSession==null){
				context.addMessage(null, new FacesMessage("Could not log you in. Please try again!"));
				ExternalContext ec = context.getExternalContext();
				String rootPath = ec.getApplicationContextPath();
				ec.redirect(rootPath+"/pages/admin/adminLogin.xhtml");
				return "adminLogin.xhtml?faces-redirect=true";
			}
			userSession.setAdminUser(admin);
			context.addMessage(null, new FacesMessage("Login Success"));
			return "adminActions?faces-redirect=true";
			
		} catch (GuiException e) {
			context.addMessage(null, new FacesMessage("Failure reason: "+e.getMessage()));
		} catch (HostException e) {
			context.addMessage(null, new FacesMessage("Failure reason: "+e.getMessage()));
		} catch (UserLoginException e) {
			context.addMessage(null, new FacesMessage("Failure reason: "+e.getMessage()));
		}
		catch(Exception e){
			context.addMessage(null, new FacesMessage("Unknown reason: "+e.getMessage()));
			logger.error(e);
		}
		return null;
		
	}


	
	public DbConnector getDbConn() {
		return dbConn;
	}



	public void setDbConn(DbConnector dbConn) {
		this.dbConn = dbConn;
	}



	public AdminUser getAdmin() {
		return admin;
	}


	public void setAdmin(AdminUser admin) {
		this.admin = admin;
	}

	
}
