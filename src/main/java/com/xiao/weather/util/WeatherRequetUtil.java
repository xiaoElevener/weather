package com.xiao.weather.util;

import com.xiao.weather.config.XinZhiConfig;
import org.junit.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author xiao_elevener
 * @date 2018-03-19 23:29
 */
public class WeatherRequetUtil {

    /**
     * 签名失效时间
     */
    private static final Integer TTL = 60;

    /**
     * 加密算法
     */
    private static final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 编码方式
     */
    private static final String UTF_8 = "utf-8";

    /**
     * 语言
     */
    private static final String HanYu = "zh-Hans";

    public static String getRequestUrl(String apiUrl) {
        long current = System.currentTimeMillis();
        String param = "ts=" + current + "&ttl=" + TTL + "&uid=" + XinZhiConfig.uid;
        String signature;
        String sig = null;
        try {
            signature = getSignature(param, XinZhiConfig.key);
            sig = URLEncoder.encode(signature, UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String requestUrl = apiUrl + "?" + param + "&sig=" + sig;
        return requestUrl;
    }


    private static String getSignature(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    }


    private static String bytesToHex(byte[] data) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            result.append(Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
        }
        return result.toString();
    }


    @Test
    public void test() {
        String request = getRequestUrl(XinZhiConfig.weatherApi) + "&location=杭州";


        try {
            URL url = new URL(request);    // 把字符串转换为URL请求地址
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 打开连接
            connection.connect();// 连接会话
            // 获取输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {// 循环读取流
                sb.append(line);
            }
            br.close();// 关闭流
            connection.disconnect();// 断开连接
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("失败!");
        }
    }
}
