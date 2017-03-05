package net.tuxun.customer.module.admin.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.LogoutFilter;

public class SecLogoutFilter extends LogoutFilter {

	protected String getRedirectUrl(ServletRequest req, ServletResponse resp,
			Subject subject) {
		return "/back/login.do";
	}	
}
