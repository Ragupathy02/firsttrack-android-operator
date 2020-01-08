package com.firstpage.user.Ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.firstpage.user.Common.CommonFunctions;
import com.firstpage.user.Common.SessionManager;
import com.firstpage.user.Common.SharedPreference;
import com.firstpage.user.Interfaces.APIClient;
import com.firstpage.user.Interfaces.ApiInterface;
import com.firstpage.user.Model.Error;
import com.firstpage.user.Model.Login;
import com.firstpage.user.Model.Loginresponse;
import com.firstpage.user.R;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginscreenActivity extends AppCompatActivity {
    public TextInputEditText et_mail;
    public TextInputEditText et_password;
    public AppCompatButton bt_signin;
    SessionManager session;
    String regEx = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]{1,3}";
    ProgressDialog progressDialog;
    SharedPreference sharedPreference;
    ProgressBar simpleProgressBar;
    View view;
    RelativeLayout relativeLayout;
    AppCompatTextView tvForgot;
    public android.support.v7.app.AlertDialog deleteDialog;
    String rolelist;
    String build;
    String str_role;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginscreen);
//        if(!isConnected(LoginscreenActivity.this)) buildDialog(LoginscreenActivity.this).show();
//        else {
//
//        }
        et_mail=(TextInputEditText) findViewById(R.id.et_email);
        progressDialog = new ProgressDialog(LoginscreenActivity.this);
        et_password=(TextInputEditText)findViewById(R.id.et_password);
        bt_signin=(AppCompatButton)findViewById(R.id.btn_signin);
        relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        tvForgot=(AppCompatTextView)findViewById(R.id.tv_forgot_pwd);
        ProgressBar simpleProgressBar = (ProgressBar) findViewById(R.id.simpleProgressBar);
        sharedPreference=new SharedPreference();
        session=new SessionManager(this);
        session.checkLogin();
