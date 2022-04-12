package com.example.pollingtest;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PollRVAdapter extends RecyclerView.Adapter<PollRVAdapter.ViewHolder> {
    //creating variables for our list, context, interface and position.
    private final ArrayList<PollRVModal> pollRVModalArrayList;
    private final Context context;
    private final PollClickInterface pollClickInterface;
    int lastPos = -1;

    //creating a constructor.
    public PollRVAdapter(ArrayList<PollRVModal> pollRVModalArrayList, Context context, PollClickInterface PollClickInterface) {
        this.pollRVModalArrayList = pollRVModalArrayList;
        this.context = context;
        this.pollClickInterface = PollClickInterface;
    }

    @NonNull
    @Override
    public PollRVAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating our layout file on below line.
        View view = LayoutInflater.from(context).inflate(R.layout.poll_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PollRVAdapter.ViewHolder holder, int position) {

        //setting data to our recycler view item on below line.
        PollRVModal PollRVModal = pollRVModalArrayList.get(position);
        holder.pollTV.setText(PollRVModal.getPollName());
        Picasso.get().load(PollRVModal.getPollImg()).into(holder.pollIV);
        //adding animation to recycler view item on below line.
        setAnimation(holder.itemView, position);
        holder.pollIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pollClickInterface.onPollClick(position);
            }
        });
    }

    private void setAnimation(View itemView, int position) {
        if (position > lastPos) {
            //on below line we are setting animation.
            Animation animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left);
            itemView.setAnimation(animation);
            lastPos = position;
        }
    }

    @Override
    public int getItemCount() {
        return pollRVModalArrayList.size();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //creating variable for our image view and text view on below line.
        private  ImageView pollIV;
        private  TextView pollTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //initializing all our variables on below line.
            pollIV = itemView.findViewById(R.id.idIVPoll);
            pollTV = itemView.findViewById(R.id.idTVPollName);
        }
    }

    //creating a interface for on click
    public interface PollClickInterface {
        void onPollClick(int position);
    }
}

