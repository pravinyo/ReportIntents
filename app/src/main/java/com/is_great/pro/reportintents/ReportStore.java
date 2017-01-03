package com.is_great.pro.reportintents;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Pravinyo on 12/22/2016.
 */

public class ReportStore {
    private static ReportStore sReportStore;
    private List<Report> mReports;// for storing report ;
    public static ReportStore get(Context context){
        if(sReportStore == null)
        {
            sReportStore = new ReportStore(context);
        }
        return sReportStore;
    }
    private ReportStore(Context context){
        mReports = new ArrayList<>();
        for(int i=0;i<100;i++){
            Report report= new Report();
            report.setTitle("Report #"+(i+1));
            report.setResolved(i%3==0);
            mReports.add(report);
        }
    }
    public List<Report> getReports()
    {
        return mReports;
    }
    public Report getReport(UUID id)
    {
        for (Report report:mReports)
        {
            if(report.getID().equals(id))
            {
                return report;
            }

        }
        return null;
    }
}

