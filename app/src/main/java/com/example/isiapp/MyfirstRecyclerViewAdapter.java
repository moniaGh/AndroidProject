package com.example.isiapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.isiapp.firstFragment.OnListFragmentInteractionListener;

import java.util.ArrayList;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link CasesClass} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyfirstRecyclerViewAdapter extends RecyclerView.Adapter<MyfirstRecyclerViewAdapter.ViewHolder> {

    private List<CasesClass> mValues=new ArrayList<>();
    private final OnListFragmentInteractionListener mListener;
   private Context context;
    public MyfirstRecyclerViewAdapter(Context context,List<CasesClass> items, OnListFragmentInteractionListener listener) {
       Log.e("bbbbb", String.valueOf(items.size()));
       for(int i=0;i<items.size();i++) {
           mValues.add(new CasesClass(items.get(i).getTas_uid(), items.get(i).getPro_title(), items.get(i).getPro_uid()));
           Log.e("xxxxx",mValues.get(i).getPro_title());
           Log.e("nnnnnnnn1",mValues.get(i).getTas_uid());
       }
       Log.e("bbbbb1", String.valueOf(mValues.size()));

        mListener = listener;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_first, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
       //holder.mItem.setText(position);
       // $$


        Log.e("bbbbbbbbb3333",mValues.get(position).getPro_title());
        Log.e("bbbbbbbbbb2", String.valueOf(mValues.size()));
        holder.mIdView.setText(String.valueOf(position+1));
        holder.mContentView.setText(mValues.get(position).getPro_title().substring(0,mValues.get(position).getPro_title().indexOf("(")));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {

                    Log.e("onClick: clicked on: " ,"tirarara");

if(context!=null)
{
    Bundle args = new Bundle();
    args.putString("pro_uid", mValues.get(position).getPro_uid());
    args.putString( "tas_uid", mValues.get(position).getTas_uid());
    //set Fragmentclass Arguments
   // AppCompatActivity activity = (AppCompatActivity) v.getContext();
    form fragobj = new form();
    fragobj.setArguments(args);
    FragmentManager activity=((AppCompatActivity)context).getSupportFragmentManager();

    activity.beginTransaction().replace(R.id.fragment_holder, fragobj).addToBackStack(null).commit();

    // AppCompatActivity activity = (AppCompatActivity) v.getContext();
    //Fragment myFragment = new form();
    //activity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_holder, myFragment).addToBackStack(null).commit();
   // Intent intent = new Intent(context,form.class);
    //intent.putExtra("pro_uid", mValues.get(position).getPro_uid());
    //intent.putExtra("tas_uid", mValues.get(position).getTas_uid());
                   // context.startActivity(intent);
}

                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.onListFragmentInteraction(holder.mItem);

                }
            }
        });
    }

    @Override
    public int getItemCount() {


        return mValues.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public CasesClass mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.item_number);
            mContentView = (TextView) view.findViewById(R.id.content);

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
