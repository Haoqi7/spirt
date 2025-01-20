package com.flower.spirit.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

public class HttpUtil {
	
	
    /**
     * json 字符串
     * @param url
     * @param param
     * @return
     */
    public static String getSerchPersion(String url,String param){
        HttpClient httpClient = new HttpClient();
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        GetMethod getMethod = new GetMethod(url);
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        getMethod.getParams().setParameter("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Mobile Safari/537.36");
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错: "+ getMethod.getStatusLine());
            }
            byte[] responseBody = getMethod.getResponseBody();
            response = new String(responseBody, param);
        } catch (HttpException e) {
            System.out.println("请检查输入的URL!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("发生网络异常!");
            e.printStackTrace();
        } finally {
         /* 6 .释放连接 */
            getMethod.releaseConnection();
        }
        return response;
    }
    
    public static String httpGetBili(String url,String param,String cookie){
        HttpClient httpClient = new HttpClient();
    
        httpClient.getParams().setCookiePolicy(CookiePolicy.BROWSER_COMPATIBILITY);
        httpClient.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
        GetMethod getMethod = new GetMethod(url);
     
        getMethod.getParams().setParameter(HttpMethodParams.SO_TIMEOUT, 5000);
        getMethod.getParams().setParameter("user-agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Mobile Safari/537.36");
        getMethod.addRequestHeader("user-agent","Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.0.0 Mobile Safari/537.36");
        if(null != cookie && !cookie.equals("")) {
        	   getMethod.addRequestHeader("cookie",cookie);
        }
     
        getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
        String response = "";
        try {
            int statusCode = httpClient.executeMethod(getMethod);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("请求出错: "+ getMethod.getStatusLine());
            }
            byte[] responseBody = getMethod.getResponseBody();
            response = new String(responseBody, param);
        } catch (HttpException e) {
            System.out.println("请检查输入的URL!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("发生网络异常!");
            e.printStackTrace();
        } finally {
         /* 6 .释放连接 */
            getMethod.releaseConnection();
        }
        return response;
    }

    /**
     * post请求
     * @param url
     * @param json
     * @return
     */
    @SuppressWarnings("deprecation")
	public static JSONObject doPost(String url,JSONObject json){
        DefaultHttpClient client = new DefaultHttpClient();
        HttpPost post = new HttpPost(url);
        JSONObject response = null;
        try {
            StringEntity s = new StringEntity(json.toString());
            s.setContentEncoding("UTF-8");
            s.setContentType("application/json;charset=UTF-8");//发送json数据需要设置contentType
            post.setEntity(s);
            HttpResponse res = client.execute(post);
            if(res.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                String result = EntityUtils.toString(res.getEntity());// 返回json格式：
                result = new String(result.getBytes("ISO-8859-1"), "utf-8");
                response = JSONObject.parseObject(result);
            }
        } catch (Exception e) {
        	client.close();
        }
        return response;
    }

	public static void  downBiliFromUrl(String urlStr,String fileName,String savePath) throws Exception{
        try {
        	 URL url = new URL(urlStr); 
             HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
             conn.setConnectTimeout(5*1000);
             conn.setRequestProperty("User-Agent", "Mozilla/5.0 BiliDroid/7.25.0 (bbcallen@gmail.com)");
             conn.setRequestProperty("referer", "https://www.bilibili.com");
             InputStream input = conn.getInputStream();  
             byte[] getData = readInputStream(input);    
             File saveDir = new File(savePath);
             if(!saveDir.exists()){
            	  FileUtils.createDirectory(savePath);
             }
             File file = new File(saveDir+File.separator+fileName);    
             FileOutputStream output = new FileOutputStream(file);     
             output.write(getData); 
             if(output!=null){
             	output.close();  
             }
             if(input!=null){
                 input.close();
             }
		} catch (Exception e) {
			throw e;
		}
    }
    
	public static void  downLoadFromUrl(String urlStr,String fileName,String savePath){
        try {
        	 URL url = new URL(urlStr); 
             HttpURLConnection conn = (HttpURLConnection)url.openConnection();  
             conn.setConnectTimeout(5*1000);
             conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 10; SM-G981B) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.162 Mobile Safari/537.36");
             InputStream input = conn.getInputStream();  
             byte[] getData = readInputStream(input);    
             File saveDir = new File(savePath);
             if(!saveDir.exists()){
            	  FileUtils.createDirectory(savePath);
             }
             File file = new File(saveDir+File.separator+fileName);    
             FileOutputStream output = new FileOutputStream(file);     
             output.write(getData); 
             if(output!=null){
             	output.close();  
             }
             if(input!=null){
                 input.close();
             }
		} catch (Exception e) {
			
		}
    }

    public static  byte[] readInputStream(InputStream inputStream) throws IOException {  
        byte[] buffer = new byte[10240];  
        int len = 0;  
        ByteArrayOutputStream bos = new ByteArrayOutputStream();  
        while((len = inputStream.read(buffer)) != -1) {  
            bos.write(buffer, 0, len);  
        }  
        bos.close();  
        return bos.toByteArray();  
    }
	
}
