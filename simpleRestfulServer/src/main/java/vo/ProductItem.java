package com.antra.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ProductItem {
    private Long id;

    @NotNull
    private String name;

    @Min(0)
    private Double price;

    public ProductItem(){

    }
    public ProductItem(Long id, String name, Double price){
        super();
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
