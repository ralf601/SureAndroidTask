package com.hsn.sureandroidtask.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */

@Root(name = "SupplierData",strict = false)
public class SupplierData {

    @Element(name = "SupplierNumber", required = false)
    private String SupplierNumber;

    @Element(name = "CompanyName", required = false)
    private String CompanyName;

    @Element(name = "Address1", required = false)
    private String Address1;

    @Element(name = "Address2", required = false)
    private String Address2;

    @Element(name = "City", required = false)
    private String City;

    @Element(name = "State", required = false)
    private String State;

    @Element(name = "Zip", required = false)
    private String Zip;

    @Element(name = "ZipPlus4", required = false)
    private String ZipPlus4;

    @Element(name = "Telephone", required = false)
    private String Telephone;

    @Element(name = "Description", required = false)
    private String Description;

    @Element(name = "IsSupplierParticipating", required = false)
    private String IsSupplierParticipating;


    public String getSupplierNumber() {
        return SupplierNumber;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public String getAddress1() {
        return Address1;
    }

    public String getAddress2() {
        return Address2;
    }

    public String getCity() {
        return City;
    }

    public String getState() {
        return State;
    }

    public String getZip() {
        return Zip;
    }

    public String getZipPlus4() {
        return ZipPlus4;
    }

    public String getTelephone() {
        return Telephone;
    }

    public String getDescription() {
        return Description;
    }

    public String getIsSupplierParticipating() {
        return IsSupplierParticipating;
    }
}
