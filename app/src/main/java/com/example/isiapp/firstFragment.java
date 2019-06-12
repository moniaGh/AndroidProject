package com.example.isiapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class firstFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
   private  List<CasesClass> mValues=new ArrayList<>();
   Context context=this.getContext();

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public firstFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static firstFragment newInstance(int columnCount) {
        firstFragment fragment = new firstFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first_list, container, false);
        final Context context = view.getContext();
        final RecyclerView recyclerView = (RecyclerView) view;
        // Set the adapter
        if (view instanceof RecyclerView) {

            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            } }
        GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String myToken = null;
        myToken = pref.getString(myToken,null);
        Log.e("token",myToken);
        Call<List<CasesClass>> call = service.getCases("Bearer "+myToken);

//Execute the request asynchronously//

        call.enqueue(new Callback<List<CasesClass>>() {



            //Handle a successful response//
            @Override
            public void onResponse(Call<List<CasesClass>> call, Response<List<CasesClass>> response) {
                Log.e("aaaaaaaaaa", String.valueOf(response.message()));

                for (int i = 0; i < response.body().size(); i++) {
                    Log.e("kkkkkkkkk", response.body().get(i).getPro_title());
                    mValues.add(new CasesClass(response.body().get(i).getTas_uid(),response.body().get(i).getPro_title(),response.body().get(i).getPro_uid()));
                }
                Log.e("yeeeeeess", mValues.get(0).getPro_title());
                Log.e("vvvvv", String.valueOf(mValues.size()));
                recyclerView.addItemDecoration(new SimpleDividerItemDecoration(
                        context.getApplicationContext()
                ));
                recyclerView.setAdapter(new MyfirstRecyclerViewAdapter(context,mValues, mListener));

            }

            @Override

//Handle execution failures//

            public void onFailure(Call<List<CasesClass>> call, Throwable throwable) {

//If the request fails, then display the following toast//
                Log.e("saker", "fomek");

            }
        });
        Log.e("vvvv2", String.valueOf(mValues.size()));
    recyclerView.setAdapter(new MyfirstRecyclerViewAdapter(context,mValues, mListener));

        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(CasesClass item);
    }
}
