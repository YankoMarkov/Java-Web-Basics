package panda.web.beans.receipts;

import org.modelmapper.ModelMapper;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.domain.models.viewModels.receipt.ReceiptDetailsViewModel;
import panda.services.ReceiptService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import java.time.format.DateTimeFormatter;

@Named
@RequestScoped
public class ReceiptDetailsBean {

    private ReceiptService receiptService;
    private ModelMapper modelMapper;
    private ReceiptDetailsViewModel receiptDetailsViewModel;

    public ReceiptDetailsBean() {
    }
    
    @PostConstruct
    public void init(){
        this.receiptDetailsViewModel = new ReceiptDetailsViewModel();
    }

    @Inject
    public ReceiptDetailsBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    public ReceiptDetailsViewModel getReceiptDetailsViewModel() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        String id = request.getParameter("id");
        ReceiptServiceModel receiptServiceModel = this.receiptService.getReceiptById(id);
        this.receiptDetailsViewModel = this.modelMapper.map(receiptServiceModel, ReceiptDetailsViewModel.class);
        this.receiptDetailsViewModel.setRecipient(receiptServiceModel.getRecipient().getUsername());
        this.receiptDetailsViewModel.setDeliveryAddress(receiptServiceModel.getaPackage().getShippingAddress());
        this.receiptDetailsViewModel.setPackageDescription(receiptServiceModel.getaPackage().getDescription());
        this.receiptDetailsViewModel.setPackageWeight(receiptServiceModel.getaPackage().getWeight());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        String date = receiptServiceModel.getIssuedOn().format(formatter);
        this.receiptDetailsViewModel.setIssuedOn(date);
        return this.receiptDetailsViewModel;
    }
}
