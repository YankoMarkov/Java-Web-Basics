package panda.services;

import org.modelmapper.ModelMapper;
import panda.domain.entities.Receipt;
import panda.domain.models.serviceModels.ReceiptServiceModel;
import panda.repository.ReceiptRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptServiceImpl implements ReceiptService {

    private final ReceiptRepository receiptRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ReceiptServiceImpl(ReceiptRepository receiptRepository, ModelMapper modelMapper) {
        this.receiptRepository = receiptRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveReceipt(ReceiptServiceModel receiptService) {
        Receipt receipt = this.modelMapper.map(receiptService, Receipt.class);
        this.receiptRepository.save(receipt);
    }

    @Override
    public ReceiptServiceModel getReceiptById(String id) {
        Receipt receipt = this.receiptRepository.findById(id);
        if (receipt == null) {
            return null;
        }
        return this.modelMapper.map(receipt, ReceiptServiceModel.class);
    }

    @Override
    public ReceiptServiceModel getReceiptByPackage(String packageId) {
        Receipt receipt = this.receiptRepository.findByPackage(packageId);
        if (receipt == null) {
            return null;
        }
        return this.modelMapper.map(receipt, ReceiptServiceModel.class);
    }

    @Override
    public List<ReceiptServiceModel> getAllReceiptByUser(String username) {
        List<Receipt> receipts = this.receiptRepository.findAllByUser(username);
        if (receipts == null) {
            return null;
        }
        return receipts.stream()
                .map(receipt -> this.modelMapper.map(receipt, ReceiptServiceModel.class))
                .collect(Collectors.toList());
    }
}
