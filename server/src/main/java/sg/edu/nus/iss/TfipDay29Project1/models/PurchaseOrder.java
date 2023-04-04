package sg.edu.nus.iss.TfipDay29Project1.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.bson.Document;

import jakarta.validation.constraints.NotBlank;

public class PurchaseOrder {

    private String id;

    @NotBlank
    private String name;

    private String email;

    private String deliveryDate;

    private List<LineItem> lineItems = new ArrayList<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public void addLineItem(LineItem lineItem) {
        this.lineItems.add(lineItem);
    }

    public static Document fromObjectToBson(PurchaseOrder purchaseOrder) {
        Document documentPurchaseOrder = new Document();
        documentPurchaseOrder.put("id", purchaseOrder.getId());
        documentPurchaseOrder.put("name", purchaseOrder.getName());
        documentPurchaseOrder.put("email", purchaseOrder.getEmail());
        documentPurchaseOrder.put("deliveryDate", purchaseOrder.getDeliveryDate());
        List<Document> lineItems = purchaseOrder.getLineItems().stream().map(LineItem::fromObjectToBson).collect(Collectors.toList());
        documentPurchaseOrder.put("lineItems", lineItems);
        return documentPurchaseOrder;
    }
    
}
