package com.is_great.pro.reportintents;

import android.support.v4.app.Fragment;

/**
 * Created by Pravinyo on 12/22/2016.
 */

public class ReportListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new ReportListFragment();
    }
}
