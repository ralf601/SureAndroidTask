package com.hsn.sureandroidtask.network;

import com.hsn.sureandroidtask.network.req.SupplierListRequestEnvelope;
import com.hsn.sureandroidtask.network.resp.SupplierListResponseEnvelope;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by hassanshakeel on 3/24/18.
 */
public interface MedicareApi {
    String BASE_URL = "http://webservicex.net";

    @Headers({
            "Content-Type: application/soap+xml; charset=utf-8"
    })
    @POST("/medicareSupplier.asmx")
    Call<SupplierListResponseEnvelope> requestInfoByCity(@Body SupplierListRequestEnvelope envelope);

}
