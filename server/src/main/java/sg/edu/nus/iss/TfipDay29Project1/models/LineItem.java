package sg.edu.nus.iss.TfipDay29Project1.models;

import org.bson.Document;

public class LineItem {

    // Id not required because it is embedded
    // private String id;

    private String item;

    private Integer quantity;

    // public String getId() {
    //     return id;
    // }

    // public void setId(String id) {
    //     this.id = id;
    // }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static Document fromObjectToBson(LineItem lineItem) {
        Document documentLineItem = new Document();
        // Id not required because it is embedded
        // documentLineItem.put("id", lineItem.getId());
        documentLineItem.put("item", lineItem.getItem());
        documentLineItem.put("quantity", lineItem.getQuantity());
        return documentLineItem;
    }
    
}
