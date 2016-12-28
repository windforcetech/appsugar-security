package org.appsugar.security.parent;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import io.buji.pac4j.filter.SecurityFilter;

/**
 * filter适配器
 * @author NewYoung
 * 2016年12月28日下午1:49:07
 */
public class FilterAdapter implements Filter {

	private SecurityFilter filter;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		filter.init(filterConfig);
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		filter.doFilter(request, response, chain);
	}

	@Override
	public void destroy() {
		filter.destroy();
	}

	public void setFilter(SecurityFilter filter) {
		this.filter = filter;
	}

}
