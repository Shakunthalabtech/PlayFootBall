package com.waggieetales.androidapp.playfootball.adapters;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.waggieetales.androidapp.playfootball.R;
import com.waggieetales.androidapp.playfootball.models.Game;

import java.util.List;


    public class ListAdapter extends BaseAdapter {

    Activity activity;
    public LayoutInflater inflater;
    List<Game> array;

    public ListAdapter(Activity activ, List<Game> textValues) {

        activity = activ;
        array = textValues;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return array.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return array.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    static class ViewHolderItem {
        TextView tvHomeTeamName,tvAwayTeamName,tvGoalsHomeTeam,tvGoalsAwayTeam,tvStatus,tvDate;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolderItem viewHolder;


        if (inflater == null) {
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.game_single_item, parent, false);
            viewHolder = new ViewHolderItem();
            viewHolder.tvHomeTeamName = (TextView) convertView.findViewById(R.id.homeResultTV);
            viewHolder.tvAwayTeamName = (TextView) convertView.findViewById(R.id.awayResultTv);
            viewHolder.tvGoalsHomeTeam = (TextView) convertView.findViewById(R.id.goalsHomeResultTv);
            viewHolder.tvGoalsAwayTeam = (TextView) convertView.findViewById(R.id.goalsAwayResultTv);
            viewHolder.tvStatus = (TextView) convertView.findViewById(R.id.statusResultTv);
            viewHolder.tvDate = (TextView) convertView.findViewById(R.id.date_timeResultTv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolderItem) convertView.getTag();
        }

        Game mGettersSetters = array.get(position);

        viewHolder.tvHomeTeamName.setText(mGettersSetters.getHomeTeamName() != null ? mGettersSetters.getHomeTeamName() : "" );
        viewHolder.tvAwayTeamName.setText(mGettersSetters.getAwayTeamName() != null ? mGettersSetters.getAwayTeamName() : "");
        viewHolder.tvGoalsHomeTeam.setText(mGettersSetters.getResult().getGoalsHomeTeam() != null ? mGettersSetters.getResult().getGoalsHomeTeam()  : "");
        viewHolder.tvGoalsAwayTeam.setText(mGettersSetters.getResult().getGoalsAwayTeam() != null ? mGettersSetters.getResult().getGoalsAwayTeam() : "");
        viewHolder.tvStatus.setText(mGettersSetters.getStatus() != null ? mGettersSetters.getStatus() : "");
        viewHolder.tvDate.setText(mGettersSetters.getDate() != null ? mGettersSetters.getDate() : "");
        Log.d("In AdapCLass Name", "" + array.get(position).getHomeTeamName() != null ? mGettersSetters.getHomeTeamName() : "");
        return convertView;
    }

}

