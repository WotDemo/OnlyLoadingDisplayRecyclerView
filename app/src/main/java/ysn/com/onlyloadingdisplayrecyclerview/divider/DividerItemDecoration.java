package ysn.com.onlyloadingdisplayrecyclerview.divider;

import android.content.Context;
import android.support.annotation.Nullable;

import ysn.com.onlyloadingdisplayrecyclerview.R;
import ysn.com.recyclerviewdivider.Divider;
import ysn.com.recyclerviewdivider.DividerBuilder;
import ysn.com.recyclerviewdivider.RecyclerViewDivider;

/**
 * @Author yangsanning
 * @ClassName DividerItemDecoration
 * @Description 一句话概括作用
 * @Date 2019/7/5
 * @History 2019/7/5 author: description:
 */
public class DividerItemDecoration extends RecyclerViewDivider {

    private int color;

    public DividerItemDecoration(Context context) {
        super(context);
        color = context.getColor(R.color.transparent);
    }

    @Nullable
    @Override
    public Divider getDivider(int itemPosition) {
        return new DividerBuilder()
                .setTopLine(true, color, 8, 0, 0)
                .setLeftLine(true, color, 10, 0, 0)
                .setRightLine(true, color, 10, 0, 0)
                .setBottomLine(true, color, 8, 0, 0)
                .create();
    }
}