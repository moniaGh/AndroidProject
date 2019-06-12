package com.example.isiapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Intent.getIntent;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link form.OnFragmentInteractionListener1} interface
 * to handle interaction events.
 * Use the {@link form#newInstance} factory method to
 * create an instance of this fragment.
 */
public class form extends Fragment {
    Context context=this.getContext();
    LinearLayout linearLayout ;

    private OnFragmentInteractionListener1 mListener;

    public form() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     * @return A new instance of fragment form.
     */
    // TODO: Rename and change types and number of parameters
    public static form newInstance(String param1, String param2) {
        form fragment = new form();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);
        final Context context = this.getContext();

       // final String pro_uid = intent.getStringExtra("pro_uid");
        //String tas_uid = intent.getStringExtra("tas_uid");
        final String pro_uid = getArguments().getString("pro_uid");
        String tas_uid = getArguments().getString("tas_uid");

        Log.e("xxxxxxxxxx",pro_uid);
        Log.e("xxxxxxxxxx1",tas_uid);

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        String myToken = null;

        myToken = pref.getString(myToken,null);
        Log.e("hhhhh",myToken);
        Call<List<stepOne>> call = service.getStepOne(pro_uid,tas_uid,"Bearer "+myToken);
//Execute the request asynchronously//

        final String finalMyToken = myToken;
        call.enqueue(new Callback<List<stepOne>>() {


            //Handle a successful response//
            String myStepUid = null;
            @Override

            public void onResponse(Call<List<stepOne>> call, Response<List<stepOne>> response) {
                Log.e("etat",response.message());
                Log.e("hhhhhh3",response.body().get(0).getStep_uid_obj());
                myStepUid =response.body().get(0).getStep_uid_obj();



                /********************************/
                //déuxieme appel
                Log.e("hhhhhhhhhhh6", myStepUid);
                Log.e("hhhhhhhhhhh6", pro_uid);
                Log.e("hhhhhhhhhhh6", finalMyToken);


                Call<dynaContent> call1 = service.getDynCon(pro_uid, myStepUid,"Bearer "+ finalMyToken);
//Execute the request asynchronously//

                call1.enqueue(new Callback<dynaContent>() {

                    @Override

//Handle a successful response//

                    public void onResponse(Call<dynaContent> call, Response<dynaContent> response) {

                        Log.e("hhhhhh20", String.valueOf(response.code()));
                        getMyForm(response.body().getDyn_content(),context);
                    }


                    @Override

//Handle execution failures//

                    public void onFailure(Call<dynaContent> call, Throwable throwable) {
                        Log.e("saker fomel","saker");
                        Log.e("3lech",throwable.getMessage()+throwable.getStackTrace());

//If the request fails, then display the following toast//

                    }
                });

            }


            @Override

//Handle execution failures//

            public void onFailure(Call<List<stepOne>> call, Throwable throwable) {
                Log.e("saker fomel","saker");
                Log.e("3lech",throwable.getMessage()+throwable.getStackTrace());

//If the request fails, then display the following toast//

            }
        });


        /********************************************************/
//Log.e("hhhhhhhhhhhhhhhhhhhh10",myStepUid);




    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

                 View view =inflater.inflate(R.layout.fragment_form, container, false);
                 linearLayout=view.findViewById(R.id.linear_layout);
        return view;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction1(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener1) {
            mListener = (OnFragmentInteractionListener1) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener1 {
        // TODO: Update argument type and name
        void onFragmentInteraction1(Uri uri);
    }
    void getMyForm(String content,Context context)
    {


        //String content = "{\"name\":\"attestation de presence\",\"description\":\"\",\"items\":[{\"type\":\"form\",\"variable\":\"\",\"var_uid\":\"\",\"dataType\":\"\",\"id\":\"1324527325cab2062174a91093626827\",\"name\":\"attestation de presence\",\"description\":\"\",\"mode\":\"edit\",\"script\":{\"type\":\"js\",\"code\":\"/*\\n$(\\\"#cancelCase\\\").find(\\\"button\\\").click(function(){\\n  $(\\\"#action\\\").setValue(\\\"CANCEL\\\");\\n});\\n*/\\n\\n$(\\\"#title0000000001\\\").find(\\\"span.textlabel\\\").css(\\\"align\\\", \\\"center\\\");    \\n\\n\\n$(\\\"#title0000000001\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n$(\\\"#nom_res\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n$(\\\"#CIN\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n$(\\\"#nom_etu\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n$(\\\"#niveau\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n$(\\\"#heure1\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n$(\\\"#heure2\\\").find(\\\"span.textlabel\\\").css(\\\"color\\\", \\\"#191970\\\");\\n\\n//boutton\\n$(\\\"#submit0000000001\\\").find(\\\"button\\\").css(\\\"backgroundColor\\\", \\\"#191970\\\");\\n$(\\\"#submit0000000001\\\").find(\\\"button\\\").css(\\\"color\\\",\\\"white\\\");\\n\\n//background image \\n//$(\\\"#1324527325cab2062174a91093626827\\\").css(\\\"background-image\\\", \\\"url(''));\\n\\n\\n$(\\\"#1324527325cab2062174a91093626827\\\").css(\\\"border\\\", \\\"1px solid #191970\\\");\\n\\n//$(\\\"#1324527325cab2062174a91093626827\\\").css(\\\"backgroundColor\\\", \\\"#cfcbbf\\\");\\n\"},\"language\":\"en\",\"externalLibs\":\"\",\"printable\":false,\"items\":[[{\"type\":\"image\",\"id\":\"image0000000001\",\"name\":\"image0000000001\",\"label\":\"\",\"hint\":\"\",\"src\":\"https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Institut_Sup%C3%A9rieur_d%27Informatique_%28logo%29.svg/1200px-Institut_Sup%C3%A9rieur_d%27Informatique_%28logo%29.svg.png\",\"shape\":\"thumbnail\",\"alternateText\":\"\",\"comment\":\"\",\"mode\":\"parent\",\"alt\":\"\",\"colSpan\":2},{\"colSpan\":3},{\"type\":\"title\",\"id\":\"title0000000001\",\"label\":\"Demande d'attestation de présence\",\"colSpan\":4},{\"colSpan\":3}],[{\"colSpan\":3},{\"type\":\"text\",\"variable\":\"nom_etu\",\"var_uid\":\"2333873575cab2146cfe2d0097423983\",\"dataType\":\"string\",\"protectedValue\":false,\"id\":\"nom_etu\",\"name\":\"nom_etu\",\"label\":\"Nom et prenom \",\"defaultValue\":\"\",\"placeholder\":\"Veuillez saisir votre nom\",\"hint\":\"\",\"required\":true,\"textTransform\":\"none\",\"validate\":\"\",\"validateMessage\":\"\",\"maxLength\":1000,\"formula\":\"\",\"mode\":\"parent\",\"operation\":\"\",\"dbConnection\":\"7253687695cb5f790552817099517831\",\"dbConnectionLabel\":\"[127.0.0.1:3306] mysql: ISI_BUSINESS_DATA\",\"sql\":\"select nom from etudiant \",\"var_name\":\"nom_etu\",\"colSpan\":6},{\"colSpan\":3}],[{\"colSpan\":3},{\"type\":\"text\",\"variable\":\"CIN\",\"var_uid\":\"3205438795cab212e487185001609401\",\"dataType\":\"string\",\"protectedValue\":false,\"id\":\"CIN\",\"name\":\"CIN\",\"label\":\"CIN : \",\"defaultValue\":\"\",\"placeholder\":\"Veuillez saisir votre cin\",\"hint\":\"\",\"required\":true,\"textTransform\":\"none\",\"validate\":\"\",\"validateMessage\":\"\",\"maxLength\":1000,\"formula\":\"\",\"mode\":\"parent\",\"operation\":\"\",\"dbConnection\":\"7253687695cb5f790552817099517831\",\"dbConnectionLabel\":\"[127.0.0.1:3306] mysql: ISI_BUSINESS_DATA\",\"sql\":\"select id from etudiant \",\"var_name\":\"CIN\",\"colSpan\":6},{\"colSpan\":3}],[{\"colSpan\":3},{\"type\":\"text\",\"variable\":\"niveau\",\"var_uid\":\"6076072655cab2168a12ef8074777778\",\"dataType\":\"string\",\"protectedValue\":false,\"id\":\"niveau\",\"name\":\"niveau\",\"label\":\"Niveau \",\"defaultValue\":\"\",\"placeholder\":\"exemple: L03FSI\",\"hint\":\"\",\"required\":true,\"textTransform\":\"none\",\"validate\":\"\",\"validateMessage\":\"\",\"maxLength\":1000,\"formula\":\"\",\"mode\":\"parent\",\"operation\":\"\",\"dbConnection\":\"7253687695cb5f790552817099517831\",\"dbConnectionLabel\":\"[127.0.0.1:3306] mysql: ISI_BUSINESS_DATA\",\"sql\":\"select niveau from etudiant \",\"var_name\":\"niveau\",\"colSpan\":6},{\"colSpan\":3}],[{\"colSpan\":3},{\"type\":\"text\",\"variable\":\"groupe\",\"var_uid\":\"5341805835cb5f731c6ba40036167410\",\"dataType\":\"string\",\"protectedValue\":false,\"id\":\"groupe\",\"name\":\"groupe\",\"label\":\"groupe\",\"defaultValue\":\"\",\"placeholder\":\"veuillez saisir le numéro de votre groupe\",\"hint\":\"\",\"required\":true,\"textTransform\":\"none\",\"validate\":\"\",\"validateMessage\":\"\",\"maxLength\":1000,\"formula\":\"\",\"mode\":\"parent\",\"operation\":\"\",\"dbConnection\":\"workflow\",\"dbConnectionLabel\":\"PM Database\",\"sql\":\"\",\"var_name\":\"groupe\",\"colSpan\":6},{\"colSpan\":3}],[{\"colSpan\":10},{\"type\":\"submit\",\"id\":\"submit0000000001\",\"name\":\"submit0000000001\",\"label\":\"ENVOYER DEMANDE\",\"colSpan\":2}]],\"variables\":[{\"var_uid\":\"2333873575cab2146cfe2d0097423983\",\"prj_uid\":\"5982309495c9e28c3283ea0061483252\",\"var_name\":\"nom_etu\",\"var_field_type\":\"string\",\"var_field_size\":10,\"var_label\":\"string\",\"var_dbconnection\":\"workflow\",\"var_dbconnection_label\":\"PM Database\",\"var_sql\":\"\",\"var_null\":0,\"var_default\":\"\",\"var_accepted_values\":\"[]\",\"inp_doc_uid\":\"\"},{\"var_uid\":\"3205438795cab212e487185001609401\",\"prj_uid\":\"5982309495c9e28c3283ea0061483252\",\"var_name\":\"CIN\",\"var_field_type\":\"string\",\"var_field_size\":10,\"var_label\":\"string\",\"var_dbconnection\":\"workflow\",\"var_dbconnection_label\":\"PM Database\",\"var_sql\":\"\",\"var_null\":0,\"var_default\":\"\",\"var_accepted_values\":[],\"inp_doc_uid\":\"\",\"var_uid_old\":\"5845255315cab0e680094a4032319536\",\"var_name_old\":\"CIN\",\"prj_uid_old\":\"4884442395ca4ecc063f3d7005627265\"},{\"var_uid\":\"6076072655cab2168a12ef8074777778\",\"prj_uid\":\"5982309495c9e28c3283ea0061483252\",\"var_name\":\"niveau\",\"var_field_type\":\"string\",\"var_field_size\":10,\"var_label\":\"string\",\"var_dbconnection\":\"workflow\",\"var_dbconnection_label\":\"PM Database\",\"var_sql\":\"\",\"var_null\":0,\"var_default\":\"\",\"var_accepted_values\":\"[]\",\"inp_doc_uid\":\"\"},{\"var_uid\":\"5341805835cb5f731c6ba40036167410\",\"prj_uid\":\"5982309495c9e28c3283ea0061483252\",\"var_name\":\"groupe\",\"var_field_type\":\"string\",\"var_field_size\":10,\"var_label\":\"string\",\"var_dbconnection\":\"workflow\",\"var_dbconnection_label\":\"PM Database\",\"var_sql\":\"\",\"var_null\":0,\"var_default\":\"\",\"var_accepted_values\":\"[]\",\"inp_doc_uid\":\"\"}]}]}";
        //Gson gson = new Gson().fromJson(content,JsonArray);
        //  JSON json = fromStringToJSON(content);
        String cont=content ;
        JSONObject jsonObj = null;
        try {
            jsonObj = new JSONObject(content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.e("bbbbbb", jsonObj.toString());
        JSONArray myArray = null;
        try {
            myArray = jsonObj.getJSONArray("items");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (myArray != null) {
            Log.e("bbbbbb1", myArray.toString());
        }

        JSONArray otherArray = null;


        for (int i = 0; i < myArray.length(); i++) {

            JSONObject two = null;
            try {
                two = myArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JSONArray three = null;
            try {
                three = two.getJSONArray("items");
                Log.e("bbbbbbbbbbbb7", three.toString());
            } catch (JSONException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < three.length(); j++) {

                JSONArray four = null;
                try {
                    four = three.getJSONArray(j);
                    Log.e("bbbbbbbbbbbb8", String.valueOf(four.toString()));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int k = 0; k < four.length(); k++) {
                    try {

                        Log.e("bbbbbbbb9", four.getJSONObject(k).toString());
                        String myInput = null;

                        Log.e("bbbbbbbb10", (String) four.getJSONObject(k).get("type"));
                        myInput = (String) four.getJSONObject(k).get("type");
                        if (myInput != null) {
                            switch (myInput) {

                                case "title":  TextView textView1 = new TextView(context);
                                    textView1.setText((String)four.getJSONObject(k).get("label"));
                                    linearLayout.addView(textView1);

                                case "text":
                                    TextView textView = new TextView(context);
                                    textView.setText((String)four.getJSONObject(k).get("label"));
                                    linearLayout.addView(textView);

                                    EditText editView = new EditText(context);
                                    editView.setHint((String) four.getJSONObject(k).get("placeholder"));
                                    if (editView.getParent() != null) {
                                        ((ViewGroup) editView.getParent()).removeView(editView); // <- fix
                                    }
                                    linearLayout.addView(editView);


                                    break;
                                case "file": break;
                                case  "textarea":
                                    TextView textView5=new TextView(context);
                                    EditText textView4=new EditText(context);
                                    textView4.setInputType(3);
                                    linearLayout.addView(textView4);


                                    break;
                                case "radio": TextView textView2= new TextView(context);
                                    textView2.setHint((String) four.getJSONObject(k).get("label"));
                                    linearLayout.addView(textView2);
                                    JSONArray five =four.getJSONObject(k).getJSONArray("options");

                                    for(int l=0;l<five.length();l++)
                                    {
                                        RadioButton btn =new RadioButton(context);
                                        TextView textView3= new TextView(context);
                                        textView3.setHint((String) five.getJSONObject(l).get("label"));
                                        linearLayout.addView(textView3);
                                        linearLayout.addView(btn);

                                    }

                                    break;
                                case "subtitle":break;
                                case "submit" : Button btn = new Button(context);
                                    btn.setText((String)four.getJSONObject(k).get("label"));
                                    linearLayout.addView(btn);
                                    break;

                                default:
                                    break;
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }

    }

}
