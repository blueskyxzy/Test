package com.xzy.maven;
// created by xzy on 2019-07-27

public class NCDataInfo {

    private String vbillcode_exc; // 订单号

    private String vbillitem_exc; // 订单明细id

    public String getVbillcode_exc() {
        return vbillcode_exc;
    }

    public void setVbillcode_exc(String vbillcode_exc) {
        this.vbillcode_exc = vbillcode_exc;
    }

    public String getVbillitem_exc() {
        return vbillitem_exc;
    }

    public void setVbillitem_exc(String vbillitem_exc) {
        this.vbillitem_exc = vbillitem_exc;
    }

    public String getInvoicendflag() {
        return invoicendflag;
    }

    public void setInvoicendflag(String invoicendflag) {
        this.invoicendflag = invoicendflag;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    private String invoicendflag; // 是否开票 Y表示是，N表示否

    private String invoice; // 发票号

}
