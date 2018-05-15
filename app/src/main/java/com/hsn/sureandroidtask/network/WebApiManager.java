package com.hsn.sureandroidtask.network;

import android.content.Context;


import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.network.req.SearchEventRequest;
import com.hsn.sureandroidtask.network.req.SupplierListRequestBody;
import com.hsn.sureandroidtask.network.req.SupplierListRequestData;
import com.hsn.sureandroidtask.network.req.SupplierListRequestEnvelope;
import com.hsn.sureandroidtask.model.SupplierData;
import com.hsn.sureandroidtask.network.resp.SearchEventResponse;
import com.hsn.sureandroidtask.network.resp.SupplierListResponseEnvelope;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hassanshakeel on 2/15/18.
 */

public class WebApiManager {

    private Context context;

    private static WebApiManager webApiManager;

    public static WebApiManager getInstance(Context context) {
        if (webApiManager == null) {
            synchronized (WebApiManager.class) {
                webApiManager = new WebApiManager(context);
            }
        }
        return webApiManager;
    }

    private WebApiManager(Context context) {
        this.context = context;
    }


    public void getAllEvents(final WebApiResponseListener<List<EventDetails>> listener) {
        SearchEventRequest searchEventRequest = new SearchEventRequest();
        searchEventRequest.setCategoryID("0");
        searchEventRequest.setCityID(0l);
        searchEventRequest.setEventId("");
        searchEventRequest.setFromDate("Thu Jul 27 2017");
        searchEventRequest.setToDate("Sun Dec 31 2017");
        searchEventRequest.setRegionID(0l);
        searchEventRequest.setStartIndex(0l);
        searchEventRequest.setLang(Locale.getDefault().getLanguage());
        searchEventRequest.setPageSize(6000l);
        try {
            RetrofitHelper.getWebApi(context)
                    .fetchEvents(searchEventRequest)
                    .enqueue(new Callback<SearchEventResponse>() {
                        @Override
                        public void onResponse(Call<SearchEventResponse> call, Response<SearchEventResponse> response) {
                            if (response.code() != 200)
                                listener.onError(WebApiError.HttpRequestFailed, "response code=" + response.code());
                            else if (response.body().getResult().equals("OK"))
                                listener.onDataFetched(response.body().getRecords());
                            else
                                listener.onError(WebApiError.HttpRequestFailed, "Result=" + response.body().getResult());

                        }

                        @Override
                        public void onFailure(Call<SearchEventResponse> call, Throwable t) {
                            t.printStackTrace();
                            listener.onError(WebApiError.Exception, t.getMessage());
                        }
                    });
        } catch (NoNetworkException nne) {
            nne.printStackTrace();
            listener.onError(WebApiError.NoNetworkAvailable, nne.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError(WebApiError.Exception, e.getMessage());
        }
    }


    public void requestInfoByCity(final String cityName, final WebApiResponseListener<List<SupplierData>> listener) {
        SupplierListRequestData requestData = new SupplierListRequestData();
        requestData.setCity(cityName);

        final SupplierListRequestBody requestBody = new SupplierListRequestBody();
        requestBody.setSupplierListRequestData(requestData);

        SupplierListRequestEnvelope envelope = new SupplierListRequestEnvelope();
        envelope.setBody(requestBody);

        try {
            RetrofitHelper.getMedicareApi(context)
                    .requestInfoByCity(envelope)
                    .enqueue(new Callback<SupplierListResponseEnvelope>() {
                        @Override
                        public void onResponse(Call<SupplierListResponseEnvelope> call, Response<SupplierListResponseEnvelope> response) {
                            if (response.code() == 200)
                                listener.onDataFetched(response.body().getBody().getData().getSupplierDataLists().getElements());
                            else
                                listener.onError(WebApiError.HttpRequestFailed, "response code=" + response.code());

                        }

                        @Override
                        public void onFailure(Call<SupplierListResponseEnvelope> call, Throwable t) {
                            t.printStackTrace();
                            listener.onError(WebApiError.Exception, t.getMessage());
                        }
                    });
        } catch (NoNetworkException nne) {
            nne.printStackTrace();
            listener.onError(WebApiError.NoNetworkAvailable, nne.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            listener.onError(WebApiError.Exception, e.getMessage());
        }
    }
}
