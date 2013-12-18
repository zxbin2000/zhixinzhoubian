package com.android.search.constant;

public class ReturnCode {
	public static final int OK = 0;// ok 正常
	public static final int PARAM_INVALID = 2;// Parameter Invalid 请求参数非法
	public static final int VERIFY_FAILURE = 3;// Verify Failure 权限校验失败
	public static final int QUOTA_FAILURE = 4;// Quota Failure 权限校验失败
	public static final int AK_FAILURE = 5;// AK Failure ak不存在或者非法
}