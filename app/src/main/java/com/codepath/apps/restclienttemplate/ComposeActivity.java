package com.codepath.apps.restclienttemplate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ComposeActivity extends AppCompatActivity {
    private TwitterClient client = TwitterApp.getRestClient(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compose);
    }


    // publishes the entered text as a tweet
    public void publishTweet(View view) {
        EditText tweet = (EditText) findViewById(R.id.editTweet);
        client.sendTweet(tweet.getText().toString(), new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                // Return the tweet message back to timeline
                EditText tweet = (EditText) findViewById(R.id.editTweet);
                // Prepare data intent
                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("tweet", tweet.getText().toString());
                data.putExtra("code", 200); // ints work too
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity, pass data to parent
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Return the tweet message back to timeline
                EditText tweet = (EditText) findViewById(R.id.editTweet);
                // Prepare data intent
                Intent data = new Intent();
                // Pass relevant data back as a result
                data.putExtra("tweet", tweet.getText().toString());
                data.putExtra("code", 200); // ints work too
                // Activity finished ok, return the data
                setResult(RESULT_OK, data); // set result code and bundle data for response
                finish(); // closes the activity, pass data to parent
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("ComposeActivity", responseString);
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                Log.d("ComposeActivity", errorResponse.toString());
                throwable.printStackTrace();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("ComposeActivity", errorResponse.toString());
                throwable.printStackTrace();
            }
        });
    }
}
