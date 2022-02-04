package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.DataQueryBuilder;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CampusGallery extends AppCompatActivity {

    //declaring variables
    ImageView sampleImage;
    Button sampleButton;
    //public final ArrayList result = new ArrayList<List<gallery>>();
    public final List<gallery>[] result = new List[]{new ArrayList<>()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_gallery);

        sampleButton = findViewById(R.id.sampleButton);
        sampleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMore();
                toastMessage("loading...");
                sampleButton.setText("Next page");

            }
        });

        gallery galleryVar = new gallery();
        final long startTime = System.currentTimeMillis();
        final int PAGESIZE = 80;
        final Integer[] currentPage = {0};
        // Todo: controlled next page loading rather than automated one.

        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        queryBuilder.setPageSize(PAGESIZE);
        Backendless.Persistence.of(gallery.class).find(queryBuilder, new AsyncCallback<List<gallery>>() {
            @Override
            public void handleResponse(List<gallery> response) {
                result[0] = response;
                // TODO MULTIPLE PAGE LOADING
                int size = response.size();
                if (size == PAGESIZE) {
                    queryBuilder.prepareNextPage();
                    Backendless.Data.of(gallery.class).find(queryBuilder, this);
                } else {
                    toastMessage("Start time" + (System.currentTimeMillis() - startTime));
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void loadMore(){
        ListView mListView = findViewById(R.id.imageListView);
        MyAdapter myAdapter = new MyAdapter();
        mListView.setAdapter(myAdapter);
    }

    public class MyAdapter extends BaseAdapter {

        List<gallery> response = result[0];

        @Override
        public int getCount() {
            return response.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView = getLayoutInflater().inflate(R.layout.card_gallery_images, parent, false);
            // display the image
            ImageView profileImage = convertView.findViewById(R.id.cardViewGalleryImage);
            Glide.with(CampusGallery.this).load(response.get(position).getPhoto_url()).into(profileImage);
            return convertView;
        }
    }


    public void toastMessage( String msg){
        Toast.makeText(CampusGallery.this, msg, Toast.LENGTH_SHORT).show();
    }
}