package com.example.imedadmin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.imedadmin.eBook.EbookActivity;
import com.example.imedadmin.faculty.UpdateFaculty;
import com.example.imedadmin.notice.DeleteNoticeActivity;
import com.example.imedadmin.notice.UploadNotice;
import com.example.imedadmin.users.UserList;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView uploadNotice,addEbook,faculty,deleteNotice,logout;
    CardView addGalleryImage,registerUser;

    private ActionBarDrawerToggle toggle;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private FirebaseAuth auth;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //action bar icon
        androidx.appcompat.app.ActionBar actionBar = getSupportActionBar();
        //set action bar background
        // actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionBar_bg));
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("Dashboard");

        sharedPreferences = this.getSharedPreferences("login",MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (sharedPreferences.getString("isLogin","false").equals("false")){
            openLogin();
        }


        uploadNotice = findViewById(R.id.addNotice);
        addGalleryImage=findViewById(R.id.addGalleryImage);
        addEbook=findViewById(R.id.addEbook);
        deleteNotice=findViewById(R.id.deleteNotice);
        faculty=findViewById(R.id.faculty);
        logout=findViewById(R.id.logout);
        registerUser=findViewById(R.id.registerUser);

        uploadNotice.setOnClickListener(this);
        addGalleryImage.setOnClickListener(this);
        addEbook.setOnClickListener(this);
        faculty.setOnClickListener(this);
        deleteNotice.setOnClickListener(this);
        registerUser.setOnClickListener(this);


        //Custom Image/logo for Action Bar
        actionBar.setDisplayShowCustomEnabled(true);
        LayoutInflater inflater =(LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.custom_image,null);
        actionBar.setCustomView(view);
        //Custom Image/logo for Action Bar End


    }

    private void openLogin() {
        startActivity(new Intent(MainActivity.this,LoginActivity.class));
        finish();
    }

//for option menu logout
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.logout:
                editor.putString("isLogin","false");
                editor.commit();
                openLogin();
                break;
            case R.id.deletePdf:
                startActivity(new Intent(this, EbookActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {

            case R.id.addNotice:

                intent = new Intent(MainActivity.this, UploadNotice.class);
                startActivity(intent);
                break;

            case R.id.addGalleryImage:
                intent=new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;

            case R.id.addEbook:
                intent=new Intent(MainActivity.this,UploadPdfActivity.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent=new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent=new Intent(MainActivity.this, DeleteNoticeActivity.class);
                startActivity(intent);
                break;
            case R.id.registerUser:
                startActivity(new Intent(this, UserList.class));
                break;

        }

    }

}