package com.waggieetales.androidapp.playfootball.factory;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.waggieetales.androidapp.playfootball.config.AppConfig;
import com.waggieetales.androidapp.playfootball.contracts.IFactory;
import com.waggieetales.androidapp.playfootball.contracts.IVolleyListner;
import com.waggieetales.androidapp.playfootball.models.Game;
import com.waggieetales.androidapp.playfootball.utils.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BaseFactory implements IFactory {

    String endPoints;
    Gson gson;

    public BaseFactory(String endpoints) {
        endPoints = endpoints;
        gson = new GsonBuilder().create();

    }

    @Override
    public void getList(final IVolleyListner listner) {

        Response.Listener onSuccess = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Type listType = new TypeToken<ArrayList<Game>>() {
                }.getType();
                JSONObject jobj = null;
                try {
                    jobj = new JSONObject(response);
                    listner.onSuccess(gson.fromJson(String.valueOf(jobj.get("fixtures")), listType));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        Response.ErrorListener onError = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listner.onError(error.toString());
            }
        };

        StringRequest reqList = new StringRequest(Request.Method.GET, endPoints, onSuccess, onError);

        AppConfig.requestQueue.add(reqList);

    }

    @Override
    public void getOfflineList(final IVolleyListner listner) {

        Cache cache = AppConfig.getInstance().requestQueue.getCache();
        Cache.Entry entry = cache.get(endPoints);
        if (entry != null) {
            try {
                String data = new String(entry.data, "UTF-8");
                JSONObject jobj = new JSONObject(data);
                Type listType = new TypeToken<ArrayList<Game>>() {}.getType();
                listner.onSuccess(gson.fromJson(String.valueOf(jobj.get("fixtures")), listType));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            listner.onError(Constants.NO_CHACHE);
        }
    }
}
