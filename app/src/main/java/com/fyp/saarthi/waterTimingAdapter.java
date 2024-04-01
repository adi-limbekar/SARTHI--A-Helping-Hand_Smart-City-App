package com.fyp.saarthi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class waterTimingAdapter extends FirestoreRecyclerAdapter<waterTimingModelAdmin, waterTimingAdapter.waterTimingViewHolder> {
    private static OnLongItemClick onLongItemClick,onDeleteClick;
    private Context context;


    public waterTimingAdapter(@NonNull FirestoreRecyclerOptions<waterTimingModelAdmin> options,OnLongItemClick onLongItemClick,OnLongItemClick onDeleteClick,Context context) {
        super(options);
        this.onLongItemClick=onLongItemClick;
        this.onDeleteClick=onDeleteClick;
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull waterTimingViewHolder holder, int position, @NonNull waterTimingModelAdmin model) {
        holder.area.setText(model.getArea());
        holder.date.setText(model.getDate());
        holder.time.setText(model.getTime());

    }

    @NonNull
    @Override
    public waterTimingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.watertiming_item_single_admin, parent, false);
        return new waterTimingViewHolder(view);
    }


    public void deleteItem(int position){
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    public class waterTimingViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener{
        private TextView area;
        private TextView date;
        private TextView time;
        private ImageView delete;


        public waterTimingViewHolder (@NonNull View itemview) {
            super(itemview);
            delete=itemView.findViewById(R.id.delete);
            area = itemView.findViewById(R.id.area);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            itemView.setOnLongClickListener(this);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onLongItemClick.onDeleteClick(getAdapterPosition());
                    deleteItem();
                }
            });
         }




        public void deleteItem() {
            getSnapshots().getSnapshot(getAdapterPosition()).getReference().delete();
        }

        @Override
        public boolean onLongClick(View view) {
            return false;
        }
    }
    public interface OnLongItemClick{
        void onItemClick(int position);
        void onDeleteClick(int position);

    }
}
