package panda.web.beans.receipts;

import org.modelmapper.ModelMapper;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.domain.models.viewModels.receipt.ReceiptViewModel;
import panda.services.ReceiptService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class ReceiptBean {

    private ReceiptService receiptService;
    private ModelMapper modelMapper;
    private List<ReceiptViewModel> receiptViewModels;

    public ReceiptBean() {
    }
    
    @PostConstruct
    public void init(){
        this.receiptViewModels = new ArrayList<>();
    }

    @Inject
    public ReceiptBean(ReceiptService receiptService, ModelMapper modelMapper) {
        this.receiptService = receiptService;
        this.modelMapper = modelMapper;
    }

    public List<ReceiptViewModel> getReceiptViewModels() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        String username = (String) session.getAttribute("username");
        List<ReceiptServiceModel> receiptServiceModels = this.receiptService.getAllReceiptByUser(username);
        return receiptServiceModels.stream()
                .map(receipt -> {
                    ReceiptViewModel receiptViewModel = this.modelMapper.map(receipt, ReceiptViewModel.class);
                    receiptViewModel.setRecipient(receipt.getRecipient().getUsername());
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
                    String date = receipt.getIssuedOn().format(formatter);
                    receiptViewModel.setIssuedOn(date);
                    return receiptViewModel;
                })
                .collect(Collectors.toList());
    }
}
