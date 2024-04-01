package com.fyp.saarthi;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.firebase.ui.firestore.SnapshotParser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityWaterTankNumbers extends AppCompatActivity implements waterTankNumbersAdapter.OnWaterListItemClick{
    private FirebaseFirestore wtfirebaseFirestore;
    private RecyclerView wtFirestoreList;
    private waterTankNumbersAdapter waterAdapter;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_tank_numbers);

        wtfirebaseFirestore = FirebaseFirestore.getInstance();
        wtFirestoreList = findViewById(R.id.waterTankNumbersList);
        Query query=wtfirebaseFirestore.collection("waterTankNumbers");

        FirestoreRecyclerOptions<waterTankNumbersModel> options= new FirestoreRecyclerOptions.Builder<waterTankNumbersModel>()
                .setQuery(query,new SnapshotParser<waterTankNumbersModel>() {
                    @NonNull
                    @Override
                    public waterTankNumbersModel parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        waterTankNumbersModel waterTankNumbersModel=snapshot.toObject(waterTankNumbersModel.class);
                        String itemId=snapshot.getId();
                        waterTankNumbersModel.setItem_id(itemId);
                        return waterTankNumbersModel;
                    }
                })
                .build();
        waterAdapter=new waterTankNumbersAdapter(options,this,this);
        wtFirestoreList.setHasFixedSize(true);
        wtFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        wtFirestoreList.setAdapter(waterAdapter);
    }




    @Override
    protected void onStop() {
        super.onStop();
        waterAdapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        waterAdapter.startListening();
    }

    @Override
    public void onItemClick(waterTankNumbersModel snapshot, int position, View view) {
        Log.d("ITEM CLICKED","CLICKED on position:"+position+"Number is:"+snapshot.getTankNumber());
        final Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+snapshot.getTankNumber()));
        startActivity(callIntent);
    }
}