package metube.web.filters;

import metube.domain.models.bindingModels.UserLoginBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/login")
public class UserLoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase("post")) {
            UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();

            userLoginBindingModel.setUsername(req.getParameter("username"));
            userLoginBindingModel.setPassword(req.getParameter("password"));

            req.setAttribute("userLoginModel", userLoginBindingModel);
        }

        filterChain.doFilter(req, resp);
    }
}
