package sg.edu.nus.iss.TfipDay29Project1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.Valid;
import sg.edu.nus.iss.TfipDay29Project1.models.PurchaseOrder;
import sg.edu.nus.iss.TfipDay29Project1.services.PurchaseOrderService;
import sg.edu.nus.iss.TfipDay29Project1.utils.Utils;

@RestController
@RequestMapping(path="/api")
public class PurchaseOrderController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @PostMapping(path="/order")
    public ResponseEntity<String> savePurchaseOrder(@RequestBody String payload) {
        
        JsonObject jsonObject = Utils.jsonStringToJsonObject(payload);

        PurchaseOrder purchaseOrder = Utils.jsonObjectToPurchaseOrder(jsonObject);

        String orderId = purchaseOrderService.savePurchaseOrder(purchaseOrder);

        // Frontend might only accept OrderResponse Object
        JsonObject response = Json.createObjectBuilder()
                                .add("orderId", orderId)
                                .add("message", "Order Placed!")
                                .build();
                
        return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response.toString());
    } 

    // This was my previous method where I used jackson to convert the payload
    // @PostMapping(path="/order")
    // public ResponseEntity<String> savePurchaseOrder(@RequestBody @Valid PurchaseOrder purchaseOrder) {
        
    //     String orderId = purchaseOrderService.savePurchaseOrder(purchaseOrder);

    //     // Frontend might only accept OrderResponse Object
    //     JsonObject response = Json.createObjectBuilder()
    //                             .add("orderId", orderId)
    //                             .add("message", "Order Placed!")
    //                             .build();
                
    //     return ResponseEntity.status(HttpStatus.CREATED).contentType(MediaType.APPLICATION_JSON).body(response.toString());
    // }    

    
}
