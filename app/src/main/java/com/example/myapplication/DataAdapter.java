package com.example.myapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.MyViewHolder> {

    private ArrayList<String> cTitleList = new ArrayList<>();
    private ArrayList<String> cNameList = new ArrayList<>();
    private ArrayList<String> cUploadDateList = new ArrayList<>();
    private Activity cActivity;
    private int lastPosition = -1;

    public DataAdapter(MainActivity activity, ArrayList<String> cTitleList, ArrayList<String> cNameList, ArrayList<String> cUploadDateList) {
        this.cActivity = activity;
        this.cTitleList = cTitleList;
        this.cNameList = cNameList;
        this.cUploadDateList = cUploadDateList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView c_title, c_author, c_upload_date;

        public MyViewHolder(View view) {
            super(view);
            c_title = (TextView) view.findViewById(R.id.c_title);
            c_author = (TextView) view.findViewById(R.id.c_author);
            c_upload_date = (TextView) view.findViewById(R.id.c_upload_date);
        }
    }


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
       }

    @Override
    public int getItemCount() {

}
