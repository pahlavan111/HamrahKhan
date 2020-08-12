package com.bp.hamrahkhan.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bp.hamrahkhan.R;
import com.bp.hamrahkhan.model.path.Path;

import java.util.List;

public class PathAdapter extends RecyclerView.Adapter<PathAdapter.PathHolder> {
    Context context;
    List<Path> pathList;
    int lastPosition=0;

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


        try {
            Path path= pathList.get(position);
            holder.txtTicket.setText(path.getCode());
//        int count = path.getInventoryCount();
//        Log.d("beh_c",String.valueOf(count));
            holder.txtPlanCount.setText(String.valueOf(path.getInventoryCount()));
            holder.txtStation.setText(path.getStations().get(0).getTitle());

            if (path.getStations().size()>=2){
                holder.txtDestination.setText(path.getStations().get(path.getStations().size()-1).getTitle());
            }
            holder.txtDestination.setText(path.getStations().get(1).getTitle());
        }catch (Exception e){
            Log.d("beh_err",e.toString());
        }

        if (position>lastPosition){
            Animation animation= AnimationUtils.loadAnimation(context,R.anim.slide_down);
            holder.itemView.startAnimation(animation);
        }else {
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.slide_up);
            holder.itemView.startAnimation(animation);
        }




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
