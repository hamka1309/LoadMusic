package com.mia.loadmusic.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mia.loadmusic.R;
import com.mia.loadmusic.model.Music;
import com.mia.loadmusic.util.AppUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Music> listMusics;
    private Context context;
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public HomeAdapter(Context context) {
        this.context = context;
        this.listMusics = new ArrayList<>();
    }

    public void setListMusics(List<Music> listMusics) {
        if (listMusics == null) {
            return;
        }

        if (this.listMusics == null) {
            this.listMusics = new ArrayList<>();
        }
        this.listMusics.clear();
        this.listMusics.addAll(listMusics);
        this.notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_music, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Music music = listMusics.get(position);
        holder.bind(music);
    }

    @Override
    public int getItemCount() {
        return listMusics.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_music)
        AppCompatImageView ivMusic;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_artist)
        TextView tvArtist;
        @BindView(R.id.tv_duration)
        TextView tvDuration;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        private void bind(Music item) {
            if (this.tvName != null) {
                this.tvName.setText(item.getName());
            }

            if (this.tvArtist != null) {
                this.tvArtist.setText(item.getArtist());
            }

            if (this.ivMusic != null) {
                Glide.with(context).load(item.getImage())
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .into(this.ivMusic);
            }

            if (this.tvDuration != null) {
                this.tvDuration.setText(AppUtil.convertTimeMillisecondsToString(item.getDuration()));
            }

        }
    }
}
