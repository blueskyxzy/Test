package com.xzy.maven;
// created by xzy on 2019-07-27

import lombok.Data;

@Data
public class NCBillInvoiceRequest {

    // 订单号
    private String billcode;

    // 订单明细id  不传则默认查询该订单所有明细单据的开票状态
    private String billtype;

}
