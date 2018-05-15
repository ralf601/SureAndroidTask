package com.hsn.sureandroidtask.network;


import com.hsn.sureandroidtask.network.req.SearchEventRequest;
import com.hsn.sureandroidtask.network.resp.SearchEventResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by hassanshakeel on 2/15/18.
 */

public interface WebApi {

    String BaseUrl = "http://www.saudievents.sa";

    @POST("api/EventCalendar/SearchEvents")
    Call<SearchEventResponse> fetchEvents(@Body SearchEventRequest searchEventRequest);
}
