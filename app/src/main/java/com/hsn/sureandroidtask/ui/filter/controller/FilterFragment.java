package com.hsn.sureandroidtask.ui.filter.controller;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.common.Utils;
import com.hsn.sureandroidtask.ui.common.controller.BaseFragment;
import com.hsn.sureandroidtask.ui.common.controller.MainActivity;
import com.hsn.sureandroidtask.ui.events.controller.EventsFragment;
import com.hsn.sureandroidtask.ui.filter.view.FilterView;
import com.hsn.sureandroidtask.ui.filter.view.FilterViewImpl;
import com.hsn.sureandroidtask.ui.welcome.view.WelcomeView;
import com.hsn.sureandroidtask.ui.welcome.view.WelcomeViewImpl;

import java.util.Calendar;
import java.util.List;


/**
 * Created by hassanshakeel on 2/27/18.
 */

public class FilterFragment extends BaseFragment implements FilterView.Interactor, DatePickerDialog.OnDateSetListener {

    public interface Callback {
        void onFilterUpdated(Filter filter,SortType sortType);
    }

    public enum SortType {
        Nearest,
        None
    }

    private FilterView filterView;
    private Callback callback;
    private Filter appliedFilter = new Filter();
    private boolean isStartDate = false;
    private List<String> suggestions;
    private SortType sortType;

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    public void setFilterAndSort(Filter appliedFilter,SortType sortType) {
        if (appliedFilter != null)
            this.appliedFilter = appliedFilter;
        this.sortType = sortType;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        filterView = new FilterViewImpl(inflater, container);
        filterView.setInteractor(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(filterView.getToolbar());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.filter));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        filterView.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        filterView.bindSuggestions(suggestions);
        return filterView.getRootView();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getActivity().onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        filterView.bindFilterValues(appliedFilter);
        filterView.setNearestSortChecked(SortType.Nearest == sortType ? true : false);
    }


    @Override
    public void showEndDatePicker() {
        isStartDate = false;
        showDatePicker();

    }

    @Override
    public void showStartDatePicker() {
        isStartDate = true;
        showDatePicker();
    }

    @Override
    public void onApply() {
        appliedFilter.city = filterView.getCity();

        if (callback != null)
            callback.onFilterUpdated(appliedFilter,filterView.isNearestEventsFirst()?SortType.Nearest:SortType.None);
        getActivity().onBackPressed();
    }

    @Override
    public void onClear() {
        if (callback != null)
            callback.onFilterUpdated(new Filter(),SortType.None);
        getActivity().onBackPressed();
    }


    @Override
    public void onDateSet(DatePicker datePicker, int year, int monthOfYear,
                          int dayOfMonth) {
        //yyyy-MM-dd
        if (!isStartDate) {
            appliedFilter.eventEndDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            filterView.setEndDate(Utils.UiDateFormat.format(Utils.parseFilterDate(appliedFilter.eventEndDate)));
        } else {
            appliedFilter.eventStartDate = year + "-" + (monthOfYear + 1) + "-" + dayOfMonth;
            filterView.setStartDate(Utils.UiDateFormat.format(Utils.parseFilterDate(appliedFilter.eventStartDate)));
        }

    }

    private void showDatePicker() {
        Calendar c = Calendar.getInstance();
        new DatePickerDialog(getActivity(), this, c
                .get(Calendar.YEAR), c.get(Calendar.MONTH),
                c.get(Calendar.DAY_OF_MONTH)).show();
    }


}
