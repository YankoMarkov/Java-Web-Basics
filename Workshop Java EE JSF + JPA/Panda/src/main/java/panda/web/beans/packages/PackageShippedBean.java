package panda.web.beans.packages;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.packages.PackageShippedViewModel;
import panda.services.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageShippedBean {

    private PackageService packageService;
    private ModelMapper modelMapper;
    private List<PackageShippedViewModel> packageShippedViewModels;

    public PackageShippedBean() {
    }
    
    @PostConstruct
    public void init(){
        this.packageShippedViewModels = new ArrayList<>();
    }

    @Inject
    public PackageShippedBean(PackageService packageService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.modelMapper = modelMapper;
    }

    public List<PackageShippedViewModel> getPackageShippedViewModels() {
        List<PackageServiceModel> packageServiceModels = this.packageService.getAllPackagesByStatus(Status.SHIPPED);
        if (packageServiceModels != null) {
            return packageServiceModels.stream()
                    .map(aPackage -> {
                        PackageShippedViewModel packageShippedViewModel = this.modelMapper.map(aPackage, PackageShippedViewModel.class);
                        packageShippedViewModel.setRecipient(aPackage.getRecipient().getUsername());
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                        String date = aPackage.getEstimatedDeliveryDay().format(formatter);
                        packageShippedViewModel.setEstimatedDeliveryDay(date);
                        return packageShippedViewModel;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void delivered(String id) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        PackageServiceModel packageServiceModel = this.packageService.getPackageById(id);
        packageServiceModel.setStatus(Status.DELIVERED);
        this.packageService.updatePackage(packageServiceModel);
        context.redirect("/views/packages/packageShipped.xhtml");
    }
}
