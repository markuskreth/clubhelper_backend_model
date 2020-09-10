package de.kreth.clubhelperbackend.utils;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

@Component
public class TimeProviderImpl implements TimeProvider {

	@Override
	public LocalDateTime getNow() {
		return LocalDateTime.now();
	}

}
