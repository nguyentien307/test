package com.example.tiennguyen.essay.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by TIENNGUYEN on 10/13/2017.
 */

public class GetMultiData extends AsyncTask<ArrayList<String>, Void, JSONArray>
{
    private ProgressDialog dialog;
    DataDownloadListener dataDownloadListener;
    Context ctx;
    public GetMultiData(Context ctx)
    {
        //Constructor may be parametric
        this.dialog = new ProgressDialog(ctx);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        this.ctx = ctx;

    }

    public void setDataDownloadListener(DataDownloadListener dataDownloadListener) {
        this.dataDownloadListener = dataDownloadListener;
    }

    @Override
    protected void onPreExecute() {
        this.dialog.setMessage("Please wait");
        this.dialog.show();
    }

    @Override
    protected JSONArray doInBackground(ArrayList<String>... param) {
        // do your task...
        JSONArray results = new JSONArray();
        HttpURLConnection connection = null;
        BufferedReader reader = null;


            try {
                for (int i = 0; i < param[0].size(); i++) {
                    URL url = new URL(param[0].get(i));
                    connection = (HttpURLConnection) url.openConnection();
                    connection.connect();


                    InputStream stream = connection.getInputStream();

                    reader = new BufferedReader(new InputStreamReader(stream));

                    StringBuffer buffer = new StringBuffer();
                    String line = "";

                    while ((line = reader.readLine()) != null) {
                        buffer.append(line + "\n");
                    }
                    JSONObject result = new JSONObject(buffer.toString());
                    results.put(i, result);
                }

                return results;


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

    @Override
    protected void onPostExecute(JSONArray results)
    {
        if(results != null)
        {
            dataDownloadListener.dataDownloadedSuccessfully(results);
        }
        else {
            dataDownloadListener.dataDownloadFailed();
            Toast.makeText(ctx, "fail", Toast.LENGTH_SHORT).show();
        }

        if (dialog.isShowing()) {
            dialog.dismiss();
        }

        // Setting data to list adapter
    }



    public static interface DataDownloadListener {
        void dataDownloadedSuccessfully(JSONArray data);
        void dataDownloadFailed();
    }
}

