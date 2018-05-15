package com.hsn.sureandroidtask.ui.supplier.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.hsn.sureandroidtask.R;
import com.hsn.sureandroidtask.model.EventDetails;
import com.hsn.sureandroidtask.model.SupplierData;
import com.hsn.sureandroidtask.network.WebApi;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.hsn.sureandroidtask.common.Utils.toUiDateFormat;


/**
 * Created by hassanshakeel on 2/15/18.
 */

public class SupplierAdapter extends RecyclerView.Adapter<SupplierAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onClick(SupplierData supplierData);
    }

    private List<SupplierData> supplierDataList = new ArrayList<>();
    private OnItemClickListener listener;

    public void update(List<SupplierData> supplierDataList) {
        this.supplierDataList = supplierDataList;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supplier_list, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.bind(supplierDataList.get(position));
        holder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClick(supplierDataList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return supplierDataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView name;
        public TextView contact;


        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.name = (TextView) rootView.findViewById(R.id.name);
            this.contact = (TextView) rootView.findViewById(R.id.contact);

        }

        public void bind(SupplierData supplierData) {
            name.setText(supplierData.getCompanyName());
            contact.setText(""+supplierData.getDescription());

        }
    }


}
