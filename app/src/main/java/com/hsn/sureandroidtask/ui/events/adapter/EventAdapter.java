package com.hsn.sureandroidtask.ui.events.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.network.WebApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.hsn.sureandroidtask.common.Utils.toUiDateFormat;


/**
 * Created by hassanshakeel on 2/15/18.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void viewLocation(EventDetails userContent);
    }

    private List<EventDetails> eventList = new ArrayList<>();
    private OnItemClickListener listener;

    public void update(List<EventDetails> userContents) {
        this.eventList = userContents;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_event_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(eventList.get(position));
        holder.viewLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.viewLocation(eventList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView name;
        public TextView city;
        public TextView date;
        public ImageView mainImageView;
        public TextView description;
        public View viewLocation;


        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.city = (TextView) rootView.findViewById(R.id.city);
            this.date = (TextView) rootView.findViewById(R.id.date);
            this.mainImageView = (ImageView) rootView.findViewById(R.id.mainImageView);
            this.description = (TextView) rootView.findViewById(R.id.description);
            this.viewLocation =  rootView.findViewById(R.id.viewLocation);
        }

        public void bind(EventDetails eventDetails) {
            name.setText(eventDetails.getEventTitle());

            if (Locale.getDefault().getLanguage().equals("ar"))
                city.setText(eventDetails.getCityArName());
            else
                city.setText(eventDetails.getCityEnName());

            date.setText(toUiDateFormat(eventDetails.getEventStartDate()));

            description.setText(eventDetails.getEventDetails());

            Glide.with(mainImageView.getContext())
                    .load(WebApi.BaseUrl+eventDetails.getImagePath())
                    .into(mainImageView);


        }
    }


}
