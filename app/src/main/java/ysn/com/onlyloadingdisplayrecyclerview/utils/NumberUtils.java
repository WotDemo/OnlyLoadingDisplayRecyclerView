package ysn.com.onlyloadingdisplayrecyclerview.utils;

import java.util.Random;

/**
 * @Author yangsanning
 * @ClassName NumberUtils
 * @Description 一句话概括作用
 * @Date 2019/7/5
 * @History 2019/7/5 author: description:
 */
public class NumberUtils {

    /**
     * 生成max到min范围的浮点数
     */
    public static double nextDouble(final double min, final double max) {
        return min + ((max - min) * new Random().nextDouble());
    }

    /**
     * 保留小数点后两位小数
     *
     * @param rate xx.xxxxx
     * @return result
     */
    public static String formatRate(double rate) {
        return formatRate(String.valueOf(rate));
    }

    /**
     * 保留小数点后两位小数
     *
     * @param rateStr xx.xxxxx
     * @return result
     */
    public static String formatRate(String rateStr) {
        if (rateStr.contains(".")) {
            // 获取小数点的位置
            int num = 0;
            num = rateStr.indexOf(".");

            String dianAfter = rateStr.substring(0, num + 1);
            String afterData = rateStr.replace(dianAfter, "");

            return rateStr.substring(0, num) + "." + afterData.substring(0, 2);
        } else {
            if ("1".equals(rateStr)) {
                return "100";
            } else {
                return rateStr;
            }
        }
    }
}
