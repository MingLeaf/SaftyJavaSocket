package com.server.tools;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;
import java.util.Base64;
import java.util.Scanner;


//参考博客 https://blog.csdn.net/weixin_52553215/article/details/124215865
public class DESUtil {

    // 加密
    public static String encrypt(String content, String password) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(56, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return Base64.getEncoder().encodeToString(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 解密
    public static String decrypt(String content, String password) {
        try {
            byte[] decodeContent = Base64.getDecoder().decode(content);
            KeyGenerator kgen = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(password.getBytes());
            kgen.init(56, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "DES");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(decodeContent);
            return new String(result, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while(true) {
            System.out.println("请输入要进行的操作，1.进行加密，2.进行解密，3.退出");
            int op = sc.nextInt();
            String decryptResult = "";
            if(op == 1) {
                System.out.println("请输入要进行加密的内容:");
                String content = sc.next();  //加密内容
                System.out.println("请输入加密秘钥:");
                String password = sc.next();
                String encryptResult = encrypt(content, password);    //加密结果
                System.out.println("加密结果：" + encryptResult);
            }
            else if(op == 2) {
                System.out.println("请输入密文:");
                String result = sc.next();
                System.out.println("请输入秘钥:");
                try{
                    String password = sc.next();
                    decryptResult = decrypt(result, password);     //解密结果
                }catch (Exception e) {
                    System.out.println("解密失败，请检查秘钥！");
                }
                System.out.println("解密结果：" + decryptResult);
            }
            else if(op == 3) {
                System.out.println("已退出！");
                sc.close();
                break;
            }
            else {
                System.out.println("输入有误，请重新输入");
            }
        }


    }

}
