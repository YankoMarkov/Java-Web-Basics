package panda.web.beans.users;

import org.modelmapper.ModelMapper;
import panda.domain.models.bindingModels.users.UserRegisterBindingModel;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

@Named
@RequestScoped
public class UserRegisterBean {

    private UserRegisterBindingModel userRegisterBindingModel;
    private UserService userService;
    private ModelMapper modelMapper;

    public UserRegisterBean() {
    }
    
    @PostConstruct
    public void init(){
        this.userRegisterBindingModel = new UserRegisterBindingModel();
    }

    @Inject
    public UserRegisterBean(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public UserRegisterBindingModel getUserRegisterBindingModel() {
        return userRegisterBindingModel;
    }

    public void setUserRegisterBindingModel(UserRegisterBindingModel userRegisterBindingModel) {
        this.userRegisterBindingModel = userRegisterBindingModel;
    }

    public void registerUser() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel userServiceModel = this.modelMapper.map(this.userRegisterBindingModel, UserServiceModel.class);
        if (this.userRegisterBindingModel.getPassword().equals(this.userRegisterBindingModel.getConfirmPassword()) &&
                !this.userService.userExist(this.userRegisterBindingModel.getUsername())) {
            this.userService.saveUser(userServiceModel);
            context.redirect("/views/users/userLogin.xhtml");
            return;
        }
        context.redirect("/views/users/userRegister.xhtml");
    }
}
