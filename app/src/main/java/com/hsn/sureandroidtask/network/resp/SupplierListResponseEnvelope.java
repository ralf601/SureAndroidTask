package com.hsn.sureandroidtask.network.resp;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Namespace;
import org.simpleframework.xml.NamespaceList;
import org.simpleframework.xml.Root;

/**
 * Created by hassanshakeel on 3/24/18.
 */
@Root(name = "soap12:Envelope")
@NamespaceList({
        @Namespace( prefix = "xsi", reference = "http://www.w3.org/2001/XMLSchema-instance"),
        @Namespace( prefix = "xsd", reference = "http://www.w3.org/2001/XMLSchema"),
        @Namespace( prefix = "soap12", reference = "http://www.w3.org/2003/05/soap-envelope")
})
public class SupplierListResponseEnvelope {
    @Element(required = false, name = "Body")
    private SupplierListResponseBody body;

    public SupplierListResponseBody getBody() {
        return body;
    }

    public void setBody(SupplierListResponseBody body) {
        this.body = body;
    }
}
