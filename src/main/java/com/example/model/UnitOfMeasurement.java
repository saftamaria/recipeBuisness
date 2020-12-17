package com.example.model;

import javax.persistence.*;

@Entity(name = "unitOfMeasurement")
public class UnitOfMeasurement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "uom_id")
    private Long uomId;

    private String description;

    public UnitOfMeasurement() {
    }

    public Long getUomId() {
        return uomId;
    }

    public void setUomId(Long uomId) {
        this.uomId = uomId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
