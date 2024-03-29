package com.aurigarefactored.interview.controller;

import com.aurigarefactored.interview.get_resource.DeviceData;
import com.aurigarefactored.interview.get_resource.GetResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
class Controller {

//    // Get request from user for device details, call external url, send data back to user.
//    @GetMapping(value="/client_response", produces = MediaType.TEXT_PLAIN_VALUE)
//    public ResponseEntity<String> clientResponse(){
//
//        // Get resource
//        GetResource resource = new GetResource();
//        String urlResponse = resource.getResource();
//
//        // Check to see if resource returned is not null
//        if(urlResponse != null){
//            ArrayList<String> clientResponse = resource.responseToJSON(urlResponse);
//
//            // Return device data to client (UI)
//            return ResponseEntity.ok().body(clientResponse.toString());
//        }
//        else{
//            return ResponseEntity.notFound().build();
//        }
//    }

    // Get request from user for device details, call external url, send data back to user.
    @GetMapping(value="/client_response", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DeviceData> clientResponse(){

        // Get resource
        GetResource resource = new GetResource();
        String urlResponse = resource.getResource();

        // Check to see if resource returned is not null
        if(urlResponse != null){
            //DeviceData clientResponse = resource.responseToJSON(urlResponse);

            // Return device data to client (UI)
            //return ResponseEntity.ok().body(clientResponse.toString());
            return new ResponseEntity<>(resource.responseToJSON(urlResponse), HttpStatus.OK);
        }
        else{
            return null;
        }
    }

}
