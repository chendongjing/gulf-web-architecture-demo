package cn.chmobile.ai.modules.sys.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import cn.chmobile.ai.common.entity.DataEntity;

@TableName("sys_help_info")
@SuppressWarnings("serial")
public class HelpInfo extends DataEntity<String>{
	//主键  记录Id
	@TableId(value = "id",type = IdType.UUID)
	private String id ;
	//帮助类型id
	@TableField(value = "vtype_id")
	private String vtypeId;
	//帮助类型名称
	@TableField(value = "vtype_name")
	private String vtypeName;
	//帮助信息标题
	@TableField(value = "info_title")
	private String infoTitle;
	//帮助信息内容
	@TableField( value = "info_detail")
	private String infoDetail;
	//有效状态（0：不可以     1：可用）
	private String status;
	/** 备注 */
	@TableField(value = "remarks")
	private String remarks;
	
	/**********getter setter***************/
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVtypeId() {
		return vtypeId;
	}
	public void setVtypeId(String vtypeId) {
		this.vtypeId = vtypeId;
	}
	public String getVtypeName() {
		return vtypeName;
	}
	public void setVtypeName(String vtypeName) {
		this.vtypeName = vtypeName;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public String getInfoDetail() {
		return infoDetail;
	}
	public void setInfoDetail(String infoDetail) {
		this.infoDetail = infoDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
