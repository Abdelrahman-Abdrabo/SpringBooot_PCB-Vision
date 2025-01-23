package com.PCB.PCB_Vision.model;

import lombok.*;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Defect implements Serializable {
    private String defectName;
    private String description;

    public String getDefectName() {
        return defectName;
    }

    public void setDefectName(String defectName) {
        this.defectName = defectName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Defect{" +
                "defectName='" + defectName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}