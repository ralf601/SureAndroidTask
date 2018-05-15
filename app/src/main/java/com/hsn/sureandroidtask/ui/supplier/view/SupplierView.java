package com.hsn.sureandroidtask.ui.supplier.view;


import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;

import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.model.SupplierData;
import com.hsn.sureandroidtask.ui.common.view.ViewMvc;

import java.util.List;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public interface SupplierView extends ViewMvc {

    interface Interactor {
        void onSupplierClicked(SupplierData supplierData);
    }

    void setInteractor(Interactor interactor);

    void bindSuppliers(List<SupplierData> supplierData);


    void showLoader();
    void hideLoader();

    Toolbar getToolbar();


}
