package com.ge.data.simulator.service;

import static java.util.Collections.emptyMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class QueryController {

	@Value("${demo.timeseries.tagsUrlPrefix}")
	private String tagsUrlPrefix;

	@Value("${demo.timeseries.latestQueryUrlPrefix}")
	private String latestQueryUrlPrefix;

	@Value("${demo.timeseries.latestLimitedQueryUrlPrefix}")
	private String latestLimitedQueryUrlPrefix;

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@RequestMapping("/ping")
	public String ping() {
		return "pong - " + System.currentTimeMillis();
	}

	@ExceptionHandler
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public Object exceptionHandler(HttpStatusCodeException e) {
		return e.getResponseBodyAsString();
	}

	@ResponseBody
	@RequestMapping("/tags")
	public String queryTags() throws Exception {
		ResponseEntity<String> resultStr = restTemplate.getForEntity(tagsUrlPrefix, String.class, emptyMap());
		return resultStr.getBody();
	}

	@ResponseBody
	@RequestMapping("/latest")
	public String queryLatestValues(@RequestBody /*
													 * defaultValue =
													 * "{\"tags\":[{\"name\":\"SMARTBIN_1_airQuality\"}]}")
													 */ String requestBody) throws Exception {
		if ((requestBody != null && requestBody.equals("")) || (requestBody == null)) {
			requestBody = "{\"tags\":[{\"name\":\"SMARTBIN_1_airQuality\"}]}";
		}
		ResponseEntity<String> resultStr = restTemplate.postForEntity(latestQueryUrlPrefix, requestBody, String.class,
				emptyMap());
		return resultStr.getBody();
	}

	@ResponseBody
	@RequestMapping("/latestLimited")
	public String queryLatestLimitedValues(@RequestParam("tagName") String tagName) throws Exception {

		String requestBody = "";
		// @RequestParam(defaultValue = "{\"start\": \"1y-ago\",\"tags\":
		// [{\"name\": \"SMARTBIN_1_binHeight\",\"order\": \"desc\",\"limit\":
		// 10}]}") String requestBody
		if (tagName != null && !tagName.isEmpty()) {
			requestBody = "{\"start\": \"1y-ago\",\"tags\": [{\"name\": \"" + tagName
					+ "\",\"order\": \"desc\",\"limit\": 10}]}";
		}
		ResponseEntity<String> resultStr = restTemplate.postForEntity(latestLimitedQueryUrlPrefix, requestBody,
				String.class, emptyMap());
		return resultStr.getBody();
	}
}
