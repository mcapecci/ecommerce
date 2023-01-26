package com.ecommerce.application;

import java.net.InetAddress;
import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.env.Environment;

/**
 * The class <code>com.ecommerce.application.ApplicationUtils</code> is a util
 * class for operations over applications.
 * 
 * @author Eva Magalí Capecci
 *
 */
public final class ApplicationUtils {

	/**
	 * Unique constructor without arguments.
	 */
	private ApplicationUtils() {
		super();
	}

	/**
	 * Shows information about the application.
	 *
	 * @param env Environment.
	 * @return Info.
	 */
	public static String logApplicationStartup(Environment env) {

		String protocol = "http";
		if (env.getProperty("server.ssl.key-store") != null) {
			protocol = "https";
		}
		String serverPort = env.getProperty("server.port");
		String contextPath = env.getProperty("server.servlet.context-path");
		if (StringUtils.isBlank(contextPath)) {
			contextPath = "/";
		}
		String hostAddress = "localhost";
		try {
			hostAddress = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return String.format(
				"\n----------------------------------------------------------\n\t"
						+ "Application '%s' is running! Access URLs:\n\t" + "Local: \t\t%s://localhost:%s%s\n\t"
						+ "Swagger: /swagger-ui.html#\n\t" + "External: \t%s://%s:%s:%s\n\t"
						+ "Profile(s): \t%s\n----------------------------------------------------------",
				env.getProperty("spring.application.name"), protocol, serverPort, contextPath, protocol, hostAddress,
				serverPort, contextPath, Arrays.toString(env.getActiveProfiles()));

	}
}
