package com.elouazzani.myapplicationgemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.elouazzani.myapplicationgemo.R;
import com.elouazzani.myapplicationgemo.model.GitHubRepo;

import java.util.List;

public class GitHubRepoAdapter extends RecyclerView.Adapter<GitHubRepoAdapter.ViewHolder> {
    Context context;
    private List<GitHubRepo> repList;

    public GitHubRepoAdapter(Context context, List<GitHubRepo> repList) {
        this.context=context;
        this.repList=repList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView repNameText, repDescriptionText, usernameText, nbrStarsText;
        ImageView avatar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            repNameText=itemView.findViewById(R.id.repNameText);
            repDescriptionText=itemView.findViewById(R.id.repDescriptionText);
            usernameText=itemView.findViewById(R.id.usernameText);
            nbrStarsText=itemView.findViewById(R.id.numberOfStarsText);
            avatar=itemView.findViewById(R.id.avatar);

        }
    }

    @NonNull
    @Override
    public GitHubRepoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view_trending_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GitHubRepoAdapter.ViewHolder holder, int position) {
        final GitHubRepo repository= repList.get(position);

        if (repository!=null) {
            holder.repNameText.setText(repository.getName());
            holder.repDescriptionText.setText(repository.getDescription());
            holder.usernameText.setText(repository.getLogin());
            holder.nbrStarsText.setText(String.format("%.1f",repository.getStargazersCount()));
            Glide.with(context).load(repository.getAvatar_url()).into(holder.avatar);


        }

    }

    @Override
    public int getItemCount() {
        return repList.size();
    }


}
