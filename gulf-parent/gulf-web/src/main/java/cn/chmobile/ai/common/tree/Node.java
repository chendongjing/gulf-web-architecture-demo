package cn.chmobile.ai.common.tree;

import java.util.List;

public class Node {
	public Node() { }
	public String id;    //树的节点Id，区别于数据库中保存的数据Id。若要存储数据库数据的Id，添加新的Id属性；若想为节点设置路径，类中添加Path属性
    public String text;   //节点名称
    public List<Node> nodes;  //子节点，可以用递归的方法读取，方法在下一章会总结
	public int topic; //大类
	public boolean isRoot; //是否根节点
	public String parentid; //父类id
    public Node(String id, String parentid, String text, int topic, boolean isRoot, List<Node> node)
    {
        this.id = id;
        this.parentid = parentid;
        this.text = text;
        this.topic = topic;
        this.isRoot = isRoot;		
        this.nodes = node;
    }
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public List<Node> getNodes() {
		return nodes;
	}
	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}
	public int getTopic() {
		return topic;
	}
	public void setTopic(int topic) {
		this.topic = topic;
	}
	public boolean isRoot() {
		return isRoot;
	}
	public void setRoot(boolean isRoot) {
		this.isRoot = isRoot;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
}
