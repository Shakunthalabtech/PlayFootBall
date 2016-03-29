package com.waggieetales.androidapp.playfootball;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.Toast;

import com.waggieetales.androidapp.playfootball.adapters.ListAdapter;
import com.waggieetales.androidapp.playfootball.contracts.IFactory;
import com.waggieetales.androidapp.playfootball.contracts.IVolleyListner;
import com.waggieetales.androidapp.playfootball.factory.BaseFactory;
import com.waggieetales.androidapp.playfootball.models.Game;
import com.waggieetales.androidapp.playfootball.utils.Constants;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String endpoint = "http://api.football-data.org/alpha/soccerseasons/398/fixtures";
    IFactory<Game> GameFactory = new BaseFactory(endpoint);
    IVolleyListner listner ;
    List<Game> gameList;
    ListView mGameListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGameListView = (ListView) findViewById(R.id.listView);

        listner = new IVolleyListner() {
            @Override
            public void onSuccess(Object model) {
                gameList = (List<Game>)model;
                mGameListView.setAdapter(new ListAdapter(MainActivity.this, gameList));
            }

            @Override
            public void onError(String errorMsg) {
                if(errorMsg.equalsIgnoreCase(Constants.NO_CHACHE)){
                    if(isNetworkAvailable()){
                        netWorkCall();
                    }else{
                        Toast.makeText(getApplicationContext(),"No Internet",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getApplicationContext(), "ERROR " + errorMsg, Toast.LENGTH_SHORT).show();
                }
            }
        };

        GameFactory.getOfflineList(listner);

    }



    public void netWorkCall(){
        GameFactory.getList(listner);
    }

    public boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
