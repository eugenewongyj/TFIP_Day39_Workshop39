package sg.edu.nus.iss.TfipDay29Project1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.iss.TfipDay29Project1.models.PurchaseOrder;
import sg.edu.nus.iss.TfipDay29Project1.repositories.PurchaseOrderRepository;
import sg.edu.nus.iss.TfipDay29Project1.utils.Utils;

@Service
public class PurchaseOrderService {

    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public String savePurchaseOrder(PurchaseOrder purchaseOrder) {
        // set uuid for PurchaseOrder
        purchaseOrder.setId(Utils.generateUuid());
        
        // not required anymore because the lineitems are embedded into purchaseorder
        // set uuid for LineItem
        // purchaseOrder.getLineItems().forEach(lineItem -> {
        //     lineItem.setId(Utils.generateUuid());
        // });

        purchaseOrderRepository.savePurchaseOrder(purchaseOrder);
        return purchaseOrder.getId();
    }
    
}
