package com.hsn.sureandroidtask.ui.welcome.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.hsn.sureandroidtask.R;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public class WelcomeViewImpl implements WelcomeView, View.OnClickListener {

    private GoogleMap mMap;
    private View mRootView;
    private View eventList;
    private View supplierList;
    private WelcomeInteractor interactor;
    private TextView english, arabic;

    public WelcomeViewImpl(LayoutInflater inflater, ViewGroup container) {
        mRootView = inflater.inflate(R.layout.fragment_welcome, container, false);
        initView(mRootView);
        initListeners();
    }


    @Override
    public View getRootView() {
        return mRootView;
    }

    @Override
    public void initView(View view) {
        supplierList = view.findViewById(R.id.supplierList);
        eventList = view.findViewById(R.id.eventList);
        english = view.findViewById(R.id.english);
        arabic = view.findViewById(R.id.arabic);

    }

    @Override
    public void initListeners() {
        eventList.setOnClickListener(this);
        supplierList.setOnClickListener(this);
        arabic.setOnClickListener(this);
        english.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.supplierList:
                if (interactor != null)
                    interactor.showMediSuppliers();
                break;
            case R.id.eventList:
                if (interactor != null)
                    interactor.showEvents();
                break;
            case R.id.english:
                if (interactor != null)
                    interactor.onEnglishSelected();
                setEnglish();
                break;
            case R.id.arabic:
                if (interactor != null)
                    interactor.onArabicSelected();
                setArabic();
                break;
            default:
                break;
        }
    }

    @Override
    public void setWelcomeInteractor(WelcomeInteractor interactor) {
        this.interactor = interactor;
    }

    @Override
    public void setArabic() {
        english.setTextColor(english.getResources().getColor(R.color.headingTextColor));
        arabic.setTextColor(arabic.getResources().getColor(R.color.colorTextPrimary));

    }

    @Override
    public void setEnglish() {
        arabic.setTextColor(english.getResources().getColor(R.color.headingTextColor));
        english.setTextColor(arabic.getResources().getColor(R.color.colorTextPrimary));
    }
}
