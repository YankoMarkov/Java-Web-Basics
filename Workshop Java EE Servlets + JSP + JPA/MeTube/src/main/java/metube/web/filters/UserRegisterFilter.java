package metube.web.filters;

import metube.domain.models.bindingModels.UserRegisterBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/register")
public class UserRegisterFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        if (req.getMethod().equalsIgnoreCase("post")) {
            UserRegisterBindingModel userRegisterBindingModel = new UserRegisterBindingModel();

            userRegisterBindingModel.setUsername(req.getParameter("username"));
            userRegisterBindingModel.setPassword(req.getParameter("password"));
            userRegisterBindingModel.setConfirmPass(req.getParameter("confirmPassword"));
            userRegisterBindingModel.setEmail(req.getParameter("email"));

            req.setAttribute("userRegisterModel", userRegisterBindingModel);
        }

        filterChain.doFilter(req, resp);
    }
}
