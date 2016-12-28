package org.appsugar.security.form;

import org.pac4j.oauth.client.CasOAuthWrapperClient;

/**
 * 自定义cas oauth2 客户端
 * @author NewYoung
 * 2016年12月28日下午3:58:23
 */
public class CasOauth2Client extends CasOAuthWrapperClient {

	public CasOauth2Client() {
		super();
	}

	public CasOauth2Client(String key, String secret, String casOAuthUrl) {
		super(key, secret, casOAuthUrl);
	}

}
