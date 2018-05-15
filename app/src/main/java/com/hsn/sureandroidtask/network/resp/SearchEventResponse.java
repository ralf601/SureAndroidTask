
package com.hsn.sureandroidtask.network.resp;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.hsn.sureandroidtask.model.EventDetails;

@SuppressWarnings("unused")
public class SearchEventResponse {

    @SerializedName("Records")
    private List<EventDetails> mEventDetails;
    @SerializedName("Result")
    private String mResult;

    public List<EventDetails> getRecords() {
        return mEventDetails;
    }

    public void setRecords(List<EventDetails> eventDetails) {
        mEventDetails = eventDetails;
    }

    public String getResult() {
        return mResult;
    }

    public void setResult(String Result) {
        mResult = Result;
    }

}
