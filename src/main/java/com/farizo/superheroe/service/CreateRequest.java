package com.farizo.superheroe.service;

import java.io.Serial;
import java.io.Serializable;

public class CreateRequest implements Serializable {
    @Serial
    private static final long serialVersionUID = 2433784056652780975L;

    private String name;

    public CreateRequest() {
    }

    public CreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
