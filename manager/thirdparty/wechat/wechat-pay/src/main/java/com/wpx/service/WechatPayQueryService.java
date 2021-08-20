package com.wpx.service;

import com.wpx.util.JsonUtils;
import com.wpx.constant.StringConstants;
import com.wpx.exception.CommonException;
import com.wpx.constant.WechatPayUrl;
import com.wpx.exception.WechatPayExceptionMessage;
import com.wpx.model.transactionquery.TransactionQueryResult;
import com.wpx.okhttp.util.OkHttpClientUtils;

import java.io.IOException;

/**
 * @author 不会飞的小鹏
 * @Description: 微信支付订单查询服务类
 */
public class WechatPayQueryService {

    /**
     * 根据微信支付订单号查询订单信息
     *
     * @param transactionId  微信支付系统生成的订单号
     * @param mchid  直连商户的商户号，由微信支付生成并下发
     */
    public TransactionQueryResult queryTransactionByTransactionId(String transactionId, String mchid) {
        return queryTransaction(WechatPayUrl.TRANSACTION_ID_QUERY_URL, transactionId, mchid);
    }

    /**
     * 根据商家订单号查询订单信息
     *
     * @param outTradeNo  商户系统内部订单号，只能是数字、大小写字母_-*且在同一个商户号下唯一
     * @param mchid  直连商户的商户号，由微信支付生成并下发
     */
    public TransactionQueryResult queryTransactionByOutTradeNo(String outTradeNo, String mchid) {
        return queryTransaction(WechatPayUrl.OUT_TRADE_NO_QUERY_URL, outTradeNo, mchid);
    }

    /**
     * 查询订单信息
     *
     * @param url  请求url
     */
    public TransactionQueryResult queryTransaction(String url, String pathParam, String mchid) {
        try {
            String result = OkHttpClientUtils.doGet(url + pathParam + StringConstants.QUESTION_MARK + "mchid=" + mchid);
            return JsonUtils.deserializeObject(result, TransactionQueryResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CommonException(WechatPayExceptionMessage.TRANSACTION_QUERY_ERROR);
        }
    }

}
