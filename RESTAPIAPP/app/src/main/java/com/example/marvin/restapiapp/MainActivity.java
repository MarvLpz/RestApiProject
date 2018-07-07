package com.example.marvin.restapiapp;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.FrameLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName();
    List<MyList> lstMyList;
    List<ItemCount> lstItemCount;


    RecyclerView recyclerView;

    RecyclerViewAdapter Adapter;

    FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        frameLayout = (FrameLayout) findViewById(R.id.frame_clickable_id);


        lstMyList = new ArrayList<>();
        lstItemCount = new ArrayList<>();



        recyclerView = (RecyclerView) findViewById(R.id.rv_id);
        Adapter = new RecyclerViewAdapter(this, lstMyList,lstItemCount);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(Adapter);

        makeGithubSearchQuery();

Log.e(TAG, "GithubQuery");
    }



            private void makeGithubSearchQuery() {
        String githubQuery = "https://api.stackexchange.com//2.2/questions?order=desc&sort=activity&tagged=android&site=stackoverflow";
        URL githubSearchUrl = NetworkUtils.buildUrl(githubQuery);
        new GithubQueryTask().execute(githubSearchUrl);
    }

    public class GithubQueryTask extends AsyncTask<URL, Void, String> {


        @Override
        protected String doInBackground(URL... params) {
            URL searchUrl = params[0];
            String githubSearchResults = null;
            try {
                githubSearchResults = NetworkUtils.getResponseFromHttpUrl(searchUrl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return githubSearchResults;
        }


        @Override
        protected void onPostExecute(String githubSearchResults) {


            if (githubSearchResults != null && !githubSearchResults.equals("")) {

                StringBuffer buffer2 = new StringBuffer();

                String finalJSON = githubSearchResults;

                try {

                    JSONObject parentObject = new JSONObject(finalJSON);
                    JSONArray parentArray = parentObject.getJSONArray("items");
                    JSONObject finalObject;

                    for (int i = 0; i < parentArray.length();i++) {
                         finalObject = parentArray.getJSONObject(i);

                        String Title = finalObject.getString("title");
                        String link = finalObject.getString("link");
                        String question_id = finalObject.getString("question_id");
                        String creation_date = finalObject.getString("creation_date");
                        String last_activity_date = finalObject.getString("last_activity_date");
                        String Score = finalObject.getString("score");
                        String answer_count = finalObject.getString("answer_count");
                        String view_count = finalObject.getString("view_count");
                        String is_answered = finalObject.getString("is_answered");

                        lstMyList.add (new MyList("Title: " + Title + "\n\n" + "Link: " + link + "\n\n" + "Question_id: "
                                + question_id + "\n\n" + "Creation_date: " + creation_date + "\n\n" +
                                "Last_activity_date: " + last_activity_date + "\n\n" + "Score: " + Score + "\n\n" + "Answer_count: " +
                                answer_count+ "\n\n" + "View_count: " + view_count+ "\n\n" + "Is_answered: " + is_answered));

                        lstItemCount.add(new ItemCount(i));
                        Adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {

            }
        }
    }




}
