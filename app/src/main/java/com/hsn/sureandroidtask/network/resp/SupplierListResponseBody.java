package com.hsn.sureandroidtask.network.resp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */
@Root(name = "Body", strict = false)
public class SupplierListResponseBody {
    @Element(name = "GetSupplierByCityResponse", required = false)
    @Namespace(reference = "http://www.webservicex.net/")
    private SupplierListResponseData data;

    public SupplierListResponseData getData() {
        return data;
    }

    public void setData(SupplierListResponseData data) {
        this.data = data;

    }
}
