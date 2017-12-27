package com.ninggc.match.trade.DOF;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 */
public interface IOperation<T> {
    /**
     *
     * @return true|false
     */
    public boolean insert(T t);

    /**
     *
     * @return true|false
     */
    public boolean delete(int id);

    /**
     * 匹配相同id项进行更新数据
     * @return true|false
     */
    public boolean update(T t);

    /**
     *
     * @return T|null
     */
    public T selectById(int id);

    /**
     *
     * @param key 筛选条件
     * @param doInDO 自定义操作
     * @return
     */
    public T selectBy(String key, DoInDO<String, T> doInDO);

    /**
     *
     * @return List|null|length = 0
     */
    public List<T> selectAll();
}
