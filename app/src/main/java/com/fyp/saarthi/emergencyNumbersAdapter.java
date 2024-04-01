package com.fyp.saarthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class emergencyNumbersAdapter extends FirestoreRecyclerAdapter<emergencyNumbersModel,emergencyNumbersAdapter.emergencyNumbersViewHolder> {
    private OnListItemClick onListItemClick;
    private Context context;
    public emergencyNumbersAdapter(@NonNull FirestoreRecyclerOptions<emergencyNumbersModel> options,OnListItemClick onListItemClick,Context context) {
        super(options);
        this.onListItemClick=onListItemClick;
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull emergencyNumbersViewHolder holder, int position, @NonNull emergencyNumbersModel model) {
        holder.emergency_name.setText(model.getEmergencyName());
        holder.emergency_number.setText(model.getEmergencyNumber()+"");
    }

    @NonNull
    @Override
    public emergencyNumbersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.emergencylist_item_single,parent,false);
        return new emergencyNumbersViewHolder(view);
    }

    public class emergencyNumbersViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView emergency_name;
        private TextView emergency_number;

        public emergencyNumbersViewHolder(@NonNull View itemView) {
            super(itemView);
            emergency_name=itemView.findViewById(R.id.emergency_name);
            emergency_number=itemView.findViewById(R.id.emergency_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onListItemClick.onItemClick(getItem(getAdapterPosition()),getAdapterPosition(),itemView);
            /*final Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:"+emergency_number));
            context.startActivity(callIntent);*/

        }
    }
    public interface OnListItemClick{
        void onItemClick(emergencyNumbersModel snapshot, int position,View view);

    }
}
