package com.example.team20;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.team20.databinding.ActivityJoinBinding;
import com.example.team20.domain.Member;
import com.example.team20.retrofit.MemberApi;
import com.example.team20.retrofit.RetrofitService;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class JoinActivity extends AppCompatActivity {
    private ActivityJoinBinding binding;
    private retrofit2.Retrofit retrofit;
    private Bitmap bitmap_basicProfile;
    private byte[] byteArray_basicProfile;
    RetrofitService retrofitService = new RetrofitService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityJoinBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        bitmap_basicProfile = BitmapFactory.decodeResource(getResources(), R.drawable.basic_profile);
        byteArray_basicProfile = bitmapToByteArray(bitmap_basicProfile);

        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        layoutParams.dimAmount = 0.8f;
        getWindow().setAttributes(layoutParams);

        MemberApi memberApi = retrofitService.getRetrofit().create(MemberApi.class);

        binding.btnJoin.setOnClickListener(view -> {
            String id = binding.etxtJoinId.getText().toString();
            String pw = binding.etxtJoinPw.getText().toString();
            String name = binding.etxtNickname.getText().toString();
            byte[] img = new byte[]{};
            String number = binding.etxtNumber.getText().toString();
            String mail = binding.etxtJoinEmail.getText().toString();

            Member member = new Member(id, name, img, pw, mail, number);

            memberApi.save(member)
                    .enqueue(new Callback<Member>() {
                                 @Override
                                 public void onResponse(Call<Member> call, Response<Member> response) {
                                     if(response.isSuccessful()){
                                         // Toast.makeText(JoinActivity.this, "회원 가입 성공", Toast.LENGTH_SHORT).show();
                                         Log.e("join 확인", "회원 가입 성공");
                                         showSuccessDialog();
                                         // finish();
                                     } else{
                                         Toast.makeText(JoinActivity.this, "중복 아이디", Toast.LENGTH_SHORT).show();
                                         Log.e("join 확인", "에러 : 중복 아이디");
                                         showFailDialog();
                                     }
                                 }

                                 @Override
                                 public void onFailure(Call<Member> call, Throwable t) {
                                     Log.e("join 확인", "에러 : " + t.getMessage());
                                 }
                             }
                    );
        });


        binding.btnJoinCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moveTaskToBack(true);
                finishAndRemoveTask();
                System.runFinalization();
            }
        });
    }

    void showFailDialog(){
        AlertDialog.Builder changeBuilder = new AlertDialog.Builder(JoinActivity.this)
                .setMessage("아이디 중복입니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog changeDlg = changeBuilder.create();
        changeDlg.show();
    }

    void showSuccessDialog(){
        AlertDialog.Builder changeBuilder = new AlertDialog.Builder(JoinActivity.this)
                .setMessage("사용할 수 있는 아이디입니다.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                });
        AlertDialog changeDlg = changeBuilder.create();
        changeDlg.show();
    }

    public byte[] bitmapToByteArray(Bitmap bitmap) { //img>bitmap>byte[] 함수 필요
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        return byteArray ;
    }
}