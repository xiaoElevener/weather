package com.xiao.weather.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiao.weather.common.vo.weather.NowWeatherVO;
import com.xiao.weather.config.XinZhiConfig;
import com.xiao.weather.common.vo.weather.XinZhiResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Map;

/**
 * @author xiao_elevener
 * @date 2018-03-19 23:29
 */
@Slf4j
@Component
public class WeatherRequestUtil {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private XinZhiConfig xinZhiConfig;

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


    /**
     * 加密url
     *
     * @param apiUrl
     * @return
     */
    private String getRequestUrl(String apiUrl) {
        long current = System.currentTimeMillis();
        String param = "ts=" + current + "&ttl=" + TTL + "&uid=" + xinZhiConfig.getUid();
        String signature = null;
        try {
            signature = getSignature(param, xinZhiConfig.getKey());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return apiUrl + "?" + param + "&sig=" + signature;
    }

    /**
     * 通过key访问
     *
     * @param apiUrl
     * @return
     */
    private String getRequestUrlUnionKey(String apiUrl) {
        return apiUrl + "?" + "key=" + xinZhiConfig.getKey();
    }


    private String getSignature(String data, String key) throws NoSuchAlgorithmException, InvalidKeyException {
        byte[] keyBytes = key.getBytes();
        SecretKeySpec signingKey = new SecretKeySpec(keyBytes, HMAC_SHA1);
        Mac mac = Mac.getInstance(HMAC_SHA1);
        mac.init(signingKey);
        return Base64.getEncoder().encodeToString(mac.doFinal(data.getBytes()));
    }


    public XinZhiResultVO<NowWeatherVO> request(String api, Map<String, Object> map) {
        String resultUrl = buildUrl(api, map);
        log.info(this.getClass().getName() + " url=" + resultUrl);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(resultUrl);
        URI uri = builder.build().encode(StandardCharsets.UTF_8).toUri();

        XinZhiResultVO<NowWeatherVO> xinZhiResultVO = null;
        String result;
        try {
            result = restTemplate.getForObject(uri, String.class);
            xinZhiResultVO = JSON.parseObject(result, new TypeReference<XinZhiResultVO<NowWeatherVO>>() {
            });
        } catch (HttpClientErrorException e) {
            log.error("请求天气api报错,e={}", e);
            e.printStackTrace();
        }
        return xinZhiResultVO;
    }

    /**
     * 构建url
     * @param api
     * @param map
     * @return
     */
    private String buildUrl(String api, Map<String, Object> map) {
        String url = getRequestUrlUnionKey(api);
        StringBuilder stringBuilder = new StringBuilder(url);
        for (String key : map.keySet()) {
            stringBuilder.append("&" + key + "=" + map.get(key).toString());
        }
        return stringBuilder.toString();
    }


}
