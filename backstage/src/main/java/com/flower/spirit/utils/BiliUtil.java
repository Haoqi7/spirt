package com.flower.spirit.utils;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class BiliUtil {
	
	/**
	 * @throws Exception 
	 * 
	 */
	public static  Map<String, String> findVideoStreaming(String url,String token,String filepath) throws Exception {
		String api ="https://api.bilibili.com/x/player/playurl";
		Map<String, String> videoDataInfo = BiliUtil.getVideoDataInfo(url);
		api=api+"?avid="+videoDataInfo.get("aid")+"&cid="+videoDataInfo.get("cid");
		if(null != token && !token.equals("")) {
			api =api+"&qn=80";
		}else {
			api =api+"&qn=64";
		}
		api =api+"&fnval=1&fnver=0&fourk=0";
		String httpGetBili = HttpUtil.httpGetBili(api, "UTF-8", token);
		JSONObject parseObject = JSONObject.parseObject(httpGetBili);
		System.out.println(httpGetBili);
		String video = parseObject.getJSONObject("data").getJSONArray("durl").getJSONObject(0).getString("url");
		HttpUtil.downBiliFromUrl(video, videoDataInfo.get("cid")+".mp4", filepath);
		videoDataInfo.put("video", filepath+"/"+videoDataInfo.get("cid")+".mp4");
		videoDataInfo.put("videoname", videoDataInfo.get("cid")+".mp4");
		return videoDataInfo;
	}
	
	
	public static Map<String, String> getVideoDataInfo(String url) {
		Map<String, String> res = new HashMap<String, String>();
		String parseEntry = BiliUtil.parseEntry(url);
		String api ="";
		if(parseEntry.contains("BV")) {
			api ="https://api.bilibili.com/x/web-interface/view?bvid="+parseEntry.substring(2, parseEntry.length());
		}
		if(parseEntry.contains("av")) {
			api="https://api.bilibili.com/x/web-interface/view?aid="+parseEntry.substring(2, parseEntry.length());
		}
		String serchPersion = HttpUtil.getSerchPersion(api, "UTF-8");
		JSONObject videoData = JSONObject.parseObject(serchPersion);
		System.out.println(serchPersion);
		String bvid = videoData.getJSONObject("data").getString("bvid");
		String aid = videoData.getJSONObject("data").getString("aid");
		String cid = videoData.getJSONObject("data").getString("cid");
		String title = videoData.getJSONObject("data").getString("title");
		String pic = videoData.getJSONObject("data").getString("pic");
		String desc = videoData.getJSONObject("data").getString("desc");
		res.put("title", title);
		res.put("bvid", bvid);
		res.put("aid", aid);
		res.put("cid", cid);
		res.put("pic", pic);
		res.put("desc", desc);
		return res;
	}
	
	
	public static String parseEntry(String url) {
		if(url.contains("/video/av") || url.contains("/video/BV") ) {
			return BiliUtil.findUrlAidOrBid(url);
		}else {
			 WebClient webClient = ThreadConfig.getWebClientNotJavaScript();
			 HtmlPage page = null;
			 try {
				  page = webClient.getPage(url);
				  webClient.waitForBackgroundJavaScript(300);
				  URL biliUrl = page.getUrl();
				  String realUrl = biliUrl.toString();
				  return BiliUtil.findUrlAidOrBid(realUrl);
			}catch (Exception e) {
				
			}finally {
				//如果后续观察内存占用问题比较大 考虑取消此处注释
				//webClient.getCurrentWindow().getJobManager().removeAllJobs();
				webClient.close();
				//System.gc();
				
			}
		}
		return "";
	}
	
	
	public static String findUrlAidOrBid(String url) {
		String replace = url.replaceAll("http://", "").replaceAll("https://", "").replace("www.bilibili.com/video/", "");
		int indexOf = replace.indexOf("/");
		String id = replace.substring(0, indexOf);
		return id;
	}

	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception {
		Map<String, String> findVideoStreaming = BiliUtil.findVideoStreaming("https://www.bilibili.com/video/BV1zs4y1w7Qy/?spm_id_from=autoNext&vd_source=4e28b639dd740fe29d40b7a7ce78150a","buvid3=A482E3B3-9600-C78A-83F9-820A593BD77D98106infoc; b_nut=1674643698; _uuid=A34D1EA8-8E3F-42AC-E823-31E87A106BFA196842infoc; rpdid=|(umYJYlYYY)0J'uY~RYRJ~|u; nostalgia_conf=-1; buvid_fp_plain=undefined; hit-new-style-dyn=0; hit-dyn-v2=1; i-wanna-go-back=-1; header_theme_version=CLOSE; CURRENT_BLACKGAP=0; buvid4=A7F1A7FD-D260-A5A3-C4CF-0978621B76B401106-023012518-I+fjjCdS2R4+iUreQE/8BA==; DedeUserID=3493262113376423; DedeUserID__ckMd5=d6afd5e2e0cb60b3; LIVE_BUVID=AUTO3516787998398287; b_ut=5; PVID=1; CURRENT_FNVAL=4048; CURRENT_PID=fed22140-d21a-11ed-8dbf-3f61c17c3c6f; FEED_LIVE_VERSION=V8; CURRENT_QUALITY=80; fingerprint=aaef0e9f1d452954ccb3f45ced603e16; buvid_fp=541890ad651676727c6770a2daf2a081; bp_video_offset_3493262113376423=785525518392885500; innersign=0; b_lsid=82F85E86_187949D9837; SESSDATA=b9852370,1697377571,aa6fa*41; bili_jct=057f32fb819f4bcb47d686c2772c026f; sid=6o43jv69; home_feed_column=4","D:\\flower\\uploadFile");
		System.out.println(findVideoStreaming);
	}
}
