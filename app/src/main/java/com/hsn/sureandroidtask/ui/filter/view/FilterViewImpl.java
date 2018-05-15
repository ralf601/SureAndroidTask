package com.hsn.sureandroidtask.ui.filter.view;

import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.common.Utils;
import com.hsn.sureandroidtask.ui.filter.controller.Filter;

import java.util.List;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public class FilterViewImpl implements FilterView, View.OnClickListener {
    private View mRootView;
    private Interactor interactor;
    private Toolbar toolbar;
    private RelativeLayout start_date;
    private RelativeLayout end_date;
    private AppCompatAutoCompleteTextView search_text_view;
    private CardView apply,clear;
    private TextView startDateText;
    private TextView endDateText;
    private CheckBox sortByNearest;


    public FilterViewImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_filter, container, false);
        initView(mRootView);
        initListeners();
    }


    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar);
        start_date = view.findViewById(R.id.start_date);
        end_date = view.findViewById(R.id.end_date);
        search_text_view = view.findViewById(R.id.search_text_view);
        apply = view.findViewById(R.id.apply);
        startDateText = view.findViewById(R.id.startDateText);
        endDateText = view.findViewById(R.id.endDateText);
        clear = view.findViewById(R.id.clear);
        sortByNearest = view.findViewById(R.id.nearest);
    }

    @Override
    public void initListeners() {
        apply.setOnClickListener(this);
        start_date.setOnClickListener(this);
        end_date.setOnClickListener(this);
        clear.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.apply:
                if (interactor != null)
                    interactor.onApply();
                break;
            case R.id.start_date:
                if (interactor != null)
                    interactor.showStartDatePicker();
                break;
            case R.id.end_date:
                if (interactor != null)
                    interactor.showEndDatePicker();
                break;
            case R.id.clear:
                if (interactor != null)
                    interactor.onClear();
                break;
            default:
                break;
        }
    }


    @Override
    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void bindSuggestions(List<String> suggestions) {
        if (suggestions.size() == 0)
            return;
        String[] arr = suggestions.toArray(new String[0]);
        ArrayAdapter<String> searchAdapter =
                new ArrayAdapter<>(search_text_view.getContext(), android.R.layout.simple_list_item_1, arr);
        search_text_view.setAdapter(searchAdapter);

    }

    @Override
    public String getCity() {
        return search_text_view.getText().toString();
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void setTitle(String title) {
        toolbar.setTitle(title);
    }

    @Override
    public void setStartDate(String date) {
        startDateText.setText(date);
    }

    @Override
    public void setEndDate(String date) {
        endDateText.setText(date);
    }

    @Override
    public void setCity(String city) {
        search_text_view.setText(city);
    }

    @Override
    public void bindFilterValues(Filter filter) {
        if (filter == null)
            return;
        if (filter.eventStartDate != null)
            startDateText.setText(Utils.UiDateFormat.format(Utils.parseFilterDate(filter.eventStartDate)));
        if (filter.eventEndDate != null)
            endDateText.setText(Utils.UiDateFormat.format(Utils.parseFilterDate(filter.eventEndDate)));
        if (filter.city != null)
            search_text_view.setText(filter.city);

    }

    @Override
    public boolean isNearestEventsFirst() {
        return sortByNearest.isChecked();
    }

    @Override
    public void setNearestSortChecked(boolean enable) {
        sortByNearest.setChecked(enable);
    }
}
