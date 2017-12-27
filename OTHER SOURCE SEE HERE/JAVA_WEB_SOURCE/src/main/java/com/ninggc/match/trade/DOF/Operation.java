package com.ninggc.match.trade.DOF;

import com.ninggc.match.trade.factory.MySessionFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

/**
 * Created by ning on 2017/7/4.
 * 抽象画的描述了对数据库的增删改查操作
 * 通过DoInDo接口实现用户自定义操作
 */
public class Operation<T> implements IOperation<T> {

    Session session;
    Transaction transaction;
    public T t;
    private String beanName;

    public Operation(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public boolean insert(T t) {
        boolean flag = false;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            session.save(t);
            transaction.commit();
            return true;
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public boolean delete(int id) {
        boolean flag = false;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            String hql = "delete " + beanName + " where id = " + id;
            Query<T> query = session.createQuery(hql);
            query.executeUpdate();
            flag = true;
            transaction.commit();
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public boolean update(T t) {
        boolean flag = false;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            flag = true;
        } catch (Exception e) {
            flag = false;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return flag;
    }

    @Override
    public T selectById(int id) {
        T t = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            String hql = "FROM " + beanName + " WHERE id = " + id;
            Query<T> query = session.createQuery(hql);
            t = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            t = null;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return t;
    }

    public T selectBy(String key, DoInDO<String, T> doInDO) {
        T t = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            t = doInDO.dosomething(session, key);
            transaction.commit();
        } catch (Exception e) {
            t = null;
            System.out.println(e.getMessage());
            transaction.rollback();
        } finally {
            if (session != null && session.isConnected()) {
                session.close();
            }
        }
        return t;
    }

    @Override
    public List<T> selectAll() {
        List<T> list = null;
        try {
            session = MySessionFactory.getSession();
            transaction = session.beginTransaction();
            String hql = "FROM " + beanName;
            Query<T> query = session.createQuery(hql);
            list = query.list();
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            if(session  != null && session.isConnected()) {
                session.close();
            }
        }
        return list;
  }
}
