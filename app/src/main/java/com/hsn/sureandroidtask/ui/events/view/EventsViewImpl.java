package com.hsn.sureandroidtask.ui.events.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import com.google.android.gms.maps.GoogleMap;
import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.ui.events.adapter.EventAdapter;
import com.hsn.sureandroidtask.ui.welcome.view.*;

import java.util.List;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public class EventsViewImpl implements EventsView, EventAdapter.OnItemClickListener {

    private View mRootView;
    private EventsInteractor interactor;
    private EventAdapter eventAdapter;
    private RecyclerView eventListView;
    private SearchView searchView;
    private Toolbar toolbar;
    private View loader;

    public EventsViewImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_events, container, false);
        initView(mRootView);
        initListeners();

    }


    private void setupRecyclerView() {
        eventAdapter = new EventAdapter();
        eventListView.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        eventListView.setAdapter(eventAdapter);


    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void initView(View view) {
        eventListView = view.findViewById(R.id.eventListView);
        toolbar = view.findViewById(R.id.toolbar);
        loader = view.findViewById(R.id.loader);
        setupRecyclerView();
    }

    @Override
    public void initListeners() {
        eventAdapter.setOnItemClickListener(this);
    }

    @Override
    public void setInteractor(EventsInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void bindEvents(List<EventDetails> eventDetails) {
        eventAdapter.update(eventDetails);
    }

    @Override
    public void setSearchView(SearchView searchView) {
        this.searchView = searchView;

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (interactor != null)
                    interactor.searchByEventName(newText);
                return false;
            }
        });
    }

    @Override
    public void showLoader() {
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loader.setVisibility(View.GONE);
    }

    @Override
    public Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    public void viewLocation(EventDetails eventDetails) {
        if (interactor != null)
            interactor.showEventLocation(eventDetails);
    }
}
