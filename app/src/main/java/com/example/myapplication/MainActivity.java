package com.example.myapplication;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    //ProgressBar pd;
    TextView ctext;
    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private ProgressDialog mProgressDialog;
    private String url = "https://github.com/atapata/MyApplication/commits/master";
    private ArrayList<String> cNameList = new ArrayList<>();
    private ArrayList<String> cUploadDateList = new ArrayList<>();
    private ArrayList<String> cTitleList = new ArrayList<>();
    // URL to get contacts JSON
    //private static String url = "https://github.com/atapata/MyApplication/commits/master";

    ArrayList<HashMap<String, String>> contactList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
      //  pd=findViewById(R.id.cprogressbar);
         ctext=findViewById(R.id.ctext1);
        contactList = new ArrayList<>();

        //lv = (ListView) findViewById(R.id.list);

        new Description().execute();
    }
 private class Description extends AsyncTask<Void, Void, Void> {
        String desc;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(MainActivity.this);
            mProgressDialog.setTitle("Android Basic JSoup Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                // Connect to the web site
                Document mBlogDocument = Jsoup.connect(url).get();
                // Using Elements to get the Meta data
                Elements mElementDataSize = mBlogDocument.select("a[class=commit-author tooltipped tooltipped-s user-mention]");
                Log.d("aaaaaaaaaaa11111111111", String.valueOf(mElementDataSize));
                // Locate the content attribute
                int mElementSize = mElementDataSize.size();

                for (int i = 0; i < mElementSize; i++) {
                    Elements mElementAuthorName = mBlogDocument.select("a[class=message js-navigation-open]").select("a").eq(i);
                    String mAuthorName = mElementAuthorName.text();
                    Log.d("bbbbbbbbbbb222222222",mAuthorName);

                    Elements mElementBlogUploadDate = mBlogDocument.select("div[class=commit-meta commit-author-section no-wrap d-flex flex-items-center mt-1]").eq(i);
                    String mBlogUploadDate = mElementBlogUploadDate.text();
                    Log.d("cccccccccc33333333",mBlogUploadDate);

                    Elements mElementBlogTitle = mBlogDocument.select("h2[class=entry-title]").select("a").eq(i);
                    String mBlogTitle = mElementBlogTitle.text();

                    cNameList.add(mAuthorName);
                    cUploadDateList.add(mBlogUploadDate);
                    cTitleList.add(mBlogTitle);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            // Set description into TextView

            RecyclerView mRecyclerView = (RecyclerView)findViewById(R.id.act_recyclerview);

            DataAdapter mDataAdapter = new DataAdapter(MainActivity.this, cTitleList, cNameList, cUploadDateList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mDataAdapter);

            mProgressDialog.dismiss();
        }
    }


}

