package de.kreth.clubhelperbackend.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping("/")
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @param response
	 *            response Object for redirection.
	 * @param device
	 *            device Type to decide redirection target.
	 * @param locale
	 *            locale for language
	 * @param model
	 *            model to set response data
	 * @return Name of View
	 */
	@GetMapping(value = "/")
	public String home(HttpServletResponse response, Locale locale) {

		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		return "This is Clubhelper Backend at " + formattedDate;
	}

}
