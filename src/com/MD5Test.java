package com;
import com.server.tools.MD5util;


public class MD5Test {

    public static void main(String[] args) {
        MD5util md5util = new MD5util();
        System.out.println(md5util.getMD5Str("666666"));
    }
}
