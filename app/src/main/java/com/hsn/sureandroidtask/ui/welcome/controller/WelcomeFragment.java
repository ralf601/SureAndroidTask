package com.hsn.sureandroidtask.ui.welcome.controller;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hsn.sureandroidtask.ui.common.controller.BaseFragment;
import com.hsn.sureandroidtask.ui.common.controller.MainActivity;
import com.hsn.sureandroidtask.ui.events.controller.EventsFragment;
import com.hsn.sureandroidtask.ui.supplier.controller.SupplierFragment;
import com.hsn.sureandroidtask.ui.welcome.view.WelcomeView;
import com.hsn.sureandroidtask.ui.welcome.view.WelcomeViewImpl;

import java.util.Locale;


/**
 * Created by hassanshakeel on 2/27/18.
 */

public class WelcomeFragment extends BaseFragment implements WelcomeView.WelcomeInteractor {


    private WelcomeView welcomeView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        welcomeView = new WelcomeViewImpl(inflater, container);
        welcomeView.setWelcomeInteractor(this);
        if (Locale.getDefault().getLanguage().equals("ar"))
            welcomeView.setArabic();
        else
            welcomeView.setEnglish();
        return welcomeView.getRootView();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void showEvents() {
        ((MainActivity) getActivity()).replaceFragment(EventsFragment.class, true, null);
    }

    @Override
    public void showMediSuppliers() {
        ((MainActivity) getActivity()).replaceFragment(SupplierFragment.class, true, null);

    }

    @Override
    public void onArabicSelected() {
        setLocale("ar");
    }

    @Override
    public void onEnglishSelected() {
        setLocale("en");

    }

    public void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getActivity().getBaseContext().getResources().updateConfiguration(config,
                getActivity().getBaseContext().getResources().getDisplayMetrics());


//        Locale myLocale = new Locale(lang);
//        Resources res = getResources();
//        DisplayMetrics dm = res.getDisplayMetrics();
//        Configuration conf = res.getConfiguration();
//        conf.locale = myLocale;
//        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(getActivity(), MainActivity.class);
        startActivity(refresh);
        getActivity().finish();
    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {}


}
