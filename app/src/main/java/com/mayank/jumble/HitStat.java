package com.mayank.jumble;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class HitStat extends Fragment {

    ListView listView;
    RequestQueue requestQueue;
    String showUrl = "http://auntkitchen.in/Jumble/showToHitStat.php";
    String[] htext;
    String[] nick;
    int[] support;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hitstat, container, false);

        requestQueue = Volley.newRequestQueue(getContext());

        if (isNetworkAvailable()) {
            trimCache(getContext());
            Toast.makeText(getContext(), "Internet connected", Toast.LENGTH_SHORT).show();
            JSONCLASS();

        } else {
            Toast.makeText(getContext(), "Internet not connected. Plz try again", Toast.LENGTH_SHORT).show();
        }
        return view;
    }

    public void JSONCLASS()
    {
        JsonObjectRequest jsonObjectRequest= new JsonObjectRequest(Request.Method.POST, showUrl,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println(response.toString());
                        try
                        {
                            JSONArray hitArray = response.getJSONArray("hits");
                            HitListCall(hitArray.length());
                            for(int i = 0; i < hitArray.length();i++) {
                                JSONObject hit = hitArray.getJSONObject(i);
                                htext[i] = hit.getString("Htext");
                                support[i] = hit.getInt("Support");
                                nick[i] = hit.getString("Nick");
                            }
                        }catch(JSONException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    void HitListCall(int x) {
        htext=new String[x];
        nick=new String[x];
        support=new int[x];
        HitStatList adapter1 = new HitStatList(getActivity(),htext,support,nick);
        listView = (ListView)getView().findViewById(R.id.hitstat_list);
        listView.setAdapter(adapter1);
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    public static void trimCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // The directory is now empty so delete it
        return dir.delete();
    }

}

