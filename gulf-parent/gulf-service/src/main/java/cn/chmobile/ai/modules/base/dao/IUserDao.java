package cn.chmobile.ai.modules.base.dao;

import cn.chmobile.ai.util.PageData;

public interface IUserDao {

	PageData selectByPrimaryKey(Integer id);

}