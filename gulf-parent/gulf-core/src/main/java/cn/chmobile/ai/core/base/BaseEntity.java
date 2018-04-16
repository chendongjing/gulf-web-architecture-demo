package cn.chmobile.ai.core.base;

/**
 * Entity基类
 * 使用此类规范Mybatis实体相应元素的id命名，所有Entity类需继承此类
 *
 * @author  229260827@qq.com
 *
 * @param <T>
 */
public class BaseEntity {

	private String id;
	private String createdBy;
	private String createdAt;
	private String updatedBy;
	private String updatedAt;

	
	/**
	 * 插入之前执行方法，需要手动调用
	 */
	public void preInsert(){

	}
	
	/**
	 * 更新之前执行方法，需要手动调用
	 */
	public void preUpdate(){

	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}
}
