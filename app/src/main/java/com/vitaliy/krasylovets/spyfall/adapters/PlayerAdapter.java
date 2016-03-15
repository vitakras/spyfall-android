package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.util.Collections;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.PlayerViewHolder> {

    // Global Variables
    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_ROLES = 2;

    // Instance Variables
    private List<Player> playerList = Collections.emptyList();
    private LayoutInflater inflater;
    private int viewType;
    private OnItemClickListener onItemClickListener;
    private OnItemFocusChangeListener onItemFocusChangeListener;

    public PlayerAdapter(Context context, List<Player> playerList, int viewType) {
        this.playerList = playerList;
        this.inflater = LayoutInflater.from(context);
        this.viewType = viewType;
    }

    public PlayerAdapter(Context context, List<Player> playerList) {
       this(context, playerList, VIEW_TYPE_DEFAULT);
    }

    @Override
    public PlayerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;

        if (viewType == VIEW_TYPE_ROLES) {
            view = inflater.inflate(R.layout.player_role_row, parent, false);
        } else {
            view = inflater.inflate(R.layout.player_drawer_row, parent, false);
        }

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

    @Override
    public int getItemViewType(int position) {
        return this.viewType;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setOnItemFocusChangeListener(OnItemFocusChangeListener onItemFocusChangeListener) {
        this.onItemFocusChangeListener = onItemFocusChangeListener;
    }

    public class PlayerViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnFocusChangeListener {

        private EditText editText;
        private Button button;

        public PlayerViewHolder(View itemView) {
            super(itemView);

            if (viewType == VIEW_TYPE_DEFAULT) {
                editText = (EditText) itemView.findViewById(R.id.player_name);
                editText.setOnFocusChangeListener(this);
            } else if (viewType == VIEW_TYPE_ROLES) {
                button = (Button) itemView.findViewById(R.id.player_name);
                button.setOnClickListener(this);
            }
        }

        public void setPlayer(Player player) {
            if (viewType == VIEW_TYPE_DEFAULT) {
                editText.setHint(player.getName());
            } else if (viewType == VIEW_TYPE_ROLES) {
                button.setText(player.getName());
            }
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if(onItemFocusChangeListener != null) {
                onItemFocusChangeListener.onItemFocusChange(v, hasFocus, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface OnItemFocusChangeListener {
        void onItemFocusChange (View view, boolean hasFocus, int position);
    }
}
