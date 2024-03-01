package com.example.demo;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;


import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

public class TestParam {
    private final static String KEY = "foundernewsedit";
    public static void main(String[] args) throws Exception {
        System.out.println(decrypt("fa5335d356758905abd241f58c5c6f5ca3775b70d3f502895b09aeabc2f117875131a1f27f9a1e7c23cbc1689cb6aec5de3270b5f574fe8a0fe72a787ee0c943dffe55f2db28cffdfa1d185fe9648c09a239754b01b287d400d141cc3462e2d9135fef753d5075c79fd554e7d8ec7ec4787666eb1280785096271e66e1eb25d6af7e88639ca1752b74fe56825bceb6666cf96b61b150c91f2301e72303afefe25e61ea4cdb8de9f6c42c6e1f10891c5e1619747bab291ee8238ae409bbe5d94966de5bdc97ce80adf39eba3f845cc7bc8f4cfbaee3bc33dc011093ce8903c59074f8998a9df8656b216b386206e3f658af7a24a85408666405e56b1fc0a9d3f472ac3e687c34defdffd6c512963b53067cbb2647a76c8e696c902434ad5a7c7e"));
        long timestamp=System.currentTimeMillis();
        String signature="";
        String appName = "e32091ce71066d48ee739c1376c59fb06eaef7b7f90bc6935030ca4489b1b2c3";
        String appId = "d4be6dbefad44f7a8c364d7a3e14c64f";
        String appSecret="32787be0b1ee68b7300b9db4e0d60120a36092729892cc067f2492ee4d209b6bf342f74817f1d1c8023199970111df2d";
        // String sourceStr = appName+appId+appSecret+timestamp;
        String sourceStr = "方正电子-测试系统d4be6dbefad44f7a8c364d7a3e14c64f0e3025e4bba043f0991a588d05226d8f";
        System.out.println("sourceStr:"+sourceStr);
        signature= MD5(sourceStr+timestamp);
        System.out.println("timestamp:"+timestamp+"  signature:"+signature);

    }
    /**
     * AES加密字符串
     *
     * @param content
     *            需要被加密的字符串
     * @param key
     *            加密需要的key
     * @return 密文
     */
    public static String encrypt(String content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");  // 创建AES的Key生产者
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(KEY.getBytes());
            kgen.init(128, random); // 利用key作为随机数初始化出  128位的key生产者,加密没关系，SecureRandom是生成安全随机数序列，key是种子，只要种子相同，序列就一样，所以解密只要有key就行
            SecretKey secretKey = kgen.generateKey(); //生成一个密钥
            byte[] encodeFormat = secretKey.getEncoded();  // 返回基本编码格式的密钥，如果此密钥不支持编码，则返回null
            SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, "AES"); // 转换为AES专用密钥
            Cipher cipher = Cipher.getInstance("AES");  // 创建密码器
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);  // 初始化为加密模式的密码器
            byte[] result = cipher.doFinal(byteContent);  // 加密
            return Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }
    /**
     * 解密AES加密过的字符串
     *
     * @param content
     *            AES加密过过的内容
     * @param key
     *            加密时的key
     * @return 明文
     */
    public static String decrypt(String content){
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            random.setSeed(KEY.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] encodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(encodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);  // 初始化为解密模式的密码器
            byte[] result;
            result = cipher.doFinal(Hex.decodeHex(content.toCharArray()));
            return new String(result,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (DecoderException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes("utf-8"));
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
            // System.out.println("MD5(" + sourceStr + ",32) = " + result);
            // System.out.println("MD5(" + sourceStr + ",16) = " + buf.toString().substring(8, 24));
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
        return result;
    }
}
