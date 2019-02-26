package panda.web.beans.packages;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.domain.models.serviceModels.UserServiceModel;
import panda.domain.models.viewModels.packages.PackagePendingViewModel;
import panda.services.PackageService;
import panda.services.ReceiptService;
import panda.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackageDeliveredBean {

    private PackageService packageService;
    private UserService userService;
    private ReceiptService receiptService;
    private ModelMapper modelMapper;
    private List<PackagePendingViewModel> packagePendingViewModels;

    public PackageDeliveredBean() {
    }
    
    @PostConstruct
    public void init(){
        this.packagePendingViewModels = new ArrayList<>();
    }

    @Inject
    public PackageDeliveredBean(PackageService packageService, UserService userService, ReceiptService receiptService, ModelMapper modelMapper) {
        this.packageService = packageService;
        this.userService = userService;
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    public List<PackagePendingViewModel> getPackagePendingViewModels() {
        List<PackageServiceModel> packageServiceModels = this.packageService.getAllPackagesByStatus(Status.DELIVERED);
        if (packageServiceModels != null) {
            return packageServiceModels.stream()
                    .map(aPackage -> {
                        PackagePendingViewModel packagePendingViewModel = this.modelMapper.map(aPackage, PackagePendingViewModel.class);
                        packagePendingViewModel.setRecipient(aPackage.getRecipient().getUsername());
                        return packagePendingViewModel;
                    })
                    .collect(Collectors.toList());
        }
        return new ArrayList<>();
    }

    public void acquire(String packageId, String username) throws IOException {
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        PackageServiceModel packageServiceModel = this.packageService.getPackageById(packageId);
        packageServiceModel.setStatus(Status.ACQUIRED);
        createReceipt(username, packageServiceModel);
        ReceiptServiceModel receiptServiceModel = this.receiptService.getReceiptByPackage(packageServiceModel.getId());
        packageServiceModel.setReceipt(receiptServiceModel);
        this.packageService.updatePackage(packageServiceModel);
        context.redirect("/views/users/userHome.xhtml");
    }

    private void createReceipt(String username, PackageServiceModel packageServiceModel) {
        UserServiceModel userServiceModel = this.userService.getUserByUsername(username);
        ReceiptServiceModel receiptServiceModel = new ReceiptServiceModel();
        receiptServiceModel.setFee(BigDecimal.valueOf(packageServiceModel.getWeight()).multiply(BigDecimal.valueOf(2.67)));
        receiptServiceModel.setIssuedOn(LocalDateTime.now());
        receiptServiceModel.setRecipient(userServiceModel);
        receiptServiceModel.setaPackage(packageServiceModel);
        this.receiptService.saveReceipt(receiptServiceModel);
    }
}
