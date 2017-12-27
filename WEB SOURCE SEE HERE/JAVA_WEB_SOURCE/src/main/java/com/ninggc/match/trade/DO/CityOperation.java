package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.City;
import com.ninggc.match.trade.DOF.AbstractOperation;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class CityOperation extends AbstractOperation<City> {
    private final String bean_name = getClass().getSimpleName().contains("Operation")
            ? getClass().getSimpleName().replaceAll("Operation", "")
            : getClass().getSimpleName();
    public CityOperation() {
        super("City");
    }
}
