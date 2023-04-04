package sg.edu.nus.iss.TfipDay29Project1.repositories;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.TfipDay29Project1.models.PurchaseOrder;
import sg.edu.nus.iss.TfipDay29Project1.utils.Utils;

import static sg.edu.nus.iss.TfipDay29Project1.Constants.*;

@Repository
public class PurchaseOrderRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public void savePurchaseOrder(PurchaseOrder purchaseOrder) {
        Document documentPurchaseOrder = Utils.purchaseOrderToDocument(purchaseOrder);
        documentPurchaseOrder = mongoTemplate.insert(documentPurchaseOrder, COLLECTION_PURCHASEORDER);
        
        // ObjectId id = documentPurchaseOrder.getObjectId(FIELD_UNDERSCOREID);
        // return id.toString();
    }
    
}
