package com.hsn.sureandroidtask.network.resp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */
@Root(name = "GetSupplierByCityResponse", strict = false)
@Namespace(reference = "http://www.webservicex.net/")
public class SupplierListResponseData {
    @Element(name = "GetSupplierByCityResult", required = false)
    private boolean SupplierByCityResult;

    @Element(name = "SupplierDataLists", required = false)
    private SupplierDataLists SupplierDataLists;

    public boolean isSupplierByCityResult() {
        return SupplierByCityResult;
    }

    public void setSupplierByCityResult(boolean supplierByCityResult) {
        SupplierByCityResult = supplierByCityResult;
    }

    public com.hsn.sureandroidtask.network.resp.SupplierDataLists getSupplierDataLists() {
        return SupplierDataLists;
    }

    public void setSupplierDataLists(com.hsn.sureandroidtask.network.resp.SupplierDataLists supplierDataLists) {
        SupplierDataLists = supplierDataLists;
    }
}
