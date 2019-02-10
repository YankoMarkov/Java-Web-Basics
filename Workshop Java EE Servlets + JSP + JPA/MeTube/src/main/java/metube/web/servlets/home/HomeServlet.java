package metube.web.servlets.home;

import metube.domain.models.serviceModels.UserServiceModel;
import metube.domain.models.viewModels.users.UserProfileViewModel;
import metube.services.UserService;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    @Inject
    public HomeServlet(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    
        UserServiceModel userServiceModel = this.userService.getUserByUsername((req.getSession().getAttribute("user")).toString());
        UserProfileViewModel userProfileViewModel = this.modelMapper.map(userServiceModel, UserProfileViewModel.class);
        
        req.setAttribute("tubeModel", null);
        req.setAttribute("tubeModel", userProfileViewModel.getTubes());
        
        req.getRequestDispatcher("/home/home.jsp").forward(req, resp);
    }
}
