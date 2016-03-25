package com.vitaliy.krasylovets.spyfall.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.vitaliy.krasylovets.spyfall.R;
import com.vitaliy.krasylovets.spyfall.resources.Player;

import java.util.Collections;
import java.util.List;

/**
 * Created by vitaliy on 2016-02-15.
 */
public class PlayerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Global Variables
    public static final int VIEW_TYPE_DEFAULT = 1;
    public static final int VIEW_TYPE_ADD_PLAYER = 2;

    // Instance Variables
    private List<Player> playerList = Collections.emptyList();
    private LayoutInflater inflater;
    private int viewType;

    //Listeners
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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case VIEW_TYPE_ADD_PLAYER:
                ((AddPlayerViewHolder) holder).setPlayer(this.playerList.get(position), position);
                break;
            case VIEW_TYPE_DEFAULT:
            default:
                ((ShowPlayerRoleViewHolder) holder).setPlayer(this.playerList.get(position));
                break;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        RecyclerView.ViewHolder viewHolder;

        switch (viewType) {
            case VIEW_TYPE_ADD_PLAYER:
                view = inflater.inflate(R.layout.player_add_row, parent, false);
                viewHolder = new AddPlayerViewHolder(view);
                break;
            case VIEW_TYPE_DEFAULT:
            default:
                view = inflater.inflate(R.layout.player_role_row, parent, false);
                viewHolder = new ShowPlayerRoleViewHolder(view);
                break;
        }

        return viewHolder;
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

    /**
     * Interface for Setting on Click Listener
     */
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    /**
     * Interface for setting on Item Focus Change
     */
    public interface OnItemFocusChangeListener {
        void onItemFocusChange (View view, boolean hasFocus, int position);
    }

    /**
     * This View holder is responsible for the AddPlayer Recycle view
     */
    public class AddPlayerViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener, View.OnFocusChangeListener {

        private EditText editText = null;
        private ImageButton imageButton = null;

        public AddPlayerViewHolder(View itemView) {
            super(itemView);
            this.editText = ((EditText)itemView.findViewById(R.id.player_name));
            this.editText.setOnFocusChangeListener(this);

            this.imageButton = ((ImageButton)itemView.findViewById(R.id.remove_player_btn));
            this.imageButton.setOnClickListener(this);
        }

        public void setPlayer(Player player, int position) {
            if (player.getName().isEmpty()) {
                String hint = inflater.getContext().getString(R.string.player)
                        + " " + (position + 1);
                this.editText.setHint(hint);
                this.editText.setText("");
            } else  {
                this.editText.setText(player.getName());
            }
        }

        public void setPlayer(Player player) {
            editText.setText(player.getName());
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

    /**
     * Responsible for handling the show Player based on the role
     */
    public class ShowPlayerRoleViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private View itemView;
        private TextView textView;

        public ShowPlayerRoleViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            this.itemView.setOnClickListener(this);

            this.textView = (TextView) itemView.findViewById(R.id.player_name);
        }

        public void setPlayer(Player player) {
            textView.setText(player.getName());
        }

        @Override
        public void onClick(View v) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
}
