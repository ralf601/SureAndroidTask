package com.hsn.sureandroidtask.ui.common.view;

import android.view.View;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public interface  ViewMvc {

    View getRootView();


    void initView(View view);


    void initListeners();

}