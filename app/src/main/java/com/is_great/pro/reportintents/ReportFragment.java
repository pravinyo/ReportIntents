package com.is_great.pro.reportintents;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import java.util.Date;
import java.util.UUID;

/**
 * Created by Pravinyo on 12/22/2016.
 */

public class ReportFragment extends Fragment {
    private static final String ARG_REPORT_ID="report_id";
    private static final String Dialog_Date="DialogDate";
    private static final int REQUEST_DATE=0;
    private Report mReport;
    private EditText mTitle;
    private Button mDateButton;
    private CheckBox mResolvedCheckbox;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mReport = new Report();
        UUID reportId=(UUID) getArguments().getSerializable(ARG_REPORT_ID);
        mReport=ReportStore.get(getActivity()).getReport(reportId);
    }

    public static ReportFragment newInstance(UUID reportId){
        Bundle arg=new Bundle();
        arg.putSerializable(ARG_REPORT_ID,reportId);
        ReportFragment fragment = new ReportFragment();
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v =inflater.inflate(R.layout.fragment_report,container,false);

        mTitle=(EditText) v.findViewById(R.id.report_title);
        mTitle.setText(mReport.getTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mReport.setTitle(s.toString());

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mDateButton =(Button) v.findViewById(R.id.report_date);
        mDateButton.setText(mReport.getDate().toString());
        mDateButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                FragmentManager fragmentManager = getFragmentManager();
                DatePickerFragment dialog =DatePickerFragment.newInstance(mReport.getDate());
                dialog.setTargetFragment(ReportFragment.this,REQUEST_DATE);
                dialog.show(fragmentManager,Dialog_Date);
            }
        });

        mResolvedCheckbox=(CheckBox) v.findViewById(R.id.report_resolved);
        mResolvedCheckbox.setChecked(mReport.isResolved());
        mResolvedCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mReport.setResolved(isChecked);
            }
        });

        return v;
    }
    @Override
    public void onActivityResult(int requestCode,int resultCode,Intent data){
        if(resultCode != Activity.RESULT_OK){
            return;
        }
        if(requestCode == REQUEST_DATE){
            Date date = (Date) data.getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mReport.setDate(date);
            mDateButton.setText(mReport.getDate().toString());
        }
    }
}

