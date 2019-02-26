package panda.web.filters;

import panda.domain.entities.Role;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/index",
		"/login",
		"/register"})
public class AdministratorFilter implements Filter {
	
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		
		HttpSession session = request.getSession();
		if (session.getAttribute("username") != null && session.getAttribute("role").equals(Role.ADMIN)) {
			response.sendRedirect("/views/users/userHome.xhtml");
			return;
		}
		
		filterChain.doFilter(request, response);
	}
}
