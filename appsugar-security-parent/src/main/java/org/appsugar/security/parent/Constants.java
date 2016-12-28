package org.appsugar.security.parent;

/**
 * 常量定义
 * @author NewYoung
 * 2016年12月28日上午10:49:36
 */
public final class Constants {
	public static final String FILTER_MAPPING_NAME = "pac4jFilterMapping";
	public static final String CLIENT_CALLBACK_URL_KEY = "appsugar.security.pac4j.callback.url";
	public static final String CALLBACK_FILTER_NAME = "loginCallbackFilter";
	//form
	public static final String FORM_LOGIN_URL_KEY = "appsugar.security.pac4j.form.login.url";
	public static final String FORM_CLIENT_NAME = "FormClient";
	public static final String FORM_FILTER_NAME = "formSecurityFilter";
	//cas
	public static final String CAS_LOGIN_URL_KEY = "appsugar.security.pac4j.cas.login.url";
	public static final String CAS_CLIENT_NAME = "CasClient";
	public static final String CAS_FILTER_NAME = "casSecurityFilter";
	//cas oauth2
	public static final String CAS_OAUTH2_LOGIN_URL_KEY = "appsugar.security.pac4j.casoauth2.login.url";
	public static final String CAS_OAUTH2_KEY = "appsugar.security.pac4j.casoauth2.key";
	public static final String CAS_OAUTH2_SECRET_KEY = "appsugar.security.pac4j.casoauth2.secret";
	public static final String CAS_OAUTH2_CLIENT_NAME = "CasOauth2Client";
	public static final String CAS_OAUTH2_FILTER_NAME = "casOauth2SecurityFilter";
}
