package com.hsn.sureandroidtask.network.req;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */

@Root(name = "soap12:Body", strict = false)
public class SupplierListRequestBody {

    @Element(name = "GetSupplierByCity",required = false)
    private SupplierListRequestData supplierListRequestData;

    public SupplierListRequestData getSupplierListRequestData() {
        return supplierListRequestData;
    }

    public void setSupplierListRequestData(SupplierListRequestData supplierListRequestData) {
        this.supplierListRequestData = supplierListRequestData;
    }
}
