package com.example.senolb.project.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.senolb.project.GifsAdapter;
import com.example.senolb.project.GlobalData;
import com.example.senolb.project.R;
import com.example.senolb.project.gesturehelper.OnStartDragListener;
import com.example.senolb.project.gesturehelper.SimpleItemTouchHelperCallback;

import java.util.ArrayList;

public class LikesActivity extends Activity implements OnStartDragListener {
    private ArrayList<String> gifUrls=new ArrayList<>();
    private ItemTouchHelper helper;

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        this.overridePendingTransition(R.anim.pull_in_left, R.anim.push_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_likes);
        RecyclerView rvGifs = (RecyclerView) findViewById(R.id.rvGifs);

        ArrayList<String> pass = GlobalData.getList();

        for(int i= 0; i < pass.size();i++ ){
            gifUrls.add(pass.get(i));
        }

        final GifsAdapter gifAdapter = new GifsAdapter(getApplicationContext(), gifUrls,this);

        rvGifs.setHasFixedSize(true);
        rvGifs.setAdapter(gifAdapter);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(gifAdapter);
        helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rvGifs);

        rvGifs.setLayoutManager(new LinearLayoutManager(this));
        rvGifs.requestLayout();
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        helper.startDrag(viewHolder);
    }

    public void removeIndex(int i){
        gifUrls.remove(i);
    }
}