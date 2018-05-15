package com.hsn.sureandroidtask.ui.common.controller;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

/**
 * Created by hassanshakeel on 2/27/18.
 */

public abstract  class BaseFragment extends Fragment {


    private AbstractFragmentCallback mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (AbstractFragmentCallback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement " + AbstractFragmentCallback.class.getCanonicalName());
        }

    }


    public void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack,
                                Bundle args) {
        mCallback.replaceFragment(claz, addToBackStack, args);
    }



    public interface AbstractFragmentCallback {

        void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack,
                             Bundle args);

        void replaceFragment(Fragment fragment, boolean addToBackStack,
                             Bundle args);

    }

    protected void toastShort(String text) {
        Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_SHORT);
    }

    protected void toastLong(String text) {
        Toast.makeText(getActivity().getApplicationContext(), text, Toast.LENGTH_LONG);
    }


    protected void showError(String text) {
        Toasty.error(getActivity(),text).show();
    }

    protected void showInfo(String text) {
        Toasty.info(getActivity(),text).show();
    }

    protected void showWarning(String text) {
        Toasty.warning(getActivity(),text).show();
    }


}
