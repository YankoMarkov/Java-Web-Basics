package panda.web.beans.packages;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.bindingModels.packages.PackageCreateBindingModel;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.domain.models.viewModels.users.UserAllViewModel;
import panda.services.PackageService;
import panda.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageCreateBean {

    private PackageCreateBindingModel packageCreateBindingModel;
    private PackageService packageService;
    private UserService userService;
    private ModelMapper modelMapper;
    private List<UserAllViewModel> allUsers;

    public PackageCreateBean() {
    }
    
    @PostConstruct
    public void init(){
        this.packageCreateBindingModel = new PackageCreateBindingModel();
        this.allUsers = new ArrayList<>();
    }

    @Inject
    public PackageCreateBean(PackageService packageService, UserService userService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public PackageCreateBindingModel getPackageCreateBindingModel() {
        return packageCreateBindingModel;
    }

    public void setPackageCreateBindingModel(PackageCreateBindingModel packageCreateBindingModel) {
        this.packageCreateBindingModel = packageCreateBindingModel;
    }

    public List<UserAllViewModel> getAllUsers() {
        List<UserServiceModel> userServiceModels = this.userService.getAllUsers();
        return userServiceModels.stream()
                .map(user -> this.modelMapper.map(user, UserAllViewModel.class))
                .collect(Collectors.toList());
    }

    public void createPackage() throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        UserServiceModel userServiceModel = this.userService.getUserByUsername(this.packageCreateBindingModel.getRecipient());
        PackageServiceModel packageServiceModel = this.modelMapper.map(this.packageCreateBindingModel, PackageServiceModel.class);
        packageServiceModel.setRecipient(userServiceModel);
        packageServiceModel.setStatus(Status.PENDING);
        this.packageService.savePackage(packageServiceModel);
        context.redirect("/views/users/userHome.xhtml");
    }
}
