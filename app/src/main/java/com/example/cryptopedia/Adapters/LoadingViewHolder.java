package com.example.cryptopedia.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.example.cryptopedia.R;

public class LoadingViewHolder extends RecyclerView.ViewHolder {

    public ProgressBar progressBar;

    public LoadingViewHolder(View itemView){
        super(itemView);

        progressBar = itemView.findViewById(R.id.progress_bar);


    }
}
