package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.BaseActivity;
import com.example.kotlindemo.R;
import com.example.kotlindemo.databinding.ActivityCreateDbBinding;

public class CreateDbActivity extends BaseActivity<ActivityCreateDbBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_create_db;
    }

    @Override
    public void initListener() {

        MyDataBaseHelper dbHelper = new MyDataBaseHelper(this, "BookStore.db", 2);

        binding.createDataBase.setOnClickListener(v -> {
            dbHelper.getWritableDatabase();
        });
    }
}