```
请勿滥用，本项目仅用于学习和测试！请勿滥用.
```

### 为啥要做这个

```
啊  我喜欢的抖音视频怎么被下架了！怎么失效了！可恶  手机内存又满了！
```

### 简介

```
一个视频下载汇聚类功能  
将视频下载地址推送给 应用目前已经支持的下载器 目前支持http 及外置Aria2 
并建立视频资源缓存 后台可进行管理查询查看等 
后期预计增加视频墙功能和APP端播放视频功能
```

### 技术框架

```
spring boot 2.7.10
spring boot jpa
sqlite
htmlunit等
```

## docker hub

[Docker](https://hub.docker.com/r/qingfeng2336/spirit)

### github

[GitHub - Confession1995/spirit: spirit](https://github.com/Confession1995/spirit)

### 功能点

- [x] 配置下载器

- [x] HTTP下载

- [x] 推送Aria2

- [x] 多用户管理

- [x] 视频资源缓存查看

- [x] 视频墙

- [ ] 视频解析

- [x]           抖音

- [x]           哔哩

- [ ] APP或小程序播放视频

- [ ] 其他功能
### 目前已开始计划的功能
- [ ] 仅通过链接判断视频来源
- [ ] 增加个人收藏一键下载功能    后台模块
- [ ] 批量提交视频链接     
- [ ] APP或小程序播放已缓存的视频列表
- [ ] 哔哩平台新增大会员标记提升缓存画质问题
- [ ] 后台新增视频墙功能
- [ ] 强化视频解析成功状态
- [ ] 新增哔哩扫码获取Cookie功能
### 预计目标实现及支持平台

```
    支持平台AMD64及ARM
    可解析平台
        抖音
        B站
        待总结
```

### 部署

##### 手动部署

```
待后续完善  可在任意存在java1.8以上环境部署
```

##### docker部署

###### docker cli

```
docker run --name spirit -d -p 28081:28081 -v /home/spirit/log:/app/log -v /home/spirit/video:/app/resources/video -v /home/spirit/cover:/app/resources/cover -v /home/spirit/db:/app/db -v /home/spirit/tmp:/tmp qingfeng2336/spirit:latest
```

###### 手动方式

    

```
docker 部署下 容器端口 为20801
/app/resources/video       请注意此处  挂载目录必须与系统配置用推送给Aria2的下载目录一致   
                           如果不使用Aria2 只使用内置http 下载器  就正常挂载此目录
/app/resources/cover
/app/db
/app/log
/tmp
```

### 后台默认帐号密码

```
后台地址是 http://ip:port/admin/login
默认帐号为admin 默认密码为123456
```

### 手机配套端

```
    安卓用户可以有两种选择 一个是我打包好的apk 一个是自己发布一个小程序
    apk
      https://github.com/Confession1995/spirit/blob/main/app/release/apk/apk.apk
    
    ios用户 仅有一种选择是自己发布一个小程序
    
    小程序基于uniapp开发  直接发布一个体验版本的小程序即可  不要推正式版
    并打开小程序开发模式  跳过域名校验  即可使用
```

###### 小程序界面或APP界面

服务器地址 服务器端口 服务器token 仅需要填写一次  填写后点击保存服务器

小程序或者APP监听剪贴板 会自动复制到第一个大方框里

点击推送 将分享链接推送给后台 将进入排队 进行处理  设置了线程1 所以会排队

<img src="https://s2.loli.net/2023/04/19/GlmrVTWEe8AyYR2.jpg" title="" alt="微信图片_20230419093629.jpg" width="251">

### 意见反馈

### 更新方向

```
暂无
```

## 参考资料

https://github.com/SocialSisterYi/bilibili-API-collect

https://toscode.gitee.com/zong_zh/parsing-tiktok-video
