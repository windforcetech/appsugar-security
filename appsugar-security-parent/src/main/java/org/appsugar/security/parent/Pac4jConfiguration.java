package org.appsugar.security.parent;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.pac4j.core.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;

import io.buji.pac4j.filter.CallbackFilter;

/**
 * form登录方式配置
 * @author NewYoung
 * 2016年12月28日上午10:11:20
 */
@Configuration
public class Pac4jConfiguration {
	public static final Logger logger = LoggerFactory.getLogger(Pac4jConfiguration.class);
	@Autowired
	private Environment env;

	/**
	 * 定义客户端集合
	 * @author NewYoung
	 * 2016年12月28日上午10:43:39
	 */
	@Bean
	@Lazy(false)
	public Pac4jClients pac4jClients() {
		Pac4jClients clients = new Pac4jClients();
		String callbackUrl = env.getProperty(Constants.CLIENT_CALLBACK_URL_KEY);
		logger.debug("callback url is {}", callbackUrl);
		clients.setCallbackUrl(callbackUrl);
		return clients;
	}

	/**
	 * 定义客户端配置
	 * @author NewYoung
	 * 2016年12月28日上午10:44:19
	 */
	@Bean
	@Lazy(false)
	public Config pac4jConfig() {
		return new Config(pac4jClients());
	}

	/**
	 * 回调拦截器
	 * @author NewYoung
	 * 2016年12月28日下午1:19:50
	 */
	public CallbackFilter callbackFilter() {
		CallbackFilter filter = new CallbackFilter();
		filter.setConfig(pac4jConfig());
		return filter;
	}

	@Bean(name = Constants.FILTER_MAPPING_NAME)
	public Map<String, Filter> pac4jFilterMapping() {
		Map<String, Filter> filters = new HashMap<>();
		filters.put(Constants.CALLBACK_FILTER_NAME, callbackFilter());
		filters.put(Constants.FORM_FILTER_NAME, new FilterAdapter());
		filters.put(Constants.CAS_FILTER_NAME, new FilterAdapter());
		filters.put(Constants.CAS_OAUTH2_FILTER_NAME, new FilterAdapter());
		return filters;
	}
}
