package sg.edu.nus.iss.TfipDay29Project1.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bson.Document;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import sg.edu.nus.iss.TfipDay29Project1.models.LineItem;
import sg.edu.nus.iss.TfipDay29Project1.models.PurchaseOrder;

public class Utils {

    public static String generateUuid() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    public static JsonObject jsonStringToJsonObject(String jsonString) {
        Reader reader = new StringReader(jsonString);
        JsonReader jsonReader = Json.createReader(reader);
        return jsonReader.readObject();
    }

    public static JsonObject jsonStringToJsonObject2(String payload) {

        JsonObject jsonObject = Json.createObjectBuilder().build();

        try (InputStream is = new ByteArrayInputStream(payload.getBytes())) {
            JsonReader reader = Json.createReader(is);
            jsonObject = reader.readObject();

        } catch(Exception e) {

        }
        return jsonObject;
    }

    public static PurchaseOrder jsonObjectToPurchaseOrder(JsonObject jsonObject) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();

        purchaseOrder.setName(jsonObject.getString("name"));
        purchaseOrder.setEmail(jsonObject.getString("email"));
        // For now, I will continue to have the deliveryDate data type set as string
        // Chuk's solution has the deliveryDate data type as date but he does not convert the string from FE
        // Instead he does this:
		// order.setDeliveryDate(new Date());
        purchaseOrder.setDeliveryDate(jsonObject.getString("deliveryDate"));

        // Extra information
        // Can technically use for loop here
        // Why need to map to JsonObject? It is because getJsonArray returns an array of jsonvalues
        // Look at previous examples to see whether there was a need to filter. 
        // so far the only time that a filter was required was the redis project. this is
        // because the result coming out from redis is object so there is a need to cast back into the class that you want
        
        
        
        
        // If you want to do this, you need to initialize the list in purchaseorder
        // jsonObject.getJsonArray("lineItems").stream()
        //     // .filter(JsonObject.class::isInstance) // this is not required because the jsonValue has to be an object
        //     .map(JsonObject.class::cast)
        //     .map(Utils::jsonObjectToLineItem)
        //     .forEach(purchaseOrder::addLineItem);
        
        // return purchaseOrder;

        // alternative way. chuk's method
        List<LineItem> lineItemList = jsonObject.getJsonArray("lineItems")
                                            .stream()           
                                            .map(v -> (JsonObject)v)
                                            .map(v -> jsonObjectToLineItem(v))
                                            .toList();
        purchaseOrder.setLineItems(lineItemList);

        return purchaseOrder; 
     
    }

    public static LineItem jsonObjectToLineItem(JsonObject jsonObject) {
        LineItem lineItem = new LineItem();
        lineItem.setItem(jsonObject.getString("item"));
        lineItem.setQuantity(jsonObject.getInt("quantity"));

        return lineItem;
    }

    public static Document purchaseOrderToDocument(PurchaseOrder purchaseOrder) {
        Document document = new Document();
        document.put("id", purchaseOrder.getId());
        document.put("name", purchaseOrder.getName());
        document.put("email", purchaseOrder.getEmail());
        document.put("deliveryDate", purchaseOrder.getDeliveryDate());
        List<Document> documentList = purchaseOrder.getLineItems()
                                        .stream()
                                        .map(Utils::lineItemToDocument)
                                        .toList();
        document.put("lineItems", documentList);   
        return document;  
        
        // alternatively I can put in an empty list first into document
        // and add into the list one by one

        // List<Document> documentList = new ArrayList<>();
        // document.put("lineItems2", documentList);
        // purchaseOrder.getLineItems()
        //                 .stream()
        //                 .map(Utils::lineItemToDocument)
        //                 .forEach(documentList::add);
        // return document;
                                        
        
    }

    public static Document lineItemToDocument(LineItem lineItem) {
        Document document = new Document();
        document.put("item", lineItem.getItem());
        document.put("quantity", lineItem.getQuantity());
        return document;
    }
    
}
