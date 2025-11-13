package com.example.unnamedapp;

import android.view.*;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.ViewHolder> {

    public interface OnDateClickListener {
        void onDateClick(String date);
    }

    private List<String> dates;
    private OnDateClickListener listener;

    public DateAdapter(List<String> dates, OnDateClickListener listener) {
        this.dates = dates;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateText;
        public ViewHolder(View v) {
            super(v);
            dateText = v.findViewById(R.id.dateText);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_date, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String date = dates.get(position);
        holder.dateText.setText(date);
        holder.dateText.setOnClickListener(v -> listener.onDateClick(date));
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }
}
