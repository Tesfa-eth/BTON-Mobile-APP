package com.application.bton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Layout;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
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
    Button btnGeneralCampusView, btnSearchCatagory;
    public final List<gallery>[] result = new List[]{new ArrayList<>()};
    public final List<GalleryCatagories>[] resultCategory = new List[]{new ArrayList<>()};
    //public final ArrayList<List<GalleryCatagories>> arrayCategory = new ArrayList<>();
    public final String[] spinnerStorage = {"galleryCatagory"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_campus_gallery);

        // get spinner
        Spinner galleryCategorySpinner = (Spinner) findViewById(R.id.spinnerGalleryCategory);
        ArrayAdapter<CharSequence> adapterGalleryCategory = ArrayAdapter.createFromResource(this,
                R.array.galleryCategories, android.R.layout.simple_spinner_item);
        adapterGalleryCategory.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        galleryCategorySpinner.setAdapter(adapterGalleryCategory);
        // get everything from the GalleryCatagories table and store it in resultCategory
        //getALlCatagories();
        galleryCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCategory = parent.getItemAtPosition(position).toString();
                if(selectedCategory.equals("Categories")){
                    return;
                }
                else{
                    spinnerStorage[0] = selectedCategory;
                    getByCatagory();
                    btnGeneralCampusView.setText("General Campus View");
                }
                //toastMessage(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //btn search category
        btnSearchCatagory = findViewById(R.id.btnSearchCatagory);
        btnSearchCatagory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadCatagory();
            }
        });

        btnGeneralCampusView = findViewById(R.id.btnGeneralCampusView);
        btnGeneralCampusView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadMore();
                toastMessage("Next page");
                btnGeneralCampusView.setText("Next page");

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
                    return;
                    //toastMessage("Start time" + (System.currentTimeMillis() - startTime));
                }
            }

            @Override
            public void handleFault(BackendlessFault fault) {

            }
        });
    }

    public void loadCatagory(){
        // calls adapter view
        //toastMessage(spinnerStorage[0] + "got" + resultCategory[0].get(0).getPhoto_url());
        ListView mListView = findViewById(R.id.imageListView);
        MyCatagoryAdapter myCatagoryAdapter = new MyCatagoryAdapter();
        mListView.setAdapter(myCatagoryAdapter);
    }

    public void getByCatagory(){
        GalleryCatagories galleryCatagories = new GalleryCatagories();
        final int PAGESIZE = 100;
        final Integer[] currentPage = {0};
        DataQueryBuilder queryBuilder = DataQueryBuilder.create();
        String whereclause = "title = '" + spinnerStorage[0] + "'";
        queryBuilder.setWhereClause(whereclause);
        queryBuilder.setPageSize(PAGESIZE);
        Backendless.Persistence.of(GalleryCatagories.class).find(queryBuilder, new AsyncCallback<List<GalleryCatagories>>() {
            @Override
            public void handleResponse(List<GalleryCatagories> response) {
                resultCategory[0]=response;
                int size = response.size();
                if (size == PAGESIZE) {
                    queryBuilder.prepareNextPage();
                    Backendless.Data.of(GalleryCatagories.class).find(queryBuilder, this);
                } else {
                    return;
                    //("Start time" + (System.currentTimeMillis() - startTime));
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

    public class MyCatagoryAdapter extends BaseAdapter {

        List<GalleryCatagories> response = resultCategory[0];

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
            //toastMessage("arrayResult: "+ (arrayCategory.size()*arrayCategory.get(0).size()));
            ImageView profileImage = convertView.findViewById(R.id.cardViewGalleryImage);
            Glide.with(CampusGallery.this).load(response.get(position).getPhoto_url()).into(profileImage);
            return convertView;
        }
    }


    public void toastMessage( String msg){
        Toast.makeText(CampusGallery.this, msg, Toast.LENGTH_SHORT).show();
    }
}