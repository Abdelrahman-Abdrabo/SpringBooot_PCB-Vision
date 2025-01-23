package com.PCB.PCB_Vision.model;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Component implements Serializable {
    private String type;
    private List<String> details;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getDetails() {
        return details;
    }

    public void setDetails(List<String> details) {
        this.details = details;
    }

    @Override
    public String toString() {
        return "Component{" +
                "type='" + type + '\'' +
                ", details=" + details +
                '}';
    }
}