package com.xiao.weather.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sun.jndi.toolkit.url.Uri;
import com.xiao.weather.config.XinZhiConfig;
import com.xiao.weather.constant.Api;
import com.xiao.weather.vo.NowWeatherVO;
import com.xiao.weather.vo.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import static java.lang.System.out;

/**
 * @author xiao_elevener
 * @date 2018-03-19 23:29
 */
@Slf4j
public class WeatherRequetUtil<T> {

    private ClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
    private RestTemplate restTemplate = new RestTemplate(factory);

    /**
     * 签名失效时间
     */
    private final Integer TTL = 60;

    /**
     * 加密算法
     */
    private final String HMAC_SHA1 = "HmacSHA1";

    /**
     * 编码方式
     */
    private final String UTF_8 = "utf-8";

    /**
     * 语言
     */
    private final String HanYu = "zh-Hans";

    private  String getRequestUrl(String apiUrl) {
        long current = System.currentTimeMillis();
        String param = "ts=" + current + "&ttl=" + TTL + "&uid=" + XinZhiConfig.uid;
        String signature= null ;
        try {
            signature = getSignature(param, XinZhiConfig.key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String requestUrl = apiUrl + "?" + param + "&sig=" + signature;
        return requestUrl;
    }


    private  String getSignature(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    }

    public  ResultVO<T> request(Api api,Map<String, Object> map){
        String url = getRequestUrl(api.getUrl());
        StringBuilder stringBuilder = new StringBuilder(url);
        for(String key:map.keySet()){
            stringBuilder.append("&"+key+"="+ map.get(key).toString());
        }
        String resultUrl =stringBuilder.toString();
        log.info(this.getClass().getName()+" url="+resultUrl);
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resultUrl);
        URI uri = builder.build().encode().toUri();
        ResultVO<T> vo = restTemplate.getForObject(uri, ResultVO.class);
        return vo;
    }
}
