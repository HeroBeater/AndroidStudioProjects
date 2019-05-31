package com.example.lab10application;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdapterContacts extends RecyclerView.Adapter<AdapterContacts.ContactViewHolder>{

    private List<Contact> contactList;
    private Context mContext;
    public AdapterContacts(List<Contact> contactList, Context mContext){
        this.contactList = contactList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contactlist_items, null);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactViewHolder holder, int position) {
        Contact contactVO = contactList.get(position);
        holder.contactName.setText(contactVO.getContactName());
        holder.contactPhone.setText(contactVO.getContactPhone());
    }

    @Override
    public int getItemCount() {
        return contactList.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{

        TextView contactName;
        TextView contactPhone;

        public ContactViewHolder(View itemView) {
            super(itemView);
            contactName = itemView.findViewById(R.id.contactNameText);
            contactPhone = itemView.findViewById(R.id.contactPhoneText);
        }
    }
}