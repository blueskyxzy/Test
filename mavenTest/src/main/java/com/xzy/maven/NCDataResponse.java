package com.xzy.maven;// created by xzy on 2019-07-27

import java.util.List;

public class NCDataResponse<T> {

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    private List<T> data;

    private Integer total;

    private Integer result;
}
