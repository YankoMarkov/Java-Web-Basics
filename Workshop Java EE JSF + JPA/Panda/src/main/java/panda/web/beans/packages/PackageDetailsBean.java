package panda.web.beans.packages;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.packages.PackageDetailsViewModel;
import panda.services.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class PackageDetailsBean {

    private PackageService packageService;
    private ModelMapper modelMapper;
    private PackageDetailsViewModel packageDetailsViewModel;

    public PackageDetailsBean() {
    }
    
    @PostConstruct
    public void init(){
        this.packageDetailsViewModel = new PackageDetailsViewModel();
    }

    @Inject
    public PackageDetailsBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    public PackageDetailsViewModel getPackageDetailsViewModel() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id = request.getParameter("id");
        PackageServiceModel packageServiceModel = this.packageService.getPackageById(id);
        this.packageDetailsViewModel = this.modelMapper.map(packageServiceModel, PackageDetailsViewModel.class);
        this.packageDetailsViewModel.setRecipient(packageServiceModel.getRecipient().getUsername());
        if (packageServiceModel.getStatus().equals(Status.PENDING)) {
            this.packageDetailsViewModel.setEstimatedDeliveryDay("N/A");
        } else {
            if (packageServiceModel.getStatus().equals(Status.DELIVERED) || packageServiceModel.getStatus().equals(Status.ACQUIRED)) {
                this.packageDetailsViewModel.setEstimatedDeliveryDay("Delivered");
            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                String date = packageServiceModel.getEstimatedDeliveryDay().format(formatter);
                this.packageDetailsViewModel.setEstimatedDeliveryDay(date);
            }
        }
        return this.packageDetailsViewModel;
    }
}
