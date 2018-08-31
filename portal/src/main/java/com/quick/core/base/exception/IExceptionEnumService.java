package com.quick.core.base.exception;

/**
 * 抽象接口
 *
 */
public interface IExceptionEnumService {

    /**
     * 获取异常编码
     */
	String getCode();

    /**
     * 获取异常信息
     */
    String getMessage();
}
