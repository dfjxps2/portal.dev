package com.quick.core.base.exception;

import com.quick.core.base.exception.IExceptionEnumService;

/**
 * @Description 业务异常的枚举
 */
public enum ExceptionEnumServiceImpl implements IExceptionEnumService{

	/**
	 * 字典
	 */
	DICT_EXISTED("200","字典已经存在"),
	ERROR_CREATE_DICT("210","创建字典失败"),
	ERROR_WRAPPER_FIELD("220","包装字典属性失败"),
	ERROR_CODE_EMPTY("230","字典类型不能为空"),

	/**
	 * 文件上传
	 */
	FILE_READING_ERROR("300","FILE_READING_ERROR!"),
	FILE_NOT_FOUND("310","FILE_NOT_FOUND!"),
	UPLOAD_ERROR("320","上传图片出错"),

	/**
	 * 权限和数据问题
	 */
	USER_ROLE_ISVALID("410","用户角色异常"),
	DB_RESOURCE_NULL("420","数据库中没有该资源"),
	NO_PERMITION("430", "查询权限菜单异常"),
	REQUEST_INVALIDATE("440","请求数据格式不正确"),
	INVALID_KAPTCHA("440","验证码不正确"),
	CANT_DELETE_ADMIN("460","不能删除超级管理员"),
	CANT_FREEZE_ADMIN("460","不能冻结超级管理员"),
	CANT_CHANGE_ADMIN("470","不能修改超级管理员角色"),
	USER_RESOURCE_NULL("480","用户没有查询资源权限"),

	/**
	 * 账户问题
	 */
	USER_ALREADY_REG("500","该用户已经注册"),
	NO_THIS_USER("500","用户不存在"),
	USER_NOT_EXISTED("520", "没有此用户"),
	ACCOUNT_FREEZED("530", "账号被冻结"),
	OLD_PWD_NOT_RIGHT("540", "原密码不正确"),
	TWO_PWD_NOT_MATCH("550", "两次输入密码不一致"),
	USER_STATUS_LOCKING("560", "帐号被锁,请联系管理员"),
	USER_STATUS_WARING("570", "帐号被锁,请联系管理员"),
	USER_STATUS_ERROR("580", "通过IP查询用户账号异常"),

	/**
	 * 错误的请求
	 */

	EXISTED_THE_MENU("600","菜单编号重复，不能添加"),
	DICT_MUST_BE_NUMBER("610","值必须为数字"),
	REQUEST_NULL("620", "请求有错误"),
	SESSION_TIMEOUT("630", "会话超时"),
	SERVER_ERROR("640", "服务器异常");
	


	ExceptionEnumServiceImpl(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	private String code;

	private String message;

	@Override
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
