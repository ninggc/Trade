package com.ninggc.trade.factory.constants;

/**
 * Created by Ning on 7/26/2017 0026.
 */

@SuppressWarnings("ALL")
public class Constant {


    //url
//    public static final String url = "http://192.168.43.62:8080/";
    public static final String url = "http://123.207.244.139:8080/trade/";
//    public static final String url = "http://192.168.1.125:8000/";

    public static final String url_usermage = url + "usermage/";
    public static final String url_commodity = url + "commodity/";

    //image url, user for test_image
    public static final String image1 = "https://cdn0.iconfinder.com/data/icons/text-messaging-2-2/64/hello_everyone_smile-128.png";
    public static final String image2 = "https://cdn1.iconfinder.com/data/icons/green-business/720/customer-care-128.png";
    public static final String image3 = "https://cdn2.iconfinder.com/data/icons/message-1-line/64/Chat_greeting_hello_message-128.png";


    public static final String QQ_APP_ID = "1106325208";
    public static final String QQ_APP_KEY = "DmwXgfjPfNXjMI3E";

    public static final boolean DEBUG = true;

    //Status code
    public static final short PASSWORD_INCORRECT = -13;
    public static final short NOT_EXIST = -12;
    public static final int FAILED = -1;
    public static final short SUCCESS = 1;


    //shortent code
    public static final short INSIGNIFICANCE = -11;
    public static final short MAIN = 10;
    public static final short LOGIN = 11;
    public static final short REGISTER = 12;
    public static final short RELEASE_COMMODITY = 13;
    public static final short RELEASE_DELEGATION = 14;

    //Kind code
    public static final short KIND_NONE = 50;
    public static final short KIND_ALL = 51;
    public static final short KIND_BOOK = 52;
    public static final short KIND_CLOTHES = 53;
}
