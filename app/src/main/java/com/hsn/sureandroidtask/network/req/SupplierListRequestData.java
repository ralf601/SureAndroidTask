package com.hsn.sureandroidtask.network.req;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */

@Root(name = "GetSupplierByCity", strict = false)
@Namespace(reference = "http://www.webservicex.net/")
public class SupplierListRequestData {
    @Element(name = "City", required = false)
    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
