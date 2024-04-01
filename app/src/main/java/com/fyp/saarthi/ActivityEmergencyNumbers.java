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

public class ActivityEmergencyNumbers extends AppCompatActivity  implements emergencyNumbersAdapter.OnListItemClick {

    private FirebaseFirestore efirebaseFirestore;
    private RecyclerView eFirestoreList;
    private emergencyNumbersAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_numbers);

        efirebaseFirestore=FirebaseFirestore.getInstance();
        eFirestoreList=findViewById(R.id.emergencyNumbersList);

        //query
        Query query= efirebaseFirestore.collection("emergencyNumbers");
        //recycler options
        FirestoreRecyclerOptions<emergencyNumbersModel> options=new FirestoreRecyclerOptions.Builder<emergencyNumbersModel>()
                .setQuery(query,new SnapshotParser<emergencyNumbersModel>() {
                    @NonNull
                    @Override
                    public emergencyNumbersModel parseSnapshot(@NonNull DocumentSnapshot snapshot) {
                        emergencyNumbersModel emergencyNumbersModel=snapshot.toObject(emergencyNumbersModel.class);
                        String itemId=snapshot.getId();
                        emergencyNumbersModel.setItem_id(itemId);
                        return emergencyNumbersModel;
                    }
                })
                .build();

        adapter= new emergencyNumbersAdapter(options,this,this);
        eFirestoreList.setHasFixedSize(true);
        eFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        eFirestoreList.setAdapter(adapter);

    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


    @Override
    public void onItemClick(emergencyNumbersModel snapshot, int position,View view) {
        Log.d("ITEM CLICKED","CLICKED on position:"+position+"Id is:"+snapshot.getEmergencyNumber());
        /*Intent intent=new Intent(ActivityEmergencyNumbers.this,ActivityUpdateWaterTiming.class);
        startActivity(intent);*/
        final Intent callIntent = new Intent(Intent.ACTION_DIAL);
        callIntent.setData(Uri.parse("tel:"+snapshot.getEmergencyNumber()));
        startActivity(callIntent);


    }
}
