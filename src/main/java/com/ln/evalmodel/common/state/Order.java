package com.ln.evalmodel.common.state;

public enum Order {

    ASC("asc"), DESC("desc");

    private String des;

    Order(String des) {
        this.des = des;
    }

    public String getDes() {
        return des;
    }

    private void setDes(String des) {
        this.des = des;
    }
}
