package com.frt.mobile.Salas.Data;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.frt.mobile.R;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {

    @NonNull
    @Override
    public ProjectAdapter.ProjectViewHolder onCreateViewHolder(@NonNull viewGroup parent, int viewType){
        LayoutInflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.project_item, parent, false);
        return new ProjectViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectAdapter.ProjectViewHolder holder, int position){

    }

    @Override
    public int getItemCount(){
        return 0;
    }

    public static class ProjectViewHolder extends RecyclerView.ViewHolder{
        
    }
}
