package com.hsn.sureandroidtask.ui.events.view;


import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.ui.common.view.ViewMvc;

import java.util.List;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public interface EventsView extends ViewMvc {

    interface EventsInteractor {
        void showEventLocation(EventDetails eventDetails);
        void searchByEventName(String name);
    }

    void setInteractor(EventsInteractor interactor);

    void bindEvents(List<EventDetails> eventDetails );

    void setSearchView(SearchView searchView);

    void showLoader();
    void hideLoader();

    Toolbar getToolbar();

}
