package com.hsn.sureandroidtask.ui.common.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.network.WebApiError;
import com.hsn.sureandroidtask.network.WebApiManager;
import com.hsn.sureandroidtask.network.WebApiResponseListener;
import com.hsn.sureandroidtask.model.SupplierData;
import com.hsn.sureandroidtask.ui.common.view.RootViewImpl;
import com.hsn.sureandroidtask.ui.common.view.ViewMvc;
import com.hsn.sureandroidtask.ui.welcome.controller.WelcomeFragment;

import java.util.List;


public class MainActivity extends AppCompatActivity implements BaseFragment.AbstractFragmentCallback {

    ViewMvc viewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewMvc = new RootViewImpl(this, null);

        // Set the root view of the associated MVC view as the content of this activity
        setContentView(viewMvc.getRootView());

        // Show the default fragment if the application is not restored
        if (savedInstanceState == null) {
            replaceFragment(WelcomeFragment.class, false, null);
        }

    }

    @Override
    public void replaceFragment(Class<? extends Fragment> claz, boolean addToBackStack,
                                Bundle args) {

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();


        Fragment newFragment;

        try {
            // Create new fragment
            newFragment = claz.newInstance();
            if (args != null) newFragment.setArguments(args);
        } catch (InstantiationException e) {
            e.printStackTrace();
            return;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return;
        }

        if (addToBackStack) {
            // Add this transaction to the back stack
            ft.addToBackStack(null);
        }

        // Change to a new fragment
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.frame_contents, newFragment, claz.getClass().getSimpleName());
        ft.commit();


    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack, Bundle args) {

        if (args != null) fragment.setArguments(args);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (addToBackStack) {
            // Add this transaction to the back stack
            ft.addToBackStack(null);
        }

        // Change to a new fragment
        ft.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        ft.replace(R.id.frame_contents, fragment, fragment.getClass().getSimpleName());
        ft.commit();
    }


    private void testSoapRequest() {
        WebApiManager
                .getInstance(getApplicationContext())
                .requestInfoByCity("california", new WebApiResponseListener<List<SupplierData>>() {
                    @Override
                    public void onDataFetched(List<SupplierData> data) {
                        Log.i("Api", "Fetched Items:" + data.size());

                    }

                    @Override
                    public void onError(WebApiError error, String message) {
                        Log.e("Api", message);
                    }
                });

    }
}
