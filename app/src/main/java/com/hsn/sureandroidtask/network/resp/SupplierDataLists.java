package com.hsn.sureandroidtask.network.resp;

import com.hsn.sureandroidtask.model.SupplierData;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

/**
 * Created by hassanshakeel on 3/24/18.
 */


@Root(name = "SupplierDataLists",strict = false)
public class SupplierDataLists {
    @ElementList(name = "SupplierDatas",required = false)
    List<SupplierData> elements;

    public List<SupplierData> getElements() {
        return elements;
    }

    public void setElements(List<SupplierData> elements) {
        this.elements = elements;
    }
}