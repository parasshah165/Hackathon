package com.ge.tcs.cityOfTomorrow.controller;

import static java.util.Collections.emptyMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.ResponseJSON;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AssetRESTClient {

	private static final Log logger = LogFactory.getLog(AssetRESTClient.class);

	@Value("${demo.assetservice.assetURLPrefix}")
	String assetURL;

	@Value("${demo.assetservice.latestQueryUrlPrefix}")
	String latestQueryUrlPrefix;

	@Autowired
	@Qualifier("restTemplate")
	private RestTemplate restTemplate;

	@Autowired
	@Qualifier("restTemplateTimeSeries")
	private RestTemplate restTemplateTimeSeries;

	@RequestMapping("/pingAsset")
	public String ping() {
		return "pong - " + System.currentTimeMillis();
	}

	@ResponseBody
	@RequestMapping(value = "/getAssetData")
	public List<ResponseJSON> getAssetData(
			/*
			 * final HttpServletResponse response, final HttpServletRequest
			 * request,
			 * 
			 * @RequestHeader(required = false) Map<String, String>
			 * requestHeaders,
			 */@RequestParam("assetName") String assetName) throws Exception {
		logger.info("Getting into getAssetData values");
		ResponseJSON objResponseJSON = null;
		List<ResponseJSON> returnList = new ArrayList<ResponseJSON>();
		// HttpHeaders headers = new HttpHeaders();
		// headers.setAll(requestHeaders);
		// headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
		// logger.info("headers are:::" + headers.toString());
		// // headers.set("Predix-Zone-Id", zoneId);
		//
		// HttpEntity<?> requestObj = new HttpEntity<>(headers);
		ResponseEntity<String> resultStr = restTemplate.exchange(assetURL + "/" + assetName, HttpMethod.GET, null,
				String.class, emptyMap());
		if (resultStr != null) {
			// Map jsonJavaRootObject = new Gson().fromJson(resultStr.getBody(),
			// Map.class);

			JsonElement jelement = new JsonParser().parse(resultStr.getBody());
			JsonArray objJsonArray = jelement.getAsJsonArray();

			// JsonObject jobject = jelement.getAsJsonObject();
			for (int i = 0; i < objJsonArray.size(); i++) {
				JsonElement jelement1 = objJsonArray.get(i);
				JsonObject obj = jelement1.getAsJsonObject();

				if (assetName != null && !assetName.isEmpty() && assetName.equals("smartbin")) {

					objResponseJSON = new ResponseJSON();
					objResponseJSON.setAssetid(obj.get("sbid").toString().replace("\"", "").replace("\"", "").trim());
					objResponseJSON.setAssetsid(i + 1);
					String latitude = obj.get("sblatitude").toString();
					latitude = latitude.replace("\"", "").replace("\"", "").trim();
					objResponseJSON.setLat(Double.valueOf(latitude));

					String longitude = obj.get("sblongitude").toString();
					longitude = longitude.replace("\"", "").replace("\"", "").trim();
					objResponseJSON.setLongitude(Double.valueOf(longitude));

					objResponseJSON.setType(obj.get("type").toString().replace("\"", "").replace("\"", "").trim());

					Map<String, Object> params = new HashMap<String, Object>();

					String requestBody1 = "{\"tags\":[{\"name\":\""
							+ obj.get("sbid").toString().replace("\"", "").replace("\"", "").trim() + "_binHeight\"}]}";
					ResponseEntity<String> resultStr1 = restTemplateTimeSeries.postForEntity(latestQueryUrlPrefix,
							requestBody1, String.class, emptyMap());

					String requestBody2 = "{\"tags\":[{\"name\":\""
							+ obj.get("sbid").toString().replace("\"", "").replace("\"", "").trim()
							+ "_airQuality\"}]}";
					ResponseEntity<String> resultStr2 = restTemplateTimeSeries.postForEntity(latestQueryUrlPrefix,
							requestBody2, String.class, emptyMap());

					params.put("BinHeight", getLatestValue(resultStr1.getBody()));
					params.put("AirQuality", getLatestValue(resultStr2.getBody()));
					objResponseJSON.setParams(params);

					String requestBody3 = "{\"tags\":[{\"name\":\""
							+ obj.get("sbid").toString().replace("\"", "").replace("\"", "").trim() + "_color\"}]}";
					ResponseEntity<String> resultStr3 = restTemplateTimeSeries.postForEntity(latestQueryUrlPrefix,
							requestBody3, String.class, emptyMap());
					objResponseJSON.setStatus(getLatestValue(resultStr3.getBody()).toUpperCase());

				}

				else if (assetName != null && !assetName.isEmpty() && assetName.equals("watertank")) {

					objResponseJSON = new ResponseJSON();
					objResponseJSON.setAssetid(obj.get("wtid").toString().replace("\"", "").replace("\"", "").trim());
					objResponseJSON.setAssetsid(i + 1);
					String latitude = obj.get("wtlatitude").toString();
					latitude = latitude.replace("\"", "").replace("\"", "").trim();
					objResponseJSON.setLat(Double.valueOf(latitude));

					String longitude = obj.get("wtlongitude").toString();
					longitude = longitude.replace("\"", "").replace("\"", "").trim();
					objResponseJSON.setLongitude(Double.valueOf(longitude));

					objResponseJSON.setType(obj.get("type").toString().replace("\"", "").replace("\"", "").trim());

					Map<String, Object> params = new HashMap<String, Object>();

					String requestBody1 = "{\"tags\":[{\"name\":\""
							+ obj.get("wtid").toString().replace("\"", "").replace("\"", "").trim() + "_phLevel\"}]}";
					ResponseEntity<String> resultStr1 = restTemplateTimeSeries.postForEntity(latestQueryUrlPrefix,
							requestBody1, String.class, emptyMap());

					// String requestBody2 = "{\"tags\":[{\"name\":\""
					// + obj.get("sbid").toString().replace("\"",
					// "").replace("\"", "").trim()
					// + "_airQuality\"}]}";
					// ResponseEntity<String> resultStr2 =
					// restTemplateTimeSeries.postForEntity(latestQueryUrlPrefix,
					// requestBody2, String.class, emptyMap());

					params.put("phLevel", getLatestValue(resultStr1.getBody()));
					params.put("chlorine", "20%");
					params.put("turbidity", "10%");
					params.put("lead", "25 mg/L");
					objResponseJSON.setParams(params);

					// String requestBody3 = "{\"tags\":[{\"name\":\""
					// + obj.get("sbid").toString().replace("\"",
					// "").replace("\"", "").trim() + "_color\"}]}";
					// ResponseEntity<String> resultStr3 =
					// restTemplateTimeSeries.postForEntity(latestQueryUrlPrefix,
					// requestBody3, String.class, emptyMap());
					if (resultStr1 != null && resultStr1.getBody() != null && !resultStr1.getBody().isEmpty()
							&& getLatestValue(resultStr1.getBody()) != null
							&& !getLatestValue(resultStr1.getBody()).isEmpty()) {
						if (Double.valueOf(getLatestValue(resultStr1.getBody())).doubleValue() > 8.0
								|| Double.valueOf(getLatestValue(resultStr1.getBody())).doubleValue() < 6.0) {
							objResponseJSON.setStatus("RED");
						} else {
							objResponseJSON.setStatus("GREEN");
						}
					}

				}
				returnList.add(objResponseJSON);
			}

			// logger.info("jsonJavaRootObject is ::" + objJsonArray.get(1));
			// JSONObject jsonObject = new JSONObject(resultStr.getBody());
			// JSONArray obj=jsonObject.getJSONArray("");
			// for(int i=0;i<obj.length();i++){
			// String requestBody="{\"tags\":[{\"name\":\""+obj[0]
			// +"_airQuality\"}]}";
			// ResponseEntity<String> resultStr =
			// restTemplate.exchange("https://garbagebindatasimulator.run.aws-usw02-pr.ice.predix.io/latest",
			// HttpMethod.POST, null,String.class, emptyMap());
			// }
		}

		return returnList;
	}

	public String getLatestValue(String dataPoints)

	{
		JSONObject dataPointsObject = new JSONObject(dataPoints);
		JSONArray tagsArray = dataPointsObject.getJSONArray("tags");
		String name = null;
		String value = "";
		for (int i = 0; i < tagsArray.length(); i++) {
			name = (String) tagsArray.getJSONObject(i).get("name");
			logger.debug("GvlClientServiceImpl :: retrieveTimeSeriesDataPoints : name - " + name);

			JSONArray resultsArray = tagsArray.getJSONObject(i).getJSONArray("results");
			JSONArray valuesArray = resultsArray.getJSONObject(0).getJSONArray("values");

			for (int k = 0; k < valuesArray.length(); k++) {
				JSONArray jsonArray = valuesArray.getJSONArray(k);

				value = String.valueOf(jsonArray.get(k + 1));
				logger.debug("GvlClientServiceImpl :: retrieveTimeSeriesDataPoints : value - " + value);
				logger.debug("value - 1 : " + jsonArray.get(0) + "value - 2: " + jsonArray.get(1) + "value - 3: "
						+ jsonArray.get(2));
			}

		}
		return value;
	}

	@ExceptionHandler
	@RequestMapping(produces = MediaType.TEXT_HTML_VALUE)
	public Object exceptionHandler(HttpStatusCodeException e) {
		return e.getResponseBodyAsString();
	}

}
