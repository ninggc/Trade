package com.ninggc.match.trade.DOF;

import com.ninggc.match.trade.DAO.IBean;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by ning on 2017/7/3.
 */
public class AbstractOperation<T extends IBean> {
    public Operation operation;

    private String beanName;

    public AbstractOperation(String beanName) {
        this.beanName = beanName;
        operation = new Operation<T>(beanName);
    }

    /**
     *
     * @return true|false
     */
    public boolean insert(T t) {
        return operation.insert(t);
    }

    /**
     *
     * @return true|false
     */
    public boolean delete(int id) {
        return operation.delete(id);
    }

    /**
     * 匹配相同id项进行更新数据
     * @return true|false
     */
    public boolean update(T t) {
        return operation.update(t);
    };

    /**
     *
     * @return T|null
     */
    public T selectById(int id) {
        return (T) operation.selectById(id);
    }

    /**
     *
     * @return List|null
     */
    public List<T> selectAll() {
        return operation.selectAll();
    }
}
