package com.huadi.education.utils;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class BaiduAPI {
    /**
     * http://lbsyun.baidu.com/apiconsole/key
     * <百度开发者>用户申请注册的key，自v2开始参数修改为“ak”，之前版本参数为“key” 申请ak
     */
    final static String AK = "pbUayM7XyqXvUW4GoSTX3sBFRA2dr9bM";


    /**
     * 地理编码 URL
     */
    final static String ADDRESS_TO_LONGITUDEA_URL = "http://api.map.baidu.com/geocoding/v3/?output=json&location=showLocation";

    /**
     * 地理编码
     * @param address 详细的位置信息
     * @return
     */
    public Double[] AddressTolongitudea(String address) {
        if(StringUtils.isBlank(address)){
            return null;
        }

        Double[] point = new Double[2];
        try {
            address = URLEncoder.encode(address, "UTF-8");
            String url = ADDRESS_TO_LONGITUDEA_URL + "&ak=" + AK + "&address="+ address;
            System.out.println("请求url:" + url);
            HttpClient client = HttpClients.createDefault();        // 创建默认http连接
            HttpPost post = new HttpPost(url);                      // 创建一个post请求

            try {
                HttpResponse response = client.execute(post);       // 用http连接去执行get请求并且获得http响应
                HttpEntity entity = response.getEntity();           // 从response中取到响实体
                String html = EntityUtils.toString(entity);         // 把响应实体转成文本

                JSONObject obj = JSONObject.fromObject(html);       // String转json
                obj = JSONObject.fromObject(obj.get("result"));
                obj = JSONObject.fromObject(obj.get("location"));
                point[0] = Double.parseDouble(obj.get("lng").toString());
                point[1] = Double.parseDouble(obj.get("lat").toString());
            } catch (Exception e) {
                System.out.println("地理编码[异常]," + e.getMessage());
                return null;
            }
            return point;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        BaiduAPI baiduAPI = new BaiduAPI();
        Double[] point = baiduAPI.AddressTolongitudea("成都市锦江区一环路IFS中心2号楼");
        System.out.println(point);
        System.out.println(point[0]);
        System.out.println(point[1]);
    }
}

