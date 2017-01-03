package com.is_great.pro.reportintents;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

/**
 * Created by Pravinyo on 12/22/2016.
 */

public abstract class SingleFragmentActivity extends FragmentActivity {

    protected abstract Fragment createFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        FragmentManager fragmentManager=getSupportFragmentManager();
        //it is a best practise to load the fragment container.
        Fragment fragment= fragmentManager.findFragmentById(R.id.fragment_container);

        //we check because as we know each fragment repeat it's life cycle and we don't want it to
        //initialize everytime when we switch the app
        if(fragment == null)
        {
            fragment = createFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }
}
