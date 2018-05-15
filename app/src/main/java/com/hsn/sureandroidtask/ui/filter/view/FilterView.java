package com.hsn.sureandroidtask.ui.filter.view;



import android.support.v7.widget.Toolbar;

import com.hsn.sureandroidtask.ui.common.view.ViewMvc;
import com.hsn.sureandroidtask.ui.filter.controller.Filter;

import java.util.List;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public interface FilterView extends ViewMvc {

    interface Interactor {

        void showStartDatePicker();

        void showEndDatePicker();

        void onApply();

        void onClear();


    }

    void setInteractor(Interactor interactor);

    void bindSuggestions(List<String> suggestions);

    String getCity();

    Toolbar getToolbar();

    void setTitle(String title);


    void setEndDate(String date);

    void setStartDate(String date);

    void setCity(String city);

    void bindFilterValues(Filter filter);

    boolean isNearestEventsFirst();

    void setNearestSortChecked(boolean enable);


}
