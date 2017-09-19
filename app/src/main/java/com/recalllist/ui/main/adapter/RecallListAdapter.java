package com.recalllist.ui.main.adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.recalllist.R;
import com.recalllist.domain.entity.Recall;
import com.recalllist.ui.main.MainActivityPresenter;
import com.recalllist.util.CalendarUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by estevao on 18/09/17.
 */

public class RecallListAdapter extends RecyclerView.Adapter<RecallListAdapter.ViewHolder> {
    private List<Recall> recallList;
    private MainActivityPresenter activityPresenter;

    public RecallListAdapter(MainActivityPresenter activityPresenter) {
        this.activityPresenter = activityPresenter;
        recallList = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout
                        .item_recall,
                parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Recall recall = recallList.get(position);

        holder.textViewDate.setText(CalendarUtils.getStringOfActualDate(recall.getDate()));
        holder.textViewText.setText(recall.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityPresenter != null) {
                    activityPresenter.onRecallClick(recall);
                }
            }
        });
        holder.editText.setText(recall.getText());

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                holder.linearOptions.setVisibility(View.VISIBLE);
                holder.editText.setVisibility(View.VISIBLE);
                holder.textViewText.setVisibility(View.GONE);

                return true;
            }
        });

        holder.buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.linearOptions.setVisibility(View.GONE);
                holder.editText.setVisibility(View.GONE);
                holder.textViewText.setVisibility(View.VISIBLE);

                recall.setText(holder.editText.getText().toString());
                holder.textViewText.setText(holder.editText.getText());

                activityPresenter.updateRecall(recall);
            }
        });

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recallList.remove(recall);
                activityPresenter.deleteRecall(recall);
            }
        });

        if (recall.getId() == null) {
            holder.linearOptions.setVisibility(View.VISIBLE);
            holder.editText.setVisibility(View.VISIBLE);
            holder.textViewText.setVisibility(View.GONE);
        }

        holder.cardView.setCardBackgroundColor(Color.parseColor(recall.getColor()));
    }

    @Override
    public int getItemCount() {
        return recallList.size();
    }

    public void setRecallList(List<Recall> recallList) {
        if (recallList != null) {
            this.recallList = recallList;
            notifyDataSetChanged();
        }
    }

    public void addNewItem() {
        Recall recall = new Recall();
        recall.setDate(CalendarUtils.getActualTime());
        String[] allColors = activityPresenter.getContext().getResources().getStringArray(R.array.colors);
        Random ran = new Random();
        int x = ran.nextInt(4);
        recall.setColor(allColors[x]);
        this.recallList.add(0, recall);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewText)
        TextView textViewText;
        @BindView(R.id.textViewDate)
        TextView textViewDate;
        @BindView(R.id.holder)
        CardView cardView;
        @BindView(R.id.linearOptions)
        LinearLayout linearOptions;
        @BindView(R.id.imageViewSave)
        ImageView buttonSave;
        @BindView(R.id.imageViewDelete)
        ImageView buttonDelete;
        @BindView(R.id.editText)
        EditText editText;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
