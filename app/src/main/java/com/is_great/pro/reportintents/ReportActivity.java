package com.is_great.pro.reportintents;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;
/*
we are not using this as it is not a good ui so we use report pager ativity
 */
public class ReportActivity extends SingleFragmentActivity {

    private static final String EXTRA_REPORT_ID="com.is_great.pro.reportintentd.report_id";
    public static Intent newIntent(Context PackageContext, UUID reportID){
        Intent intent=new Intent(PackageContext,ReportActivity.class);
        intent.putExtra(EXTRA_REPORT_ID,reportID);
        return intent;

    }
    @Override
    protected Fragment createFragment(){
        UUID reportId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_REPORT_ID);
        return ReportFragment.newInstance(reportId);
    }

    /*
    * This code is replace by generic code by using abstract class
    * @Override
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
            fragment = new ReportFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.fragment_container,fragment)
                    .commit();
        }
    }*/
}
