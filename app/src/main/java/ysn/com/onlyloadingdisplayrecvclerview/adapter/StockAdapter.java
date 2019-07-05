package ysn.com.onlyloadingdisplayrecvclerview.adapter;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import ysn.com.baserecyclerviewadapter.adapter.BaseRecyclerViewAdapter;
import ysn.com.baserecyclerviewadapter.holder.BaseViewHolder;
import ysn.com.onlyloadingdisplayrecvclerview.R;
import ysn.com.onlyloadingdisplayrecvclerview.bean.Stock;
import ysn.com.onlyloadingdisplayrecvclerview.utils.NumberUtils;

/**
 * @Author yangsanning
 * @ClassName StockAdapter
 * @Description 一句话概括作用
 * @Date 2019/7/4
 * @History 2019/7/4 author: description:
 */
public class StockAdapter extends BaseRecyclerViewAdapter<Stock> {

    private ArrayList<Stock> datas;
    private int total;
    private int startIndex = 0;
    private int endIndex = 20;

    public StockAdapter(Context context) {
        super(context);
    }

    @Override
    public List<Stock> getDatas() {
        return datas;
    }

    @Override
    public int getGroupCount() {
        return total;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public boolean hasHeader(int groupPosition) {
        return false;
    }

    @Override
    public boolean hasFooter(int groupPosition) {
        return false;
    }

    @Override
    public int getHeaderLayout(int viewType) {
        return 0;
    }

    @Override
    public int getFooterLayout(int viewType) {
        return 0;
    }

    @Override
    public int getChildLayout(int viewType) {
        return R.layout.item_stock;
    }

    @Override
    public void onBindHeaderViewHolder(BaseViewHolder holder, int groupPosition) {
    }

    @Override
    public void onBindFooterViewHolder(BaseViewHolder holder, int groupPosition) {
    }

    @Override
    public void onBindChildViewHolder(BaseViewHolder holder, int groupPosition, int childPosition) {
        if (groupPosition < startIndex || groupPosition > endIndex
                || groupPosition >= (datas.size() + startIndex)) {
            holder.setText(R.id.stock_item_name, "--");
            holder.setText(R.id.stock_item_price, "--");
            holder.setText(R.id.stock_item_percent, "--");
        } else {
            Stock stock = datas.get(groupPosition - startIndex);
            holder.setText(R.id.stock_item_name, stock.getName());
            holder.setText(R.id.stock_item_price, NumberUtils.formatRate(stock.getPrice()));
            holder.setText(R.id.stock_item_percent, NumberUtils.formatRate(stock.getPercent()));
        }
    }

    public void setNewDatas(ArrayList<Stock> datas, int total) {
        this.datas = datas;
        this.total = total;
        notifyDataChanged();
    }

    public void refresh(ArrayList<Stock> datas, int startIndex, int endIndex, int total) {
        this.datas = datas;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
        this.total = total;
        notifyDataChanged();
    }
}
