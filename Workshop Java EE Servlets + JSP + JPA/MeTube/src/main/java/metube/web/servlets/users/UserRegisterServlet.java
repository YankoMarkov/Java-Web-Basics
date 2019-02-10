package metube.web.servlets.users;

import metube.domain.models.bindingModels.UserRegisterBindingModel;
import metube.domain.models.serviceModels.UserServiceModel;
import metube.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class UserRegisterServlet extends HttpServlet {

    private final UserService userService;
    private final ModelMapper modelMapper;

    @Inject
    public UserRegisterServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/users/registerUser.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegisterBindingModel userRegisterBindingModel = (UserRegisterBindingModel) req.getAttribute("userRegisterModel");

        if (!userRegisterBindingModel.getPassword().equals(userRegisterBindingModel.getConfirmPass())) {
            resp.sendRedirect("/register");
        } else if (this.userService.saveUser(this.modelMapper.map(userRegisterBindingModel, UserServiceModel.class))) {
            resp.sendRedirect("/login");
        } else {
            resp.sendRedirect("/register");
        }
    }
}
