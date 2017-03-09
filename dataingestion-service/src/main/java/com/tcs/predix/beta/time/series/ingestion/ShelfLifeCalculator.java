package com.tcs.predix.beta.time.series.ingestion;

import com.tcs.predix.beta.time.series.ingestion.type.CargoGatewayType;

public class ShelfLifeCalculator {

	public static long calculateOnePointShelfLife(CargoGatewayType type) {

		double intercept = -4.0391353;
		double temperature_coef = 0.1325957;
		double humidity_coef = -0.0254720;
		double o2Composition_coef = -0.0207994;
		double ethylene_coef = 0.0001257;
		
		

		double temperature = type.getTData().getTemprature();

		temperature = ((temperature - 32) * 5) / 9;

		double humidity = type.getHData().getHumidity();


		double ethylene = 9;

		double shelfLife1byY = intercept + temperature * temperature_coef + humidity * humidity_coef +

		23 * o2Composition_coef + ethylene * ethylene_coef;

		Double shelfLife = Math.exp(-1.0 * shelfLife1byY);

		return shelfLife.longValue();
	}

}
