
package com.hsn.sureandroidtask.network.req;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class SearchEventRequest {

    @SerializedName("categoryID")
    private String mCategoryID;
    @SerializedName("cityID")
    private Long mCityID;
    @SerializedName("eventId")
    private String mEventId;
    @SerializedName("eventTitle")
    private String mEventTitle;
    @SerializedName("fromDate")
    private String mFromDate;
    @SerializedName("lang")
    private String mLang;
    @SerializedName("pageSize")
    private Long mPageSize;
    @SerializedName("regionID")
    private Long mRegionID;
    @SerializedName("startIndex")
    private Long mStartIndex;
    @SerializedName("toDate")
    private String mToDate;

    public String getCategoryID() {
        return mCategoryID;
    }

    public void setCategoryID(String categoryID) {
        mCategoryID = categoryID;
    }

    public Long getCityID() {
        return mCityID;
    }

    public void setCityID(Long cityID) {
        mCityID = cityID;
    }

    public String getEventId() {
        return mEventId;
    }

    public void setEventId(String eventId) {
        mEventId = eventId;
    }

    public Object getEventTitle() {
        return mEventTitle;
    }

    public void setEventTitle(String eventTitle) {
        mEventTitle = eventTitle;
    }

    public String getFromDate() {
        return mFromDate;
    }

    public void setFromDate(String fromDate) {
        mFromDate = fromDate;
    }

    public String getLang() {
        return mLang;
    }

    public void setLang(String lang) {
        mLang = lang;
    }

    public Long getPageSize() {
        return mPageSize;
    }

    public void setPageSize(Long pageSize) {
        mPageSize = pageSize;
    }

    public Long getRegionID() {
        return mRegionID;
    }

    public void setRegionID(Long regionID) {
        mRegionID = regionID;
    }

    public Long getStartIndex() {
        return mStartIndex;
    }

    public void setStartIndex(Long startIndex) {
        mStartIndex = startIndex;
    }

    public String getToDate() {
        return mToDate;
    }

    public void setToDate(String toDate) {
        mToDate = toDate;
    }

}
