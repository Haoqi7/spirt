package com.flower.spirit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.flower.spirit.common.DataEntity;


@Entity
@Table(name = "biz_video")
public class VideoDataEntity  extends DataEntity<VideoDataEntity> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7980669221676123703L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE,generator="biz_video_seq")
	@TableGenerator(name = "biz_video_seq", allocationSize = 1, table = "seq_common", pkColumnName = "seq_id", valueColumnName = "seq_count")
    private Integer id;
	
	public String originaladdress;
	
	private String videoname;
	
	private String videodesc;
	
	private String videoplatform;
	
	private String videocover;
	
	private String videounrealaddr;
	
	private String videoaddr;
	
	private Date createtime;

	
	
	
	
	public VideoDataEntity() {
		super();
	}

	public VideoDataEntity(String videoname, String videodesc, String videoplatform, String videocover,
			String videoaddr,String videounrealaddr,String originaladdress) {
		super();
		this.videoname = videoname;
		this.videodesc = videodesc;
		this.videoplatform = videoplatform;
		this.videocover = videocover;
		this.videoaddr = videoaddr;
		this.createtime = new Date();
		this.videounrealaddr =videounrealaddr;
		this.originaladdress  = originaladdress;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getVideoname() {
		return videoname;
	}

	public void setVideoname(String videoname) {
		this.videoname = videoname;
	}

	public String getVideoplatform() {
		return videoplatform;
	}

	public void setVideoplatform(String videoplatform) {
		this.videoplatform = videoplatform;
	}

	public String getVideocover() {
		return videocover;
	}

	public void setVideocover(String videocover) {
		this.videocover = videocover;
	}

	public String getVideoaddr() {
		return videoaddr;
	}

	public void setVideoaddr(String videoaddr) {
		this.videoaddr = videoaddr;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getVideodesc() {
		return videodesc;
	}

	public void setVideodesc(String videodesc) {
		this.videodesc = videodesc;
	}

	public String getVideounrealaddr() {
		return videounrealaddr;
	}

	public void setVideounrealaddr(String videounrealaddr) {
		this.videounrealaddr = videounrealaddr;
	}

	public String getOriginaladdress() {
		return originaladdress;
	}

	public void setOriginaladdress(String originaladdress) {
		this.originaladdress = originaladdress;
	}
	
	
	

}
