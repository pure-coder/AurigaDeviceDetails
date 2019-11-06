package com.aurigarefactored.interview.get_resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class GetResource {

    private static final Logger log = LoggerFactory.getLogger(GetResource.class);

    // Only one instance of mapper needed so should have created this as local to whole object (Global)
    ObjectMapper mapper = new ObjectMapper();

    // Get json data from external URL
    public String getResource() {
        RestTemplate restTemplate = new RestTemplate();

//		This is the external url being called
//		String externalUrl = "https://api.cybergator.co.uk/testing/devices";

//        This is a call to an url to get the assumed response from the call to external url above.
        String url = "http://localhost:8080/externalUrl1.json";

////        // My thinking about if there were more than one device in json.
//        String url = "http://localhost:8080/externalUrl2.json";

        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();

        if(statusCode == HttpStatus.OK){
            return body;
        }
        else{
            log.info("err: Could not load resource");
            return null;
        }
    }

    // Used methods to break up functionality for readability and testing.

    // Get the object from json array.
    public ArrayList<String> responseToJSON(String response) {
        // Create Array list to add device data too.
        ArrayList<String> deviceDataArray = new ArrayList<>();

        // Get first object out of array, in test used for loop to get objects out of array,
        // thinking was for future if more than one device was listed in json response.
        // Convert String response into JsonArray
        JSONObject jsonObj;
        try {
            JSONArray jsonArr = new JSONArray(response);
            // Future if more than 1 device
            for (int i = 0; i < jsonArr.length(); i++)
            {
                jsonObj = jsonArr.getJSONObject(i);

                // Used an iterator to get the actual data out of the nested object that was retrieved from array,
                // iterator was best way to get data needed, better than hardcoding getting first object, as if more devices were
                // returned in external url (future?).
                Iterator keys = jsonObj.keys();

                while(keys.hasNext()){
                    String currentDeviceKey = (String) keys.next();
                    try {
                        String currentDeviceValue = jsonObj.getString(currentDeviceKey);

                        // Deserialization of json into object so that it can be manipulated/modified
                        // into useful data that client needs
                        DeviceData deviceData = mapper.readValue(currentDeviceValue, DeviceData.class);

                        // Add specified serialized device details to array list
                        deviceDataArray.add(mapper.writeValueAsString(deviceData));
                    }
                    catch (JSONException | IOException err) {
                        log.info(err.toString());
                    }
                }
            }
        }
        catch (JSONException err){
            System.out.println("err: " + err.toString());
            return (ArrayList) Collections.emptyList();
        }
        return deviceDataArray;
    }


}
