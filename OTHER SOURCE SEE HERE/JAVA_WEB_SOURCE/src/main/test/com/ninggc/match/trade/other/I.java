package com.ninggc.match.trade.other;

import static javafx.scene.input.KeyCode.T;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public interface I<T extends Object> {

    public default String ti() {
//        System.out.println(new T());
        return getClass().getSimpleName();
    }
}
