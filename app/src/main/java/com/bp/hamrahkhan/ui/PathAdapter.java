package com.bp.hamrahkhan.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bp.hamrahkhan.R;
import com.bp.hamrahkhan.model.path.Path;

import java.util.List;

public class PathAdapter extends RecyclerView.Adapter<PathAdapter.PathHolder> {
    Context context;
    List<Path> pathList;

    public PathAdapter(Context context, List<Path> pathList) {
        this.context = context;
        this.pathList = pathList;
    }

    @NonNull
    @Override
    public PathHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new PathHolder(inflater.inflate(R.layout.path_item1, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PathHolder holder, int position) {

        Path path= pathList.get(position);
        holder.txtTicket.setText(path.getCode());
        holder.txtStation.setText(path.getStations().get(1).getTitle());
      //  holder.txtDestination.setText(path.getStations().get(2).getTitle());


    }

    @Override
    public int getItemCount() {
        return pathList.size();
    }

    public static class PathHolder extends RecyclerView.ViewHolder {

        TextView txtStation,txtDestination,txtPlanCount,txtTicket;

        public PathHolder(@NonNull View itemView) {
            super(itemView);
            txtStation = itemView.findViewById(R.id.txt_station);
            txtDestination = itemView.findViewById(R.id.txt_destination);
            txtPlanCount = itemView.findViewById(R.id.txt_plan_count);
            txtTicket = itemView.findViewById(R.id.txt_ticket);
        }
    }
}
