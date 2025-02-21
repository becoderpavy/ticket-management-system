package com.ticket.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

import com.ticket.entity.User;
import com.ticket.util.CommonUtil;

public class AuditAwareConfig implements AuditorAware<Integer> {

	@Override
	public Optional<Integer> getCurrentAuditor() {
		User loggedInUser = CommonUtil.getLoggedInUser();
		return Optional.of(loggedInUser.getId());
	}

}
