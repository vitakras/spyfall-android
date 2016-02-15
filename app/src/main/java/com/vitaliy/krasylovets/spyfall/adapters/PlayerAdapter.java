package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.util.Collections;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    private List<Player> playerList = Collections.emptyList();
    private LayoutInflater inflater;

    public PlayerAdapter(Context context, List<Player> playerList) {
        this.playerList = playerList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.player_drawer_row, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PlayerViewHolder holder, int position) {
        holder.setPlayer(this.playerList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.playerList.size();
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder {

        private EditText editText;

        public PlayerViewHolder(View itemView) {
            super(itemView);
            editText = (EditText) itemView.findViewById(R.id.player_name);
        }

        public void setPlayer(Player player) {
            editText.setText(player.getName());
        }
    }
}
