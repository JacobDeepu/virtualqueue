package org.gptccherthala.virtualqueue.business;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.gptccherthala.virtualqueue.R;
import org.gptccherthala.virtualqueue.user.UserDatabase;

import java.util.ArrayList;

public class JoinedUsersListRecViewAdapter extends RecyclerView.Adapter<JoinedUsersListRecViewAdapter.ViewHolder> {

    private ArrayList<UserDatabase> userDatabase = new ArrayList<>();

    public JoinedUsersListRecViewAdapter() {
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joined_users_list, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.uName.setText(userDatabase.get(position).name);
    }

    @Override
    public int getItemCount() {
        return userDatabase.size();
    }

    public void setUserDatabase(ArrayList<UserDatabase> userDatabase) {
        this.userDatabase = userDatabase;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView uName;
        ImageView userImage;
        ImageButton call, alert;
        Button delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.img);
            call = itemView.findViewById(R.id.call);
            alert = itemView.findViewById(R.id.alert);
            delete = itemView.findViewById(R.id.delete);
            uName = itemView.findViewById(R.id.uName);
        }
    }
}
