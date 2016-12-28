package org.appsugar.security.form;

import java.util.Map;

import javax.servlet.Filter;

import org.appsugar.security.parent.Constants;
import org.appsugar.security.parent.FilterAdapter;
import org.appsugar.security.parent.Pac4jClients;
import org.appsugar.security.parent.Pac4jConfiguration;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.config.CasConfiguration;
import org.pac4j.core.config.Config;
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
 * CAS登录方式配置
 * @author NewYoung
 * 2016年12月28日上午10:11:20
 */
@Configuration
@Import(Pac4jConfiguration.class)
public class Pac4jCasLoginConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(Pac4jCasLoginConfiguration.class);
	@Autowired
	private Environment env;
	@Autowired
	private Pac4jClients clients;
	@Autowired
	private Config config;

	@Bean
	@Lazy(false)
	public CasClient casClient() {
		String loginUrl = env.getProperty(Constants.CAS_LOGIN_URL_KEY);
		logger.debug("cas login url is {}", loginUrl);
		CasClient client = new CasClient();
		CasConfiguration cfg = new CasConfiguration();
		cfg.setLoginUrl(loginUrl);
		client.setConfiguration(cfg);
		//把客户端添加到集合列表中
		clients.addClient(client);
		return client;
	}

	public CasSecurityFilter casSecurityFilter() {
		CasSecurityFilter filter = new CasSecurityFilter();
		filter.setClients(Constants.CAS_CLIENT_NAME);
		filter.setConfig(config);
		return filter;
	}

	@Autowired
	@Qualifier(Constants.FILTER_MAPPING_NAME)
	public void setFilters(Map<String, Filter> filters) {
		FilterAdapter filter = (FilterAdapter) filters.get(Constants.CAS_FILTER_NAME);
		filter.setFilter(casSecurityFilter());
	}
}
