package com.ninggc.trade.util.tool;

import com.google.gson.Gson;

/**
 * @author Ning
 * Created by Ning on 12/22/2017 0022.
 * 统一管理gson
 * 可通过实现接口获取对gson的引用
 */

public interface IGson {
    Gson gson = new Gson();
}
