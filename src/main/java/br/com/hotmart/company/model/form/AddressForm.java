package br.com.hotmart.company.model.form;

import br.com.hotmart.company.model.entity.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AddressForm {

    private Long id;
    private String country;
    private String uf;
    private String city;
    private String street;
    private String zipCode;

    public Address toEntity(){
        Address address = new Address();
        address.setCountry(this.country);
        address.setUf(this.uf);
        address.setCity(this.city);
        address.setStreet(this.street);
        address.setZipCode(this.zipCode);
        return address;
    }

}
