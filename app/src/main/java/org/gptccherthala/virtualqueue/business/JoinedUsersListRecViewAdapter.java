package org.gptccherthala.virtualqueue.business;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import org.gptccherthala.virtualqueue.R;
import org.gptccherthala.virtualqueue.user.UserDatabase;

import java.util.ArrayList;

public class JoinedUsersListRecViewAdapter extends RecyclerView.Adapter<JoinedUsersListRecViewAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<UserDatabase> userDatabase = new ArrayList<>();

    public JoinedUsersListRecViewAdapter(Context mContext) {
        this.mContext = mContext;
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

        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext, "000")
                .setSmallIcon(R.drawable.alert)
                .setContentTitle("My notification")
                .setContentText("Much longer text that cannot fit one line...")
                .setStyle(new NotificationCompat.BigTextStyle()
                        .bigText("Much longer text that cannot fit one line..."))
                .setPriority(NotificationCompat.PRIORITY_MAX);

        holder.alert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NotificationManagerCompat notificationManager = NotificationManagerCompat.from(mContext);

                notificationManager.notify(000, builder.build());

            }
        });

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

    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Test";
            String description = "Test Channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel("000", name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = mContext.getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

}
