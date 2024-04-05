package com.youable.bestudy.model;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Address address = (Address) obj;
        return city.equals(address.city)
                && street.equals(address.street)
                && zipcode.equals(address.zipcode);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
