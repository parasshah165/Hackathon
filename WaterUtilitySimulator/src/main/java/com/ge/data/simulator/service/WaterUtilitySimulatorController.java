package com.ge.data.simulator.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import javax.annotation.PreDestroy;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ge.data.simulator.model.LocationData;
import com.ge.data.simulator.model.Tank;
import com.ge.data.simulator.model.WebSocketClient;

@RestController
public class WaterUtilitySimulatorController{
	
	private static final Log logger = LogFactory.getLog(WaterUtilitySimulatorController.class);

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
		return "Faild to connect";

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

	public static String generateMessage(int dataPointAmount, String attributeName, Tank smartBin, int paramId) {
		String jsonStr = "{\"messageId\":\"" + smartBin.name() + "\",\"body\":[{\"name\":\"" + attributeName + "\","
				+ "\"datapoints\":" + generateDataPoints(dataPointAmount, smartBin, paramId) + ","
				+ "\"attributes\":{\"host\":\"hackathon\",\"partner\":\"TCS\"}}]}";

		logger.info("JSON is" + jsonStr);
		return jsonStr;
	}

	public static String generateDataPoints(int amount, Tank smartBin, int paramId) {
		String[] dataPoints = new String[amount];
		for (int i = 0; i < amount; i++) {
			dataPoints[i] = generateDataPoint(i + 1, smartBin, paramId);
		}
		return Arrays.toString(dataPoints);
	}

	public static String generateDataPoint(int cnt, Tank smartBin, int paramId) {
		long millis = System.currentTimeMillis();
		
		Object value = new Object();
		
		value = generateRandomUsageValue(5, 7.5);
				
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
		for (Tank smartBin : Tank.values()) {
			wsc.sendMessage(generateMessage(10, smartBin.name() + "_phLevel", smartBin, 1));
			logger.info("Height data Ingested " + 10 + " datapoints.");
			
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