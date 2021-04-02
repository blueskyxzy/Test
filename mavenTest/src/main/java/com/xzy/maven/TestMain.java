package com.xzy.maven;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.xzy.maven.NCCustomerResponse;
import com.xzy.maven.NCDataResponse;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        NCBillInvoiceRequest ncBillInvoiceRequest = new NCBillInvoiceRequest();
        ncBillInvoiceRequest.setBillcode("YG2021012545056145600846");
        List<NCDataInfo> billInvoiceInfoNew = getBillInvoiceInfoNew(ncBillInvoiceRequest);
        for (NCDataInfo ncDataInfo:billInvoiceInfoNew){
            String invoice = ncDataInfo.getInvoice();
            String itemId = ncDataInfo.getVbillitem_exc();
            if (invoice != null && !invoice.isEmpty()) {
                // 更新申请开票信息
                if (itemId != null && itemId.contains("#")) {
                    String id = itemId.substring(itemId.indexOf("#") + 1);
                    System.out.println("---");
                }
            }
        }

        System.out.println("------");
    }

    public static String getNCEntCode(String name) {
        if (name == null) {
            return "";
        }
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("name", name);
        // POST请求
        String response = HttpUtil.post("", paramMap, 10000);
//        String response = HttpUtil.createGet(CUSTOMER_URL + "?name=" + name).timeout(5000).execute().body();
        if (response == null || response.length() <= 2) {
            return null;
        }
        NCDataResponse<NCCustomerResponse> ncDataResponse =
                JSON.parseObject(
                        response,
                        new TypeReference<NCDataResponse<NCCustomerResponse>>() {

                        }
                );
        if (ncDataResponse != null) {
            List<NCCustomerResponse> data = ncDataResponse.getData();
            if (data != null) {
                for (NCCustomerResponse ncCustomerResponse : data) {
                    if (name.equals(ncCustomerResponse.getName())) {
                        return ncCustomerResponse.getCode();
                    }
                }
            }
        }
        return "false";
    }

    /**
     * 订单是否开票查询接口
     *
     * @param
     * @return 返回订单明细发票信息
     */
    public static List<NCDataInfo> getBillInvoiceInfoNew(NCBillInvoiceRequest ncBillInvoiceRequest) {
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("billcode", ncBillInvoiceRequest.getBillcode());
        paramMap.put("billtype", ncBillInvoiceRequest.getBilltype());
        String result;
        result = HttpUtil.post("", JSON.toJSONString(paramMap), 100000);
        if (result == null || result.length() <= 2) {
            return null;
        }
        NCBillInvoiceResponse response = JSONObject.parseObject(result, NCBillInvoiceResponse.class);
        if (response != null) {
            List<NCDataInfo> ncDataInfos = response.getData();
            return ncDataInfos;
        } else {
            return null;
        }
    }

}