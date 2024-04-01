package com.fyp.saarthi;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ActivityAdminDisplayWaterList extends AppCompatActivity implements waterTimingAdapter.OnLongItemClick{
    private FirebaseFirestore wtfirebaseFirestore;
    private RecyclerView wtFirestoreList;
    private FirestoreRecyclerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_display_water_list);
        waterTimingModel waterTiming = (waterTimingModel) getIntent().getSerializableExtra("waterTiming");
        wtfirebaseFirestore = FirebaseFirestore.getInstance();
        wtFirestoreList = findViewById(R.id.waterTimingList);

        Query query = wtfirebaseFirestore.collection("waterDateAndTime");
        FirestoreRecyclerOptions<waterTimingModelAdmin> options = new FirestoreRecyclerOptions.Builder<waterTimingModelAdmin>()
                .setQuery(query, waterTimingModelAdmin.class)
                .build();
        adapter=new waterTimingAdapter(options,this,this,this);
        wtFirestoreList.setHasFixedSize(true);
        wtFirestoreList.setLayoutManager(new LinearLayoutManager(this));
        wtFirestoreList.setAdapter(adapter);
        /*new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                adapter.deleteItem(position);

            }
        }).attachToRecyclerView(wtFirestoreList);*/
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
    public void onItemClick(int position) {
       //Toast.makeText(getApplicationContext(),"Deleted Successfully",Toast.LENGTH_SHORT);

    }

    @Override
    public void onDeleteClick(int position) {

    }
}
