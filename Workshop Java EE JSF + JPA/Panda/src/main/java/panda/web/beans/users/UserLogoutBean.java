package panda.web.beans.users;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLogoutBean {
	
	public void logoutUser() throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		
		context.redirect("/index.xhtml");
	}
}
