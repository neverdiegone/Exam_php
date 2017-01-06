package com.example.nguyendinh.exam_php;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class List_Activity extends AppCompatActivity implements Setting {
    private String url = serverAddress + "list.php";
    private AnimalAdapter adapter;
    private ArrayList<Animal> list;
    private GridView grd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_);
        list = new ArrayList<>();
        new Download().execute(url);
        grd = (GridView) findViewById(R.id.girdview);
        adapter = new AnimalAdapter(list, List_Activity.this);
        grd.setAdapter(adapter);
        grd.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(List_Activity.this, ViewAnimal.class);
                intent.putExtra("id", ((Animal) adapter.getItem(i)).getId());
                intent.putExtra("name", ((Animal) adapter.getItem(i)).getName());
                intent.putExtra("image", ((Animal) adapter.getItem(i)).getImage());
                intent.putExtra("voice", ((Animal) adapter.getItem(i)).getVoice());

                startActivity(intent);
            }
        });
    }

    class Download extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                String b;
                URL u = new URL(strings[0]);
                URLConnection conn = u.openConnection();
                InputStream i = conn.getInputStream();

                StringBuilder temp = new StringBuilder();

                String line = "";
                BufferedReader br = new BufferedReader(new InputStreamReader(i));
                while ((line = br.readLine()) != null) {
                    temp.append(line);
                    temp.append("\n");
                }
                br.close();

                return temp.toString();
            } catch (Exception ex) {
                Log.d("Loi", ex.toString());
                return "";
            }
        }

        @Override
        protected void onPostExecute(String str) {
            super.onPostExecute(str);
            Log.d("str", str);
            try {
                JSONObject obj = new JSONObject(str);
                JSONArray arr = obj.getJSONArray("animal");
                for (int i = 0; i < arr.length(); i++) {
                    Animal a = new Animal();

                    a.setId(Integer.parseInt(arr.getJSONObject(i).getString("id")));
                    a.setName(arr.getJSONObject(i).getString("name"));
                    a.setImage(arr.getJSONObject(i).getString("image"));
                    a.setVoice(arr.getJSONObject(i).getString("voice"));

                    list.add(a);
                }

            } catch (JSONException e) {
                Log.d("JSONException", e.toString());
            }
        }
    }
}
