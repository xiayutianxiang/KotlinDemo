package com.example;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;

import com.example.customview.PieEntry;
import com.example.customview.PieView;
import com.example.kotlindemo.R;

import java.util.ArrayList;

public class DrawPaintActivity extends AppCompatActivity {

    private PieView mPieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_paint);

        mPieView = findViewById(R.id.pieView);
        initPieView();
        initAddAndReduce();
    }

    private void initPieView() {
        mPieView.setColors(createColors());
        mPieView.setData(createData());
    }

    private ArrayList<PieEntry> createData() {
        ArrayList<PieEntry> pieLists = new ArrayList<>();
        pieLists.add(new PieEntry(20.00F, "服装"));
        pieLists.add(new PieEntry(20.00F, "数码产品"));
        pieLists.add(new PieEntry(20.00F, "保健品"));
        pieLists.add(new PieEntry(20.00F, "户外运动用品"));
        pieLists.add(new PieEntry(20.00F, "其他"));
        return pieLists;
    }

    private ArrayList<Integer> createColors() {
        ArrayList<Integer> colorLists = new ArrayList<>();
        colorLists.add(Color.parseColor("#EBBF03"));
        colorLists.add(Color.parseColor("#ff4d4d"));
        colorLists.add(Color.parseColor("#8d5ff5"));
        colorLists.add(Color.parseColor("#41D230"));
        colorLists.add(Color.parseColor("#4FAAFF"));
        return colorLists;
    }

    private void initAddAndReduce() {

    }
}