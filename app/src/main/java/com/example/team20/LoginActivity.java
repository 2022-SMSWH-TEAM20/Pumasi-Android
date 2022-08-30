package com.example.team20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.LoginInfo;
import com.example.team20.databinding.ActivityLoginBinding;
import com.example.team20.domain.Member;
import com.example.team20.retrofit.MemberApi;
import com.example.team20.retrofit.RetrofitService;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private ActivityLoginBinding binding;
    private retrofit2.Retrofit retrofit;
    private String id;
    private String pw;
    LoginInfo loginInfo;
    Member loginMember;
    RetrofitService retrofitService = new RetrofitService();
    MemberApi memberApi = retrofitService.getRetrofit().create(MemberApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl("http://192.168.0.16:9000") //cmd 창에서 확인 가능 - ipv4 주소 :9000
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();

        MemberApi service = retrofit.create(MemberApi.class);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);




        binding.btnCheck.setOnClickListener(view -> {
            String id = binding.etxtId.getText().toString();
            String pw = binding.etxtPassword.getText().toString();


            memberApi.login(id, pw).
                    enqueue(new Callback<Member>() {
                        @Override
                        public void onResponse(Call<Member> call, Response<Member> response) {

                        }

                        @Override
                        public void onFailure(Call<Member> call, Throwable t) {

                        }
                    });

                Intent intent_main = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent_main);
                finish();


        });

        binding.txtJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, JoinActivity.class);
                startActivity(intent);
            }
        });

    }
    void showCheckDialog(){
        AlertDialog.Builder changeBuilder = new AlertDialog.Builder(LoginActivity.this)
                .setMessage("로그인 실패")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog changeDlg = changeBuilder.create();
        changeDlg.show();
    }
}