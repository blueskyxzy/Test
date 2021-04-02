package com.xzy.maven;
// created by xzy on 2019-07-27

import lombok.Data;

import java.util.List;

@Data
public class NCBillInvoiceResponse {

    // 0表示成功，其它为错误码
    private String result;

    /**
     * 失败数据列表
     */
    private List<NCFailInfo> fail;

    /**
     * 查询结果
     */
    private List<NCDataInfo> data;

}
