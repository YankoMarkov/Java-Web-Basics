package panda.web.beans.packages;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Status;
import panda.domain.models.serviceModels.PackageServiceModel;
import panda.domain.models.viewModels.packages.PackagePendingViewModel;
import panda.services.PackageService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class PackagePendingBean {
	
	private PackageService packageService;
	private ModelMapper modelMapper;
	private List<PackagePendingViewModel> packagePendingViewModels;
	
	public PackagePendingBean() {
	}
	
	@PostConstruct
	public void init(){
		this.packagePendingViewModels = new ArrayList<>();
	}
	
	@Inject
	public PackagePendingBean(PackageService packageService, ModelMapper modelMapper) {
		this.packageService = packageService;
		this.modelMapper = modelMapper;
	}
	
	public List<PackagePendingViewModel> getPackagePendingViewModels() {
		List<PackageServiceModel> packageServiceModels = this.packageService.getAllPackagesByStatus(Status.PENDING);
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
	
	public void shipped(String id) throws IOException {
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		PackageServiceModel packageServiceModel = this.packageService.getPackageById(id);
		packageServiceModel.setStatus(Status.SHIPPED);
		packageServiceModel.setEstimatedDeliveryDay(LocalDateTime.now().plusDays(getRandomDays()));
		this.packageService.updatePackage(packageServiceModel);
		context.redirect("/views/packages/packagePending.xhtml");
	}
	
	private int getRandomDays() {
		Random random = new Random();
		return random.nextInt(40 - 20 + 1) + 20;
	}
}
