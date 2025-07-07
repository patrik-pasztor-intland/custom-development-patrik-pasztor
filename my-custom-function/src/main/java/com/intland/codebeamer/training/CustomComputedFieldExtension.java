package com.intland.codebeamer.training;

import com.intland.codebeamer.persistence.util.TrackerItemFilterEL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
@Lazy(false)
public class CustomComputedFieldExtension {
	public static final Logger logger = LogManager.getLogger(CustomComputedFieldExtension.class);

	@PostConstruct
	public void register() {
		try {
			TrackerItemFilterEL.registerFunction("customFunction",
					CustomComputedFieldExtension.class.getMethod("customFunction", String.class));
		} catch (Exception ex) {
			logger.warn("Could not register TrackerItemFilterEL function 'customFunction'", ex);
		}
	}


	public static String customFunction(String param) {
		return "Hello PTC" + param;
	}
}
