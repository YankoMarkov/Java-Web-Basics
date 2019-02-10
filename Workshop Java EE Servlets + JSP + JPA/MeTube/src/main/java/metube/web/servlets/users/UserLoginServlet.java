package metube.web.servlets.users;

import metube.domain.models.bindingModels.UserLoginBindingModel;
import metube.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {


    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public UserLoginServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/users/loginUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginBindingModel userLoginBindingModel = (UserLoginBindingModel) req.getAttribute("userLoginModel");

        if (!this.userService.isValidUser(userLoginBindingModel.getUsername(), userLoginBindingModel.getPassword())) {
            resp.sendRedirect("/login");
            return;
        }
        req.getSession().setAttribute("user", userLoginBindingModel.getUsername());
        resp.sendRedirect("/home");
    }
}
