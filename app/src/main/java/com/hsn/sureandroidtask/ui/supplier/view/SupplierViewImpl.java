package com.hsn.sureandroidtask.ui.supplier.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.model.SupplierData;
import com.hsn.sureandroidtask.ui.events.adapter.EventAdapter;
import com.hsn.sureandroidtask.ui.supplier.adapter.SupplierAdapter;

import java.util.List;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public class SupplierViewImpl implements SupplierView, SupplierAdapter.OnItemClickListener {

    private View mRootView;
    private SupplierView.Interactor interactor;
    private SupplierAdapter adapter;
    private RecyclerView supplierListView;
    private View loader;
    private Toolbar toolbar;

    public SupplierViewImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_suppliers, container, false);
        initView(mRootView);
        initListeners();

    }


    private void setupRecyclerView() {
        adapter = new SupplierAdapter();
        supplierListView.setLayoutManager(new LinearLayoutManager(getRootView().getContext()));
        supplierListView.setAdapter(adapter);


    }

    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void initView(View view) {
        supplierListView = view.findViewById(R.id.supplierListView);
        toolbar = view.findViewById(R.id.toolbar);
        loader = view.findViewById(R.id.loader);
        setupRecyclerView();
    }

    @Override
    public void initListeners() {
        adapter.setOnItemClickListener(this);
    }

    @Override
    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }




    @Override
    public void bindSuppliers(List<SupplierData> supplierData) {
        adapter.update(supplierData);

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
    public void onClick(SupplierData supplierData) {

    }
}
