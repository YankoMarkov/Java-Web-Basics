package panda.web.beans.users;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.packages.PackageHomeViewModel;
import panda.services.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class userHomeBean {

    private PackageService packageService;
    private ModelMapper modelMapper;
    private List<PackageHomeViewModel> pendingPackageHomeViewModels;
    private List<PackageHomeViewModel> shippedPackageHomeViewModels;
    private List<PackageHomeViewModel> deliveredPackageHomeViewModels;

    public userHomeBean() {
    }
    
    @PostConstruct
    public void init(){
        this.pendingPackageHomeViewModels = new ArrayList<>();
        this.shippedPackageHomeViewModels = new ArrayList<>();
        this.deliveredPackageHomeViewModels = new ArrayList<>();
    }

    @Inject
    public userHomeBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    public List<PackageHomeViewModel> getPendingPackageHomeViewModels() {
        List<PackageServiceModel> packageServiceModels = this.packageService.getAllPackagesByUserAndStatus(getUsername(), Status.PENDING);
        return packageServiceModels.stream()
                .map(pack -> this.modelMapper.map(pack, PackageHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageHomeViewModel> getShippedPackageHomeViewModels() {
        List<PackageServiceModel> packageServiceModels = this.packageService.getAllPackagesByUserAndStatus(getUsername(), Status.SHIPPED);
        return packageServiceModels.stream()
                .map(pack -> this.modelMapper.map(pack, PackageHomeViewModel.class))
                .collect(Collectors.toList());
    }

    public List<PackageHomeViewModel> getDeliveredPackageHomeViewModels() {
        List<PackageServiceModel> packageServiceModels = this.packageService.getAllPackagesByUserAndStatus(getUsername(), Status.DELIVERED);
        return packageServiceModels.stream()
                .map(pack -> this.modelMapper.map(pack, PackageHomeViewModel.class))
                .collect(Collectors.toList());
    }

    private String getUsername() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        return (String) session.getAttribute("username");
    }
}
