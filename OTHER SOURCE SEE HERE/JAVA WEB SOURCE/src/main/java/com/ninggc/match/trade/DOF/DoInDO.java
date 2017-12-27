package com.ninggc.match.trade.DOF;

import org.hibernate.Session;

/**
 * Created by ning on 2017/7/4.
 */

/**
 *
 * @param <T> Entity instance
 * @param <V> Return type
 */
public interface DoInDO<T, V> {
    /**
     *
     * @param session
     * @param t
     * @return
     * @throws Exception catch by IOperation
     */
    public V dosomething(Session session, T t) throws Exception;
}
