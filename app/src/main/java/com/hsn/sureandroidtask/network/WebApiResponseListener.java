package com.hsn.sureandroidtask.network;

/**
 * Created by hassanshakeel on 2/15/18.
 */

public interface WebApiResponseListener<T> {

    void onDataFetched(T callback);
    void onError(WebApiError error, String message);

}
