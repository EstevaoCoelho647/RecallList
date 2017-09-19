package com.recalllist.ui.main.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recalllist.R;
import com.recalllist.domain.entity.Recall;
import com.recalllist.ui.main.MainActivityPresenter;

import java.util.ArrayList;
import java.util.List;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Recall recall = recallList.get(position);

        holder.textViewDate.setText(recall.getDate());
        holder.textViewText.setText(recall.getText());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (activityPresenter != null) {
                    activityPresenter.onRecallClick(recall);
                }
            }
        });
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
        recall.setText("OMLAR");
        recall.setDate("21/10/2017 - 06:30");
        this.recallList.add(recall);
        notifyDataSetChanged();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.textViewText)
        TextView textViewText;
        @BindView(R.id.textViewDate)
        TextView textViewDate;
        @BindView(R.id.holder)
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
