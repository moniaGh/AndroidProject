package com.example.isiapp;



import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.widget.Toast.*;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    Intent intent = null;
    Context context=null;
     SharedPreferences pref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        mEmailView =  findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        Intent intent = new Intent(this, home.class);
context=this;
        //Change the Scope as you need

        //URL of the request we are sending4
        // final RequestQueue requestQueue = Volley.newRequestQueue(this);

       // final String url = "http://process.isiforge.tn/isi/oauth2/token";

       // bt1.setOnClickListener(new View.OnClickListener() {
         //   @Override
        //    public void onClick(View v) {

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        /*
JsonObjectRequest takes in five paramaters
Request Type - This specifies the type of the request eg: GET,POST
URL - This String param specifies the Request URL
JSONObject - This parameter takes in the POST parameters.Sending this parameters
makes this a POST request
Listener -This parameter takes in a implementation of Response.Listener()
interface which is invoked if the request is successful
Listener -This parameter takes in a implementation of Error.Listener()
interface which is invoked if any error is encountered while processing
the request
*/
                ;


            /*   JSONObject postparams = new JSONObject();
     
  
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                url, postparams,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {
                            Toast.makeText(getApplicationContext(),response.getString("access_token"),Toast.LENGTH_LONG).show();
                            intent.putExtra("access_token",response.getString("access_token"));


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        startActivity(intent);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Failure Callback
                        Toast.makeText(getApplicationContext(),"not good :(",Toast.LENGTH_LONG).show();

                    }
                });
        requestQueue.add(jsonObjReq); */

// Adding the request to the queue along with a unique string tag

//retro

             /*   GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

                Call<List<CasesClass>> call = service.getCases();

//Execute the request asynchronously//

                call.enqueue(new Callback<List<CasesClass>>() {

                    @Override

//Handle a successful response//

                    public void onResponse(Call<List<CasesClass>> call, Response<List<CasesClass>> response) {
                        Toast.makeText(MainActivity.this, "good", Toast.LENGTH_SHORT).show();
                        Log.e("aaaaaaaaaa", response.body().toString());
                        for (int i = 0; i < response.body().size(); i++) {
                            Log.e("kkkkkkkkk", response.body().get(i).getPro_title());

                        }
                    }

                    @Override

//Handle execution failures//

                    public void onFailure(Call<List<CasesClass>> call, Throwable throwable) {

//If the request fails, then display the following toast//

                        Toast.makeText(MainActivity.this, "Unable to load users", Toast.LENGTH_SHORT).show();
                    }
                });*/


           // }
        //}
    }
    private void attemptLogin() {


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        final String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }


            GetData service = RetrofitClient.getRetrofitInstance().create(GetData.class);

            Call<AccessToken> call = service.getAccess("password","*","SJGZDWXOPLJZLBDQGACCAGAVWSHORHJK","6734914665b5258c7a2eb01077382585","etudiant@isi.utm.tn","pm2019");

//Execute the request asynchronously//
            showProgress(true);
context=this.getApplicationContext();
        final View finalFocusView = focusView;
        call.enqueue(new Callback<AccessToken>() {

                @Override

//Handle a successful response//

                public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
//                    makeText(MainActivity.this, "good", LENGTH_SHORT).show();
                    String myToken=null;
if(response.isSuccessful()) {
    Log.e("kkkkkkkkk", response.body().getAccess_token());
    intent = new Intent(context, home.class);
    SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
    SharedPreferences.Editor editor = pref.edit();
    Log.e("wwwwwww",email);
    editor.putString("myUser",mEmailView.getText().toString());
    editor.putString(myToken, response.body().getAccess_token());
    editor.commit();
    Log.e("xxxxxxxxx", pref.getString("myUser", ""));

    //  intent.putExtra("access_token",response.body().getAccess_token());


    showProgress(false);
    startActivity(intent);
}
                    showProgress(false);
                }


                @Override

//Handle execution failures//

                public void onFailure(Call<AccessToken> call, Throwable throwable) {

//If the request fails, then display the following toast//
                    showProgress(false);

                    makeText(MainActivity.this, "Verifier votre Connexion", LENGTH_SHORT).show();
                    finalFocusView.requestFocus();

                }
            });
        }



            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.




    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
}



