package com.ninggc.match.trade.DO;

import com.ninggc.match.trade.DAO.Security;
import com.ninggc.match.trade.DOF.AbstractOperation;
import com.ninggc.match.trade.DOF.DoInDO;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 * Created by Ning on 7/24/2017 0024.
 */
public class SecurityOperation extends AbstractOperation<Security> {
    public SecurityOperation() {
        super("Security");
    }

    public Security selectByTel(String tel) {
        return ((Security) operation.selectBy(tel, new DoInDO() {
            @Override
            public Object dosomething(Session session, Object o) throws Exception {
                String hql = "FROM Security s WHERE s.tel = " + tel;
                Query<Security> query = session.createQuery(hql);
                Security security = query.uniqueResult();
                return security;
            }
        }));
    }
}
