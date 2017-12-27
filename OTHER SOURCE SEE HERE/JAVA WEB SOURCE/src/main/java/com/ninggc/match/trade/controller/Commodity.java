package com.ninggc.match.trade.controller;

import com.ninggc.match.trade.DO.CommodityOperation;
import com.opensymphony.xwork2.ActionSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Commodity extends BaseController {
    private CommodityOperation co = new CommodityOperation();

    public String add() {
        try {
            String json = ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String selectAll() {
        try {
            String json = ready();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String requestList() {
        try {
            String json = ready();
            List<com.ninggc.match.trade.DAO.Commodity> list = new ArrayList<>();
            list = co.selectAll();
            response.getWriter().append(gson.toJson(list));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
