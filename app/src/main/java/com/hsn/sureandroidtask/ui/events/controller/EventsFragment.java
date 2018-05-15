package com.hsn.sureandroidtask.ui.events.controller;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.common.Utils;
import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.network.WebApiError;
import com.hsn.sureandroidtask.network.WebApiManager;
import com.hsn.sureandroidtask.network.WebApiResponseListener;
import com.hsn.sureandroidtask.ui.common.controller.BaseFragment;
import com.hsn.sureandroidtask.ui.common.controller.MainActivity;
import com.hsn.sureandroidtask.ui.events.view.EventsView;
import com.hsn.sureandroidtask.ui.events.view.EventsViewImpl;
import com.hsn.sureandroidtask.ui.filter.controller.Filter;
import com.hsn.sureandroidtask.ui.filter.controller.FilterFragment;
import com.hsn.sureandroidtask.ui.map.MapsActivity;
import com.hsn.sureandroidtask.ui.welcome.view.WelcomeView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.hsn.sureandroidtask.common.Utils.ServerDateTimeFormat;


/**
 * Created by hassanshakeel on 2/27/18.
 */

public class EventsFragment extends BaseFragment implements
        EventsView.EventsInteractor, WebApiResponseListener<List<EventDetails>>, FilterFragment.Callback {


    private EventsView eventsView;
    private List<EventDetails> eventDetailsList = new ArrayList<>();
    private List<EventDetails> filteredList = new ArrayList<>();
    private FilterFragment.SortType sortBy = FilterFragment.SortType.None;
    private Filter dataFilter;
    private Map<String, String> citySuggestions = new HashMap<>();


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        eventsView = new EventsViewImpl(inflater, container);
        eventsView.setInteractor(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(eventsView.getToolbar());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.saudi_events));

        return eventsView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (eventDetailsList.size() == 0)
            fetchEvents();
        else
            eventsView.bindEvents(sortAndFilter());

    }


    @Override
    public void showEventLocation(EventDetails eventDetails) {
        MapsActivity.open(getActivity(), eventDetails);
    }

    @Override
    public void searchByEventName(String name) {
        if (name.length() == 0) {
            eventsView.bindEvents(filteredList);
            return;
        }

        List<EventDetails> filteredList = new ArrayList<>();
        for (EventDetails details : this.filteredList) {
            if (details.getEventTitle().toLowerCase().startsWith(name.toLowerCase()))
                filteredList.add(details);
        }
        eventsView.bindEvents(filteredList);
    }

    @Override
    public void onDataFetched(List<EventDetails> eventDetailsList) {
        eventsView.hideLoader();
        this.eventDetailsList = eventDetailsList;
        //apply filters
        eventsView.bindEvents(sortAndFilter());
        for (EventDetails eventDetails : eventDetailsList) {
            citySuggestions.put(eventDetails.getCityArName(), eventDetails.getCityArName());
            citySuggestions.put(eventDetails.getCityEnName(), eventDetails.getCityEnName());
        }

    }

    @Override
    public void onError(WebApiError error, String message) {
        eventsView.hideLoader();
        showError(getString(R.string.error_fetch_events));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.event_toolbar_menu, menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        if (searchItem != null) {
            SearchView searchView = (SearchView) searchItem.getActionView();
            eventsView.setSearchView(searchView);
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                FilterFragment filter = new FilterFragment();
                filter.setSuggestions(new ArrayList<>(citySuggestions.values()));
                filter.setCallback(this);
                filter.setFilterAndSort(dataFilter,sortBy);
                ((MainActivity) getActivity()).replaceFragment(filter, true, null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void fetchEvents() {
        eventsView.showLoader();
        WebApiManager.getInstance(getContext()).getAllEvents(this);
    }

    @Override
    public void onFilterUpdated(Filter filter, FilterFragment.SortType sortType) {
        this.dataFilter = filter;
        this.sortBy = sortType;

        //eventsView.bindEvents(sortAndFilter());
    }

    private List<EventDetails> sortAndFilter() {
        List<EventDetails> filteredAndSortedList = getFilteredList();

        if (sortBy == FilterFragment.SortType.Nearest) {
            Collections.sort(filteredAndSortedList, new Comparator<EventDetails>() {
                @Override
                public int compare(EventDetails eventDetails, EventDetails t1) {
                    try {
                        return ServerDateTimeFormat.parse(t1.getEventStartDate())
                                .compareTo(ServerDateTimeFormat.parse(eventDetails.getEventStartDate()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    return 0;
                }
            });
        }
        filteredList = filteredAndSortedList;
        return filteredAndSortedList;
    }

    private List<EventDetails> getFilteredList() {
        if (dataFilter == null)
            return eventDetailsList;


        List<EventDetails> filteredList = new ArrayList<>();
        for (EventDetails eventDetails : eventDetailsList) {
            //name filter
            if (dataFilter.name != null
                    && !dataFilter.name.equals("")) {
                if (dataFilter.name.toLowerCase().startsWith(eventDetails.getEventTitle().toLowerCase())) {
                } else {
                    continue;
                }
            }

            //start date filter
            if (dataFilter.eventStartDate != null
                    && !dataFilter.eventStartDate.equals("")) {
                Date filterStartDate = Utils.parseFilterDate(dataFilter.eventStartDate);
                Date itemDate = Utils.parseServerDateForFilter(eventDetails.getEventStartDate());
                if (itemDate.equals(filterStartDate)) {
                } else {
                    continue;
                }
            }
            //end date filter
            if (dataFilter.eventEndDate != null
                    && !dataFilter.eventEndDate.equals("")) {
                Date filterStartDate = Utils.parseFilterDate(dataFilter.eventEndDate);
                Date itemDate = Utils.parseServerDateForFilter(eventDetails.getEventEndDate());
                if (itemDate.equals(filterStartDate)) {
                } else {
                    continue;
                }
            }

            //city filter
            if (dataFilter.city != null
                    && !dataFilter.city.equals("")) {
                if (dataFilter.city.toLowerCase().equals(eventDetails.getCityEnName().toLowerCase())
                        || dataFilter.city.equals(eventDetails.getCityArName())) {
                } else {
                    continue;
                }
            }

            filteredList.add(eventDetails);
        }


        return filteredList;
    }
}
