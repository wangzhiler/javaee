package com.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * Created by thinkpad on 2018/4/17.
 */
public class MyTools {
    public static String getNewString(String string) {
        try {
            string = URLEncoder.encode(string, "ISO-8859-1");
            string = URLDecoder.decode(string, "UTF-8");
            return string;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return string;
    }


}
