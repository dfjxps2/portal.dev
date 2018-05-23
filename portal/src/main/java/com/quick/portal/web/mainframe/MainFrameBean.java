/**
 * <h3>标题 : Quick通用系统框架 </h3>
 * <h3>描述 : 应用中的相关配置信息都放在此</h3>
 * <h3>日期 : 2018-04-13</h3>
 * <h3>版权 : Copyright (C) 海口鑫网计算机网络有限公司</h3>
 * 
 * <p>
 * @author admin cxh@test.com.cn
 * @version <b>v1.0.0</b>
 *          
 * <b>修改历史:</b>
 * -------------------------------------------
 * 修改人 修改日期 修改描述
 * -------------------------------------------
 *          
 *          
 * </p>
 */

package com.quick.portal.web.mainframe;


import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;



/**
 * 当前登录用户信息
 * 
 * @author Administrator
 */
public class MainFrameBean implements Serializable {
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public int getSuperMenuId() {
		return superMenuId;
	}
	public void setSuperMenuId(int superMenuId) {
		this.superMenuId = superMenuId;
	}
	public String getMenuCnName() {
		return menuCnName;
	}
	public void setMenuCnName(String menuCnName) {
		this.menuCnName = menuCnName;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuIconUrl() {
		return menuIconUrl;
	}
	public void setMenuIconUrl(String menuIconUrl) {
		this.menuIconUrl = menuIconUrl;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public int getMenuLevel() {
		return menuLevel;
	}
	public void setMenuLevel(int menuLevel) {
		this.menuLevel = menuLevel;
	}
	public Integer getMenuState() {
		return menuState;
	}
	public void setMenuState(Integer menuState) {
		this.menuState = menuState;
	}
	public Timestamp getCreTime() {
		return creTime;
	}
	public void setCreTime(Timestamp creTime) {
		this.creTime = creTime;
	}
	public Timestamp getUpdTime() {
		return updTime;
	}
	public void setUpdTime(Timestamp updTime) {
		this.updTime = updTime;
	}
	public void setChildren(List<MainFrameBean> children) {
		this.children = children;
	}
	private static final long serialVersionUID = 1L;

	private int menuId; // 菜单ID
	private int superMenuId; // 登录账号
	private String menuCnName; // 菜单中文名
	private Integer depth;

	private String menuName; // 菜单名称
	private String menuIconUrl; // 菜单图标URL
	public int getAppId() {
		return appId;
	}
	public void setAppId(int appId) {
		this.appId = appId;
	}
	public int getDispOrder() {
		return dispOrder;
	}
	public void setDispOrder(int dispOrder) {
		this.dispOrder = dispOrder;
	}
	private String menuUrl; // 菜单功能URL

	private int menuLevel; // 菜单ID
	private Integer menuState; // 菜单状态

	private int appId; // 对应的应用ID
	private int dispOrder; // 显示顺序

	private Timestamp creTime; // 创建时间
	private Timestamp updTime; // 更新时间
	
	
	private boolean parent;
	
	private List<MainFrameBean> children;
	
	public MainFrameBean() {
		super();
		this.children = new ArrayList<MainFrameBean>();
		this.setParent(false);
		this.depth = 0;
	}


	public List<MainFrameBean> getChildren() {
		return children;
	}
	
	
	public void buildChildren(List<MainFrameBean> chldList) {
		List<MainFrameBean> children = new ArrayList<MainFrameBean>();
		for (MainFrameBean MainFrameBean : chldList) {
			if (MainFrameBean.getSuperMenuId() == (this.getMenuId())) {
				MainFrameBean.setDepth(this.depth + 1);
				children.add(MainFrameBean);
			}
		}
		this.children.addAll(children);
	
		if (this.children.size() > 0){
			this.setParent(true);
		}
		else{
			this.setParent(false);
		}
		
		if (this.children.size() > 0){
			this.setParent(true);
		}
		else{
			this.setParent(false);
		}
		
		Collections.sort(this.children, new Comparator<MainFrameBean>() {
			@Override
			public int compare(MainFrameBean o1, MainFrameBean o2) {
				return o1.getMenuId() - o2.getMenuId();
			}
		});
		for (MainFrameBean MainFrmBean : children) {
			MainFrmBean.buildChildren(chldList);
		}
	}
	

	
	public boolean isParent() {
		return parent;
	}

	public void setParent(boolean parent) {
		this.parent = parent;
	}
	
	public Integer getDepth() {
		return depth;
	}

	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	
}
