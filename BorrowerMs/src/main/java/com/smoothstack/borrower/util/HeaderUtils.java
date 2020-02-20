package com.smoothstack.borrower.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public final class HeaderUtils {

	/**
	 * Utility class for HTTP headers creation.
	 */
	private static final Logger log = LoggerFactory.getLogger(HeaderUtils.class);

	private HeaderUtils() {
	}

	public static HttpHeaders createAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-securityHandlerApp-alert", message);
		headers.add("X-securityHandlerApp-params", param);
		return headers;
	}

	public static HttpHeaders createEntityCreationAlert(String entityName, String param) {
		return createAlert("A new " + entityName + " is created with identifier " + param, param);
	}

	public static HttpHeaders createEntityUpdateAlert(String entityName, String param) {
		return createAlert("A " + entityName + " is updated with identifier " + param, param);
	}

	public static HttpHeaders createEntityDeletionAlert(String entityName, String param) {
		return createAlert("A " + entityName + " is deleted with identifier " + param, param);
	}

	public static HttpHeaders createFailureAlert(String entityName, String errorKey, String defaultMessage) {
		log.error("Entity processing failed, {}", defaultMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-servicebuildermsApp-error", defaultMessage);
		headers.add("X-servicebuildermsApp-params", entityName);
		return headers;
	}
}
