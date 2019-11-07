package com.aurigarefactored.interview;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DeviceData {

    @JsonProperty("name")
    public String name;

    @JsonProperty("agentVersion")
    public String agentVersion;

    @JsonProperty("howManyAlerts")
    public int howManyAlerts;

    @JsonProperty("alertIds")
    public void setHowManyAlerts(List<String> alerts){
        this.howManyAlerts = alerts.size();
    }

    @JsonProperty("architecture")
    public String architecture;

    @JsonProperty("collector")
    public String collector;

    @JsonProperty("collector")
    public void setCollector(Map<String, String> collectorData){
        this.collector = collectorData.get("collectorName");
    }

    @JsonProperty("cpuModel")
    public String cpuModel;

    @JsonProperty("description")
    public String description;

    @JsonProperty("discoveryDate")
    public String discoveryDate;

    @JsonProperty("discoveryDate")
    public void setDiscoveryDate(String date){
        this.discoveryDate = date.substring(0, 10);
    }

    @JsonProperty("ipAddresses")
    ArrayList<String> ipAddresses;

    @JsonProperty("ipAddresses")
    public void setIpAddresses(List<HashMap<String, String>> ipAddresses) {
        ArrayList<String> arrayList = new ArrayList<>();
        // Get IP address out of each of the objects and add them to the array list.
        for(int i = 0; i < ipAddresses.size(); i++){
            arrayList.add((ipAddresses.get(i).get("ipAddress")));
        }
        this.ipAddresses = arrayList;
    }
}

