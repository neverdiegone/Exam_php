package com.example.nguyendinh.exam_php;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by nguyendinh on 06/01/2017.
 */

public class AnimalAdapter extends BaseAdapter implements Setting {
    ArrayList<Animal> list;
    Context c;

    public AnimalAdapter(ArrayList<Animal> list, Context c) {
        this.list = list;
        this.c = c;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Animal_cell animal_cell;
        if(view == null)
        {
            animal_cell = new Animal_cell();
            LayoutInflater infl = ((Activity)c).getLayoutInflater();
            view = infl.inflate(R.layout.layout_cell, viewGroup, false);
            animal_cell.textView = (TextView)view.findViewById(R.id.tvcell);
            animal_cell.img = (ImageView)view.findViewById(R.id.imgcell);

            view.setTag(animal_cell);
        }
        else {
            animal_cell = (Animal_cell) view.getTag();
        }
        animal_cell.textView.setText(list.get(i).getName());
        String url =serverAddress+list.get(i).getImage();
        new DowloadImage(animal_cell.img).execute(url);
        return view;
    }
    class Animal_cell
    {
        ImageView img;
        TextView textView;
    }
    class DowloadImage extends AsyncTask<String,Integer,Bitmap>
    {
        ImageView imageView;

        public DowloadImage(ImageView imageView) {
            this.imageView = imageView;
        }
        Bitmap bitmap;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                InputStream inputStream = connection.getInputStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                Log.d("MalformeURL",e.toString());
            } catch (IOException e) {
                Log.d("IOException",e.toString());
            }
            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            imageView.setImageBitmap(bitmap);
        }
    }
}
