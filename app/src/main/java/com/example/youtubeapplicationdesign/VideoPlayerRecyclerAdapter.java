package com.example.youtubeapplicationdesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.RequestManager;
import com.example.youtubeapplicationdesign.model.MediaObject;

import java.util.ArrayList;

public class VideoPlayerRecyclerAdapter extends RecyclerView.Adapter<VideoPlayerRecyclerAdapter.VideoPlayerViewHolder> {

    private ArrayList<MediaObject> mediaObjects;
    private RequestManager requestManager;

    public VideoPlayerRecyclerAdapter(ArrayList<MediaObject> mediaObjects, RequestManager requestManager) {
        this.mediaObjects = mediaObjects;
        this.requestManager = requestManager;
    }

    @NonNull
    @Override
    public VideoPlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VideoPlayerViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.media_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoPlayerViewHolder holder, int position) {
        ((VideoPlayerViewHolder)holder).onBind(mediaObjects.get(position), requestManager);
    }

    @Override
    public int getItemCount() {
        return mediaObjects.size();
    }

    public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {

        FrameLayout media_container;
        TextView title;
        ImageView thumbnail, volumeControl;
        ProgressBar progressBar;
        View parent;
        RequestManager requestManager;

        public VideoPlayerViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView;
            media_container = itemView.findViewById(R.id.media_container);
            thumbnail = itemView.findViewById(R.id.thumbnail);
            title = itemView.findViewById(R.id.title);
            progressBar = itemView.findViewById(R.id.progressBar);
            volumeControl = itemView.findViewById(R.id.volume_control);
        }

        public void onBind(MediaObject mediaObject, RequestManager requestManager) {
            this.requestManager = requestManager;
            parent.setTag(this);
            title.setText(mediaObject.getTitle());
            this.requestManager
                    .load(mediaObject.getThumbnail())
                    .into(thumbnail);
        }

    }
}
