package com.hsn.sureandroidtask.ui.supplier.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.common.Utils;
import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.model.SupplierData;
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
import com.hsn.sureandroidtask.ui.supplier.view.SupplierView;
import com.hsn.sureandroidtask.ui.supplier.view.SupplierViewImpl;

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

public class SupplierFragment extends BaseFragment implements
         WebApiResponseListener<List<SupplierData>>,SupplierView.Interactor {


    private SupplierView supplierView;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        supplierView = new SupplierViewImpl(inflater, container);
        supplierView.setInteractor(this);
        ((AppCompatActivity) getActivity()).setSupportActionBar(supplierView.getToolbar());
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.medi_supplier));
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowHomeEnabled(true);
        supplierView.getToolbar().setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });
        return supplierView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            fetchSuppliers();
    }

    @Override
    public void onDataFetched(List<SupplierData> supplierDataList) {
        supplierView.hideLoader();
        supplierView.bindSuppliers(supplierDataList);

    }

    @Override
    public void onError(WebApiError error, String message) {
        supplierView.hideLoader();
        showError(getString(R.string.error_fetch_events));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void fetchSuppliers() {
        supplierView.showLoader();
        WebApiManager.getInstance(getContext()).requestInfoByCity("california",this);
    }


    @Override
    public void onSupplierClicked(SupplierData supplierData) {

    }
}
