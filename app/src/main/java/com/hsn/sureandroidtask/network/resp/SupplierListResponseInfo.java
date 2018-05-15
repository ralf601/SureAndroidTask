package com.hsn.sureandroidtask.network.resp;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */

@Root(name = "GetSupplierByCityResult", strict = false)
public class SupplierListResponseInfo {
    @ElementList(name = "SupplierDataLists", required = false)
    SupplierDataLists supplierDataLists;

    public SupplierDataLists getSupplierDataLists() {
        return supplierDataLists;
    }

    public void setSupplierDataLists(SupplierDataLists supplierDataLists) {
        this.supplierDataLists = supplierDataLists;
    }
}
