package org.gptccherthala.virtualqueue.business;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.gptccherthala.virtualqueue.QueueDetails;
import org.gptccherthala.virtualqueue.R;

import java.util.ArrayList;

public class BusinessDataListRecViewAdapter extends RecyclerView.Adapter<BusinessDataListRecViewAdapter.ViewHolder> {

    String businessId;
    String userId;
    private ArrayList<BusinessDatabase> businessDatabase = new ArrayList<>();
    private BusinessDatabase bData;
    private Context mContext;
    private DatabaseReference mDataBase;
    private long qLength;

    public BusinessDataListRecViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public BusinessDataListRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.business_data_list_recview_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        mDataBase = FirebaseDatabase.getInstance().getReference();
        bData = businessDatabase.get(position);
        holder.txtName.setText(bData.getName());
        Glide.with(mContext)
                .asBitmap()
                .load(bData.getImageUrl()).into(holder.image);

        mDataBase.child("user").child(userId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
            @Override
            public void onSuccess(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(businessDatabase.get(position).getbId())) {
                    holder.joinQueue.setEnabled(false);
                }
            }
        });

        holder.joinQueue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bData = businessDatabase.get(position);
                businessId = bData.getbId();

                mDataBase.child("business").child(businessId).get().addOnSuccessListener(new OnSuccessListener<DataSnapshot>() {
                    @Override
                    public void onSuccess(DataSnapshot dataSnapshot) {
                        qLength = dataSnapshot.getChildrenCount() + 1;
                        updateQueueDetails(holder);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        qLength = 1;
                        updateQueueDetails(holder);
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return businessDatabase.size();
    }

    public void setBusinessDatabase(ArrayList<BusinessDatabase> businessDatabase) {
        this.businessDatabase = businessDatabase;
        notifyDataSetChanged();
    }

    public void updateQueueDetails(ViewHolder holder) {
        QueueDetails queueDetails = new QueueDetails(bData.getName(), qLength);

        mDataBase.child("business").child(businessId).child(userId).setValue(queueDetails)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            holder.joinQueue.setEnabled(false);
                            mDataBase.child("user").child(userId).child(businessId).setValue(queueDetails)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful())
                                                Toast.makeText(mContext, "Success", Toast.LENGTH_SHORT).show();
                                            else
                                                Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else
                            Toast.makeText(mContext, "Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView txtName;
        private final ImageView image;
        private final Button joinQueue;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.text_name);
            image = itemView.findViewById(R.id.image);
            joinQueue = itemView.findViewById(R.id.button_join_queue);
        }
    }
}
