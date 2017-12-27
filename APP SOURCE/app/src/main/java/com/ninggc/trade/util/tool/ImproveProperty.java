package com.ninggc.trade.util.tool;

/**
 * Created by Ning on 12/27/2017 0027.
 * 标记接口，表示性能可以改进
 */

public @interface ImproveProperty {
    String value() default "";
}
