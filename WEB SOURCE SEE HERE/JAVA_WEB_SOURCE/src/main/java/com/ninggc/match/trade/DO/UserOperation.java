package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.Security;
import com.ninggc.match.trade.DAO.User;
import com.ninggc.match.trade.DOF.AbstractOperation;
import com.ninggc.match.trade.DOF.DoInDO;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class UserOperation extends AbstractOperation<User> {
    public UserOperation() {
        super("User");
    }

    /**
     * verify user by password
     * @param id
     * @param password
     * @return
     */
    public boolean verify(int id, String password) {
        User user = (User) operation.selectById(id);
        if (password.equals(user.getSecurityById().getPassword())) {
            return true;
        }
        return false;
    }

    public User selectByUnique(String unique) {
        return (User) operation.selectBy(unique, new DoInDO() {
            @Override
            public Object dosomething(Session session, Object o) throws Exception {
                String hql = "FROM User where unique = " + unique;
                Query<User> query = session.createQuery(hql);
                User user = query.uniqueResult();
                return user;
            }
        });
    }

    public boolean updatePassword(int id, String pwd) {
        boolean flag = false;
        User user = selectById(id);
        if (user == null) {
            return flag;
        }
        Security security = user.getSecurityById();
        if (security == null) {
            return flag;
        }
        security.setPassword(pwd);
        SecurityOperation so = new SecurityOperation();
        flag = so.update(security);
        return flag;
    }
}
