package com.flower.spirit.utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Aria2Util {
	
	private static Logger logger = LoggerFactory.getLogger(Aria2Util.class);
	
	public static Object sendMessage(String url,JSONObject post) {
		HttpPost httpPost = new HttpPost(url);
	    httpPost.setHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
	    httpPost.setEntity(new StringEntity(JSONObject.toJSONString(post), StandardCharsets.UTF_8));
	    CloseableHttpResponse response;
        try {
            response = HttpClients.createDefault().execute(httpPost);
        } catch (HttpHostConnectException e) {
        	logger.debug("Aria2 无法连接");
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        String result = null;
        try {
            result = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            if (statusCode == HttpStatus.SC_OK) {
                EntityUtils.consume(entity);
                return result;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("statusCode = " + statusCode);
        System.out.println("result = " + result);
        return null;
	}
	
	/**
	 * @param downlink
	 * @param downpath
	 * @param downclass
	 * @return
	 */
	public static JSONObject createparameter(String downlink,String downpath,String downclass,String token) {
		JSONObject obj =  new JSONObject();
		
		obj.put("id", RandomStringUtils.randomNumeric(16));
		obj.put("jsonrpc", "2.0");
		obj.put("method", "aria2.addUri");
		JSONArray params = new JSONArray();
		if(token != null) {
			params.add("token:"+token);
		}
		JSONArray downLinkArray = new JSONArray();
		downLinkArray.add(downlink);
		params.add(downLinkArray);
		JSONObject confog =  new JSONObject();
		confog.put("dir", downpath);
		confog.put("out", downclass);
		confog.put("referer", "*");
		params.add(confog);
		obj.put("params", params);
		return obj;
	}
	public static void main(String[] args) {
		JSONObject createparameter = Aria2Util.createparameter("https://pan.mdreamworld.cn/api/raw/?path=/环境安装包/AdobeAIRInstaller.exe", "D:\\aria2\\down\\xxxx", "AdobeAIRInstaller.exe","123456");
		Object sendMessage = Aria2Util.sendMessage("http://localhost:6800/jsonrpc", createparameter);
		System.out.println(sendMessage);
	}

}
