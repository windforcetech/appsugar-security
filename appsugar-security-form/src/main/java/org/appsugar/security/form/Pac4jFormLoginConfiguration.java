package org.appsugar.security.form;

import java.util.Map;

import javax.servlet.Filter;

import org.appsugar.security.parent.Constants;
import org.appsugar.security.parent.FilterAdapter;
import org.appsugar.security.parent.Pac4jClients;
import org.appsugar.security.parent.Pac4jConfiguration;
import org.pac4j.core.config.Config;
import org.pac4j.http.client.indirect.FormClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

/**
 * form登录方式配置
 * @author NewYoung
 * 2016年12月28日上午10:11:20
 */
@Configuration
@Import(Pac4jConfiguration.class)
public class Pac4jFormLoginConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(Pac4jFormLoginConfiguration.class);
	@Autowired
	private Environment env;
	@Autowired
	private Pac4jClients clients;
	@Autowired
	private Config config;

	@Bean
	@Lazy(false)
	public FormClient formClient(FormAuthenticator formAuthenticator) {
		String loginUrl = env.getProperty(Constants.FORM_LOGIN_URL_KEY);
		logger.debug("form login url is {}", loginUrl);
		FormClient client = new FormClient();
		client.setAuthenticator(formAuthenticator);
		client.setLoginUrl(loginUrl);
		//把客户端添加到集合列表中
		clients.addClient(client);
		return client;
	}

	public FormSecurityFilter formSecurityFilter() {
		FormSecurityFilter filter = new FormSecurityFilter();
		filter.setClients(Constants.FORM_CLIENT_NAME);
		filter.setConfig(config);
		return filter;
	}

	@Autowired
	@Qualifier(Constants.FILTER_MAPPING_NAME)
	public void setFilters(Map<String, Filter> filters) {
		FilterAdapter filter = (FilterAdapter) filters.get(Constants.FORM_FILTER_NAME);
		filter.setFilter(formSecurityFilter());
	}

}
