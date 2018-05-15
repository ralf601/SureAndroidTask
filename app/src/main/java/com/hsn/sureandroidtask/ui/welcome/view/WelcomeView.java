package com.hsn.sureandroidtask.ui.welcome.view;


import com.hsn.sureandroidtask.ui.common.view.ViewMvc;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public interface WelcomeView extends ViewMvc {

    interface WelcomeInteractor {

        void showEvents();

        void showMediSuppliers();

        void onArabicSelected();

        void onEnglishSelected();
    }

    void setWelcomeInteractor(WelcomeInteractor interactor);

    void setArabic();

    void setEnglish();
}
