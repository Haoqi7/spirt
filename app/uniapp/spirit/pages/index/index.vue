<template>
	<view class="content">
			<view class="content">
				<textarea class="url" placeholder="输入或粘贴分享" v-model="originaladdress"></textarea>
				<input class="uni-input" v-model="serveraddr" placeholder="服务器地址"/>
				<input class="uni-input" v-model="serverport" placeholder="服务器端口"/>
				<input class="uni-input" v-model="servertoken" placeholder="服务器token"/>
				
				<button @click="pushMessage">推送</button>
				<button @click="saveServer">保存服务器</button>
			</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				originaladdress:"",
				serveraddr:"",
				serverport:"",
				servertoken:""
			}
		},
		onLoad() {
			this.initServer();
		},
		onShow() {
			uni.getClipboardData({
				success: (res) => {
					this.originaladdress = res.data
				}
			})
		},
		methods: {
			pushMessage:function(){
				if(this.originaladdress != "" && this.serveraddr != "" && this.serverport != "" && this.servertoken != ""){
					var api =this.serveraddr+":"+this.serverport+"/api/processingVideos";
					var option ={
						token:this.servertoken,
						video:this.originaladdress
					}
					uni.showLoading({
						title:"正在获取数据"
					})
					uni.request({
						url: api,
						method: "POST",
						header: {
							'content-type': 'application/x-www-form-urlencoded' // 默认值
						},
						data:option,
						success(res) {
							if(res.data.resCode =="000001" && res.data.message != null){
								uni.hideLoading();
								uni.showToast({
									    title: res.data.message,
									    duration: 2000,
										icon: 'success'
									});
							}
							
						}
					})
					
				}
			},
			saveServer:function(){
				if(this.serveraddr != ""){
					uni.setStorageSync('serveraddr',this.serveraddr)
				}
				if(this.serverport != ""){
					uni.setStorageSync('serverport',this.serverport)
				}
				if(this.servertoken != ""){
					uni.setStorageSync('servertoken',this.servertoken)
				}
				uni.showToast({
					title: '保存成功',
					duration: 2000
				});
			},
			initServer:function(){
				var serveraddr = uni.getStorageSync('serveraddr')
				var serverport = uni.getStorageSync('serverport')
				var servertoken = uni.getStorageSync('servertoken')
				if(serveraddr != null){
					this.serveraddr =serveraddr;
				}
				if(serverport != null){
					this.serverport =serverport;
				}
				if(servertoken != null){
					this.servertoken =servertoken;
				}
			}
		}
	}
</script>

<style>
	.content{
		width: 100%;
		text-align: center;
	}
	.url{
		margin-top: 5%;
		border: 1px solid #c0b9b9;
		border-radius: 10px;
		margin-left: 12%
	}
	.uni-input{
		margin-top: 5%;
		border: 1px solid #c0b9b9;
		width: 80%;
		margin-left: 12%;
		border-radius: 10px;
	}
	button{
		margin-top: 5%;
		border: 1px solid #c0b9b9;
		width: 80%;
		margin-left: 12%;
		border-radius: 10px;
	}
</style>
