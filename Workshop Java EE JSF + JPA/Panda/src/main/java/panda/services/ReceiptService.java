package panda.services;

import panda.domain.models.serviceModels.ReceiptServiceModel;

import java.util.List;

public interface ReceiptService {

    void saveReceipt(ReceiptServiceModel receiptService);

    ReceiptServiceModel getReceiptById(String id);

    ReceiptServiceModel getReceiptByPackage(String packageId);

    List<ReceiptServiceModel> getAllReceiptByUser(String username);
}
