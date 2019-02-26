package panda.web.beans.users;

import org.modelmapper.ModelMapper;
import panda.domain.models.bindingModels.users.UserLoginBindingModel;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Named
@RequestScoped
public class UserLoginBean {

    private UserLoginBindingModel userLoginBindingModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserLoginBean() {
    }
    
    @PostConstruct
    public void init(){
        this.userLoginBindingModel = new UserLoginBindingModel();
    }

    @Inject
    public UserLoginBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserLoginBindingModel getUserLoginBindingModel() {
        return userLoginBindingModel;
    }

    public void setUserLoginBindingModel(UserLoginBindingModel userLoginBindingModel) {
        this.userLoginBindingModel = userLoginBindingModel;
    }

    public void loginUser() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        if (this.userService.isValidUser(this.userLoginBindingModel.getUsername(), this.userLoginBindingModel.getPassword())) {
            UserServiceModel userServiceModel = this.userService.getUserByUsername(this.userLoginBindingModel.getUsername());

            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            session.setAttribute("username", userServiceModel.getUsername());
            session.setAttribute("role", userServiceModel.getRole());

            context.redirect("/views/users/userHome.xhtml");
            return;
        }
        context.redirect("/views/users/userLogin.xhtml");
    }
}
