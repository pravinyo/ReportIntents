package com.is_great.pro.reportintents;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by Pravinyo on 12/22/2016.
 */

public class ReportListFragment extends Fragment {
    private RecyclerView mReportRecyclerView;
    private ReportAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle stavedInstanceState){
        View v =inflater.inflate(R.layout.fragment_report_list,container,false);
        mReportRecyclerView=(RecyclerView) v.findViewById(R.id.report_recycler_view);

        mReportRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UpdateUI();
        return v;
    }
    @Override
    public void onResume(){
        super.onResume();
        UpdateUI();
    }

    private void UpdateUI(){
        ReportStore reportStore=ReportStore.get(getActivity());
        List<Report> reports=reportStore.getReports();

        if(mAdapter==null){
            mAdapter= new ReportAdapter(reports);
            mReportRecyclerView.setAdapter(mAdapter);
        }
        else{
            mAdapter.notifyDataSetChanged();
        }
    }

    public class ReportHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private Report mReport;

        private TextView mTitleTextView;
        private TextView mDateTextView;
        private CheckBox mResolvedCheckBox;

        public ReportHolder(View itemview){
            super(itemview);
            itemview.setOnClickListener(this);
            mTitleTextView=(TextView) itemview.findViewById(R.id.list_item_report_text_view);
            mDateTextView = (TextView) itemview.findViewById(R.id.list_item_date_text_view);
            mResolvedCheckBox=(CheckBox) itemview.findViewById(R.id.list_report_resolved_check_box);

        }
        @Override
        public void onClick(View v){
            Toast.makeText(getActivity(),mReport.getTitle()+" Clicked",Toast.LENGTH_SHORT).show();
           // Intent intent=ReportActivity.newIntent(getActivity(),mReport.getID());
            Intent intent=ReportPagerActivity.newIntent(getActivity(),mReport.getID());
            startActivity(intent);
        }
        private void BindReport(Report report){
            mReport=report;
            mTitleTextView.setText(report.getTitle());
            mDateTextView.setText(report.getDate().toString());
            mResolvedCheckBox.setChecked(report.isResolved());
        }
    }
    private class ReportAdapter extends RecyclerView.Adapter<ReportHolder>{

        private List<Report> mReports;
        public ReportAdapter(List<Report> reports){
            mReports=reports;
        }
        @Override
        public ReportHolder onCreateViewHolder(ViewGroup parent,int ViewType){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_report,parent,false);
            return new ReportHolder(view);
        }
        @Override
        public void onBindViewHolder(ReportHolder holder,int position){
            Report report= mReports.get(position);
            holder.BindReport(report);
        }
        @Override
        public int getItemCount(){
            return mReports.size();
        }
    }
}
