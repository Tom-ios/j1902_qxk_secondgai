package com.qf.j1902.pojo;

import lombok.Data;

/**
 * Created by Qxk on 2019/5/29.
 */
@Data
public class ShIMing {
    private int id;
    private String truename;
    private String idcard;
    private String phonenum;
    private String uemail;
    private String stype;
   private String Auditstate;
   private String img;
    public ShIMing() {
    }

    public ShIMing(String uemail) {
        this.uemail = uemail;
    }

    public ShIMing(String truename, String idcard, String phonenum, String stype) {
        this.truename = truename;
        this.idcard = idcard;
        this.phonenum = phonenum;
        this.stype = stype;
    }

    public ShIMing(String truename, String idcard, String phonenum, String uemail, String stype) {
        this.truename = truename;
        this.idcard = idcard;
        this.phonenum = phonenum;
        this.uemail = uemail;
        this.stype = stype;
    }
}
