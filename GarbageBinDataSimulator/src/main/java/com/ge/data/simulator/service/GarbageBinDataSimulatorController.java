package com.ge.data.simulator.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.math.util.MathUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.data.simulator.model.LocationData;
import com.ge.data.simulator.model.SmartBin;
import com.ge.data.simulator.model.WebSocketClient;

@RestController

public class GarbageBinDataSimulatorController /* implements EnvironmentAware */ {

	private static final Log logger = LogFactory.getLog(GarbageBinDataSimulatorController.class);

	@Value("${demo.timeseries.ingestUrl}")
	String ingestUrl;

	@Value("${demo.timeseries.zoneId}")
	String zoneId;

	WebSocketClient wsc = new WebSocketClient();

	@Autowired
	@Qualifier("restTemplate")
	private OAuth2RestTemplate restTemplate;

	private String getAccessToken() {
		return restTemplate.getAccessToken().getValue();
	}

	private static boolean runSimulation = false;

	// SmartBinGatewayType smartBinGatewayType;
	LocationData locationData = new LocationData();
	// BinHeightData binHeightData = new BinHeightData();
	// GarbageAirQuality garbageAirQuality = new GarbageAirQuality();

	@RequestMapping(value = "/simulator/start"/*
												 * , method = RequestMethod.GET,
												 * consumes = "application/json"
												 */)
	public String startSimulation() throws Exception {
		runSimulation = true;

		if (runSimulation) {

			if (!wsc.isConnected()) {
				wsc.connect(ingestUrl, getAccessToken(), zoneId);
				// generateSimulatorData();
				return "Socket Connected";
			}
		}
		return "Failed to connect";

	}

	@PreDestroy
	@RequestMapping(value = "/simulator/stop"/*
												 * , method = RequestMethod.GET,
												 * consumes = "application/json"
												 */)
	public String stopSimulation() throws Exception {
		runSimulation = false;
		if (!runSimulation) {
			if (wsc.isConnected()) {
				wsc.disconnect();
				return "Socket disconnected";
			}
		}
		return "Failed to disconnect";
	}

	// private String retrieveAuthorizationToken() {
	//
	// List<Header> headers =
	// this.restClient.getOauthHttpHeaders("engine_telematics:3ng!n3T3lem@t!cs",
	// true);
	// String tokenString = this.restClient.requestToken(headers,
	// "/oauth/token",
	// "c28ffbe1-111c-4284-bba4-6bad40eac516.predix-uaa.run.aws-usw02-pr.ice.predix.io",
	// "80",
	// "client_credentials", null, null);
	//
	// logger.debug("GvlClientServiceImpl :: retrieveAuthorizationToken : token
	// - " + tokenString);
	//
	// JSONObject token = new JSONObject(tokenString);
	// String authorization = "Bearer " + token.getString("access_token");
	// return authorization;
	// }

	static Random random = new Random(System.currentTimeMillis());

	public static String generateMessage(int dataPointAmount, String attributeName, SmartBin smartBin, int paramId) {
		String jsonStr = "{\"messageId\":\"" + smartBin.name() + "\",\"body\":[{\"name\":\"" + attributeName + "\","
				+ "\"datapoints\":" + generateDataPoints(dataPointAmount, smartBin, paramId) + ","
				+ "\"attributes\":{\"host\":\"hackathon\",\"partner\":\"TCS\"}}]}";

		logger.info("JSON is" + jsonStr);
		return jsonStr;
	}

	public static String generateDataPoints(int amount, SmartBin smartBin, int paramId) {
		String[] dataPoints = new String[amount];
		for (int i = 0; i < amount; i++) {
			dataPoints[i] = generateDataPoint(i + 1, smartBin, paramId);
		}
		return Arrays.toString(dataPoints);
	}

	public static String generateDataPoint(int cnt, SmartBin smartBin, int paramId) {
		long millis = System.currentTimeMillis();
		Object value = new Object();
		if (paramId == 1) {
			value = 0.0;
			if (cnt % 2 == 0) {
				value = MathUtils.round(smartBin.getRedThreshold() - generateRandomUsageValue(1, 10), 0,
						BigDecimal.ROUND_HALF_DOWN);
			} else if (cnt % 3 == 0) {
				value = MathUtils.round(smartBin.getYellowThreshold() - generateRandomUsageValue(1, 20), 0,
						BigDecimal.ROUND_HALF_DOWN);
			} else {
				value = MathUtils.round(smartBin.getRedThreshold() - generateRandomUsageValue(1, 50), 0,
						BigDecimal.ROUND_HALF_DOWN);
			}
		} else if (paramId == 2) {
			value = random.nextInt(30);
		} else if(paramId == 3){
			value = "";
			if (cnt % 2 == 0) {
				value = "\"Red\"";
			} else if (cnt % 3 == 0) {
				value = "\"Yellow\"";
			} else {
				value = "\"Green\"";
			}
		}

		int quality = random.nextInt(4);
		return Arrays.toString(new Object[] { millis, value, quality });
	}

	@Scheduled(fixedRate = 60000)
	public void generateSimulatorData() throws IOException {

		logger.info(" GarbageBinDataSimulatorController :: generateSimulatorData  ");

		// while (runSimulation) {

		// the simulator is not supposed to run if the runSimulation flag is set
		// to false and so, return w/o pushing data to TimeSeries
		if (!runSimulation) {
			return;
		}

		// smartBinGatewayType = new SmartBinGatewayType();
		for (SmartBin smartBin : SmartBin.values()) {
			locationData.setName(smartBin.name() + "_location");
			locationData.setLatitude(String.valueOf(smartBin.getLatitude() + generateRandomUsageValue(0.55, 0.75)));
			locationData.setLongitude(String.valueOf(smartBin.getLongitude() + generateRandomUsageValue(0.10, 0.56)));

			wsc.sendMessage(generateMessage(10, smartBin.name() + "_binHeight", smartBin, 1));
			logger.info("Height data Ingested " + 10 + " datapoints.");
			wsc.sendMessage(generateMessage(10, smartBin.name() + "_airQuality", smartBin, 2));
			logger.info("airquality data Ingested " + 10 + " datapoints.");
			wsc.sendMessage(generateMessage(10, smartBin.name() + "_color", smartBin, 3));
			logger.info("Color data Ingested " + 10 + " datapoints.");

		}

		// }

	}

	// @Override
	// public void setEnvironment(Environment env) {
	// // this.timeseriesZone = env.getProperty("timeseriesZone");
	// // this.timeseriesUrl = env.getProperty("timeseriesUrl");
	// // this.clientId = env.getProperty("clientId");
	// }

	private static double generateRandomUsageValue(double low, double high) {
		return low + Math.random() * (high - low);

	}

}
