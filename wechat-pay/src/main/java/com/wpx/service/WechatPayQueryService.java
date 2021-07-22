package com.wpx.service;

import com.alibaba.fastjson.JSON;
import com.wpx.common.constant.StringConstants;
import com.wpx.common.exception.CustomizeException;
import com.wpx.common.exception.ExceptionMessage;
import com.wpx.constant.WechatUrl;
import com.wpx.model.transactionquery.TransactionQueryResult;
import com.wpx.util.HttpClientUtils;

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
        StringBuilder builder = new StringBuilder(WechatUrl.TRANSACTION_ID_QUERY_URL);
        String url = builder.append(transactionId)
                .append(StringConstants.QUESTION_MARK)
                .append("mchid=")
                .append(mchid)
                .toString();
        try {
            String result = HttpClientUtils.doGet(url);
            return JSON.parseObject(result, TransactionQueryResult.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new CustomizeException(ExceptionMessage.TRANSACTION_QUERY_ERROR);
        }
    }

}