//        session.checksupervisor();

        bt_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checksigin())
                {
                    login();
//

                }
            }
        });
    }
    public boolean checksigin()
    {
//        Matcher matcherObj = Pattern.compile(regEx).matcher(et_mail.getText().toString());
        Log.e("ragu", "checksigin: "+regEx );
        if (et_mail.getText().toString().length() == 0) {
            et_mail.requestFocus();
            et_mail.setError("Enter your Operator code");
            return false;
        }
//        else if (!matcherObj.matches()) {
//            et_mail.requestFocus();
//            et_mail.setError("Enter valid email id");
//            return false;
//        }
        else if (et_password.length() == 0) {
            et_password.requestFocus();
            et_password.setError("Enter your password");
            return false;
        }
//        else if(et_password.length()<=5)
//        {
//            et_password.requestFocus();
//            et_password.setError("Password is too small");
//            return false;
//        }
        return true;

    }
    public void login()
    {
        CommonFunctions.showSimpleProgressDialog(this,"Logging In",false);
         Login logins = new Login();
        logins.setOperator_code(et_mail.getText().toString());
        logins.setPassword(et_password.getText().toString());
        ApiInterface service = APIClient.getClient().create(ApiInterface.class);
        Call<Loginresponse> loginresponseCall=service.login(logins);
        loginresponseCall.enqueue(new Callback<Loginresponse>() {
            @Override
            public void onResponse(Call<Loginresponse> call, Response<Loginresponse> response) {
                Log.e("ragu", "onResponse: "+response );
                CommonFunctions.removeProgressDialog();

                if(response.isSuccessful())
                {
                    Loginresponse loginresponse = response.body();
//                    loginresponse.getData().getCompany_name()
                    Log.e("ragu", "onResponse: "+response.code() );

                    for(int i=0;i<response.body().getData().size();i++)
                    {
                        if(response.body().getData().get(i).getRole().size()>0)
                        {
                            Log.e("ragu", "onResponse:role size large " );
                            ArrayList<String> role=response.body().getData().get(i).getRole();
                             rolelist = role.toString();
                            Log.e("ragu", "onResponse:rolelist "+rolelist );
                            str_role = rolelist.replaceAll("\\[", "").replaceAll("\\]","");
                            Log.e("ragu", "onResponse:str "+str_role );
                            if(str_role.equals("OPERATOR")) {
                                sharedPreference.putString(getApplicationContext(), "image", response.body().getData().get(i).getImage());
                                sharedPreference.putString(getApplicationContext(), "name", response.body().getData().get(i).getOperator_name());
                                sharedPreference.putString(getApplicationContext(), "role", str_role);
                                sharedPreference.putString(getApplicationContext(), "emailid", response.body().getData().get(i).getOperator_email());
                                sharedPreference.putString(getApplicationContext(), "companyid", response.body().getData().get(i).getCompany_id().getCompany_name());
                                sharedPreference.putString(getApplicationContext(), "ocode", response.body().getData().get(i).getOperator_code());
                                sharedPreference.putString(getApplicationContext(), "id_user", response.body().getData().get(i).get_id());


                                session.createLoginSession(et_mail.getText().toString(),true);
                                startActivity(new Intent(getApplicationContext(), WorkboardActivity.class));
                            }
                            else if(str_role.equals("SUPERVISOR"))
                            {
                                Log.e("ragu", "onResponse:role type" );
                                sharedPreference.putString(getApplicationContext(), "image", response.body().getData().get(i).getImage());
                                sharedPreference.putString(getApplicationContext(), "name", response.body().getData().get(i).getOperator_name());
                                sharedPreference.putString(getApplicationContext(), "role", str_role);
                                sharedPreference.putString(getApplicationContext(), "emailid", response.body().getData().get(i).getOperator_email());
                                sharedPreference.putString(getApplicationContext(), "companyid", response.body().getData().get(i).getCompany_id().getCompany_name());
                                sharedPreference.putString(getApplicationContext(), "ocode", response.body().getData().get(i).getOperator_code());
                                sharedPreference.putString(getApplicationContext(), "id_user", response.body().getData().get(i).get_id());


                                session.createLoginSession(et_mail.getText().toString(),false);
                                startActivity(new Intent(getApplicationContext(), Workboardsupervisor_Activity.class));


                            }



//                            String roller=str_role.replace(',','|');
//                            Log.e("ragu", "onResponse:change "+roller );



//                            for(String obj:role)
//                            {
//                                Log.e("ragu", "onResponse:roller "+obj );
//                                StringBuilder stringBuilder = new StringBuilder();
//                                stringBuilder.append(obj);
//                                 build=stringBuilder.toString();
//                                Log.e("ragu", "onResponse:stringbuilder "+build );
//                            }

                        }

//                        sharedPreference.putString(getApplicationContext(),"image",response.body().getData().get(i).getImage());
//                        sharedPreference.putString(getApplicationContext(),"name",response.body().getData().get(i).getOperator_name());
//                        sharedPreference.putString(getApplicationContext(),"role",str_role);
//                        sharedPreference.putString(getApplicationContext(),"emailid",response.body().getData().get(i).getOperator_email());
//                        sharedPreference.putString(getApplicationContext(),"companyid",response.body().getData().get(i).getCompany_id().getCompany_name());
//                        sharedPreference.putString(getApplicationContext(),"ocode",response.body().getData().get(i).getOperator_code());
//                        sharedPreference.putString(getApplicationContext(),"id_user",response.body().getData().get(i).get_id());
//                        sharedPreference.putString(getApplicationContext(),"customer_name",response.body().getData().get(i).getCustomer_name());
                    }
//                    session.createLoginSession(et_mail.getText().toString());
//                    startActivity(new Intent(getApplicationContext(), WorkboardActivity.class));


                }
                else if(response.code() == 403)
                {
                    try {
                        Error posts = new Gson().fromJson(new JSONObject(response.errorBody().string()).toString(), Error.class);
                        Log.e("ragu", "onResponse:posts "+posts.getError_message());
//                        Toast.makeText(getApplicationContext(),"the response is:"+posts.getError_message(),Toast.LENGTH_LONG).show();
                        CommonFunctions.shortToast(getApplicationContext(),""+posts.getError_message());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ragu", "onResponse:Exception "+e.getMessage() );
                    }

                }
                else if(response.code() == 421)
                {
                    try {
                        Error posts = new Gson().fromJson(new JSONObject(response.errorBody().string()).toString(), Error.class);
                        Log.e("ragu", "onResponse:posts "+posts.getError_message());
//                        Toast.makeText(getApplicationContext(),"the response is:"+posts.getError_message(),Toast.LENGTH_LONG).show();
                        CommonFunctions.shortToast(getApplicationContext(),""+posts.getError_message());
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.e("ragu", "onResponse:Exception "+e.getMessage() );
                    };
                }
            }

            @Override
            public void onFailure(Call<Loginresponse> call, Throwable t) {
                Log.e("ragu", "onFailure:login "+t );
                CommonFunctions.removeProgressDialog();
                Snackbar snackbar = Snackbar.make(relativeLayout,"Check Your Network Connection!",Snackbar.LENGTH_LONG);
                View snackbarView = snackbar.getView();
                snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                snackbar.show();
//                CommonFunctions.shortToast(getApplicationContext(),"No Internet Connection");
//                Snackbar.make(getApplicationContext().getWindow().getDecorView().getRootView(), "No internet connnection:", Snackbar.LENGTH_LONG).show();
//                     Snackbar snackbar = Snackbar.make(ongoingrelative,"No internet connection",Snackbar.LENGTH_LONG);
//                     View snackbarView = snackbar.getView();
//                    snackbarView.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                    snackbar.show();

//                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
//                Toast toast = Toast.makeText(getApplicationContext(), "No Internet Connection", Toast.LENGTH_SHORT); // initiate the Toast with context, message and duration for the Toast
//                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
//                toast.show();
//
//
//
//                Snackbar.make(getWindow().getDecorView().getRootView(), "No internet conmnection", Snackbar.LENGTH_LONG).show();
//                  Snackbar snackbar = Snackbar.make(view, "Enter Your Message",Snackbar.LENGTH_SHORT);
//                  view = snackbar.getView();
//                  view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
//                  snackbar.show();
//                Snackbar.getView().setBackgrondColor(ContextCompat.getColor(getApplicationContext(), R.color.BLACK));

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }


//    public boolean isConnected(Context context) {
//
//        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo netinfo = cm.getActiveNetworkInfo();
//
//        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
//            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//
//            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting()))
//                return true;
//            else return false;
//        } else
//            return false;
//    }
//    public AlertDialog buildDialog(Context c) {
//        LayoutInflater factory = LayoutInflater.from(LoginscreenActivity.this);
//        final View deleteDialogView = factory.inflate(R.layout.cust_layout, null);
//        deleteDialog = new android.support.v7.app.AlertDialog.Builder(LoginscreenActivity.this).create();
//        deleteDialog.setView(deleteDialogView);
//        deleteDialogView.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
//            @Override
//            public void onClick(View v) {
//                finishAffinity();
//            }
//        });
//        return deleteDialog;
//    }

}
