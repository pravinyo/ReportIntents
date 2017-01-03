package com.is_great.pro.reportintents;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.List;
import java.util.UUID;

/**
 * Created by Pravinyo on 12/24/2016.
 */

public class ReportPagerActivity extends FragmentActivity {
    private static final String EXTRA_REPORT_ID="com.is_great.pro.reportintentd.report_id";
    private ViewPager mViewPager;
    private List<Report> mReports;

    public static Intent newIntent(Context PackageContecxt,UUID reportId){
        Intent intent=new Intent(PackageContecxt,ReportPagerActivity.class);
        intent.putExtra(EXTRA_REPORT_ID,reportId);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_pager);

        mViewPager =(ViewPager) findViewById(R.id.activity_report_pager_view_pager);

        UUID reportId = (UUID) getIntent()
                .getSerializableExtra(EXTRA_REPORT_ID);
        mReports=ReportStore.get(this).getReports();
        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                Report report = mReports.get(position);
                return ReportFragment.newInstance(report.getID());
            }

            @Override
            public int getCount() {
                return mReports.size();
            }
        });

        for(int i=0;i<mReports.size();i++){
            if(mReports.get(i).getID().equals(reportId)){
                mViewPager.setCurrentItem(i);
                break;
            }
        }

    }
}
