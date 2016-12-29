package org.appsugar.security.form;

import java.util.Map;

import javax.servlet.Filter;

import org.appsugar.security.parent.Constants;
import org.appsugar.security.parent.FilterAdapter;
import org.appsugar.security.parent.Pac4jClients;
import org.appsugar.security.parent.Pac4jConfiguration;
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
 * CAS oauth2登录方式配置
 * @author NewYoung
 * 2016年12月28日上午10:11:20
 */
@Configuration
@Import(Pac4jConfiguration.class)
public class Pac4jCasOauth2LoginConfiguration {
	private static final Logger logger = LoggerFactory.getLogger(Pac4jCasOauth2LoginConfiguration.class);
	@Autowired
	private Environment env;
	@Autowired
	private Pac4jClients clients;
	@Autowired
	private Config config;

	@Bean
	@Lazy(false)
	public CasOauth2Client casOauth2Client() {
		String loginUrl = env.getProperty(Constants.CAS_OAUTH2_LOGIN_URL_KEY);
		logger.debug("cas oauth2 login url is {}", loginUrl);
		CasOauth2Client client = new CasOauth2Client();
		client.setCasOAuthUrl(loginUrl);
		client.setKey(env.getProperty(Constants.CAS_OAUTH2_KEY));
		client.setSecret(env.getProperty(Constants.CAS_OAUTH2_SECRET_KEY));
		clients.addClient(client);
		return client;
	}

	public CasOauth2SecurityFilter casOauth2SecurityFilter() {
		CasOauth2SecurityFilter filter = new CasOauth2SecurityFilter();
		filter.setClients(Constants.CAS_OAUTH2_CLIENT_NAME);
		filter.setConfig(config);
		return filter;
	}

	@Autowired
	@Qualifier(Constants.FILTER_MAPPING_NAME)
	public void setFilters(Map<String, Filter> filters) {
		FilterAdapter filter = (FilterAdapter) filters.get(Constants.CAS_OAUTH2_FILTER_NAME);
		filter.setFilter(casOauth2SecurityFilter());
	}
}
