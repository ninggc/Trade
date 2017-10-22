package com.ninggc.trade;

import com.google.gson.Gson;
import com.ninggc.trade.DAO.Commodity;
import com.ninggc.trade.test.EncryptionUtil;

import org.junit.Test;

import static android.R.id.list;
import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        StringBuilder sb  = new StringBuilder("0123");
        System.out.println(sb.length());

        String s = sb.toString().substring(0, sb.length() - 1);
        System.out.println(sb.toString().substring(0, 1));
        System.out.println(s);

//        assertEquals(4, 2 + 2);


        System.out.println(EncryptionUtil.md5("123456"));
    }

    @Test
    public void initJson() throws Exception {
        Commodity c = new Commodity();
        c.setNote("算法导论，计算机必读书籍，因为要毕业了，所以便宜出售。");
        System.out.println(new Gson().toJson(c));
    }
}