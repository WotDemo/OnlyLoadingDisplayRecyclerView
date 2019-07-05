package ysn.com.onlyloadingdisplayrecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

import ysn.com.onlyloadingdisplayrecyclerview.adapter.StockAdapter;
import ysn.com.onlyloadingdisplayrecyclerview.bean.Stock;
import ysn.com.onlyloadingdisplayrecyclerview.divider.DividerItemDecoration;
import ysn.com.onlyloadingdisplayrecyclerview.utils.NumberUtils;

public class MainActivity extends AppCompatActivity {

    private static final int TOTAL = 300;

    private StockAdapter myAdapter;
    private LinearLayoutManager linearLayoutManager;
    AtomicBoolean isIdLe = new AtomicBoolean(true);

    Handler handler = new Handler(msg -> {
        if (msg.what == 1) {
            if (isIdLe.get()) {
                if (myAdapter != null) {
                    refresh();
                }
            }
        }
        return false;
    });

    private void refresh() {
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        int lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        myAdapter.refresh(getData(lastVisibleItemPosition - firstVisibleItemPosition),
                firstVisibleItemPosition, lastVisibleItemPosition, TOTAL);
    }

    Thread thread = new Thread() {
        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = handler.obtainMessage();
                msg.what = 1;
                msg.sendToTarget();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.main_activity_recycler_view);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        myAdapter = new StockAdapter(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this));
        recyclerView.setAdapter(myAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    Log.d("test", "停止滑动");
                    isIdLe.set(true);
                    refresh();
                } else {
                    isIdLe.set(false);
                }
            }
        });
//        myAdapter.setNewDatas(getData(), TOTAL);
    }

    public ArrayList<Stock> getData() {
        return getData(10);
    }

    public ArrayList<Stock> getData(int size) {
        ArrayList<Stock> data = new ArrayList<>();
        for (int i = 0; i <= size; i++) {
            data.add(new Stock("股票" + i, NumberUtils.nextDouble(1, 10), NumberUtils.nextDouble(0, 100)));
        }
        return data;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (thread != null) {
            thread.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (thread != null) {
            thread.stop();
        }
    }
}
