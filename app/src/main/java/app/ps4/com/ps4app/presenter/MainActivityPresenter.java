package app.ps4.com.ps4app.presenter;


import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;

import app.ps4.com.ps4app.R;
import app.ps4.com.ps4app.adapter.RVAdapter;
import app.ps4.com.ps4app.helper.Helper;
import app.ps4.com.ps4app.model.Trophy;
import app.ps4.com.ps4app.view.MainActivityView;

public class MainActivityPresenter implements Presenter {

    private MainActivityView view;
    private ArrayList<Trophy> trophies = new ArrayList<>();
    private RecyclerView.Adapter adapter;

    public MainActivityPresenter(MainActivityView mav) {
        view = mav;

    }

    @Override
    public void onCreate() {

        String json = readDataFromAssets("res/raw/trophies.json");
        trophies = parseJson(json);
        RVAdapter adapter = new RVAdapter(trophies,this);
        view.getRecyclerView().setAdapter(adapter);
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onResume() {

    }

    @Override
    public void onDestroy() {

    }

    public void openTrophy(Trophy trophy){
        Bitmap bmp = Helper.decodeBase64(trophy.getImage());
        String trophies = "23/60";//this should be calculated
        int progress = 55;//this should be calculated
        view.openTrophy(trophy.getName(),bmp,trophies,progress);
    }

    private ArrayList<Trophy> parseJson(String json) {
        ArrayList<Trophy> trophies = new ArrayList<>();
        try {
            JSONArray mainObject = new JSONArray(json);
            for (int i = 0; i < mainObject.length(); i++) {
                JSONObject trophy = mainObject.getJSONObject(i);
                String name = trophy.getString("name");
                String image = trophy.getString("image");
                int bronze = trophy.getInt("bronze");
                int gold = trophy.getInt("gold");
                int silver = trophy.getInt("silver");
                int total = trophy.getInt("total");
                Trophy t = new Trophy(name, image, bronze, gold, silver, total);
                trophies.add(t);

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return trophies;
    }

    public String readDataFromAssets(String file) {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder builder = new StringBuilder();

        String line = null;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException e) {

            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return builder.toString();

    }
}
