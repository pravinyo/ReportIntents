package com.is_great.pro.reportintents;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Pravinyo on 12/22/2016.
 */

public class Report {
    private UUID mid;
    private String mTitle;
    private boolean mResolved;
    private Date mDate;

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date mDate) {
        this.mDate = mDate;
    }


    public boolean isResolved() {
        return mResolved;
    }

    public void setResolved(boolean mResolved) {
        this.mResolved = mResolved;
    }



    public Report()
    {
        mid=UUID.randomUUID();
        mDate= new Date();
    }

    public UUID getID()
    {
        return mid;
    }
    public void setTitle(String Title)
    {
        mTitle=Title;
    }
    public String getTitle()
    {
        return mTitle;
    }

}

