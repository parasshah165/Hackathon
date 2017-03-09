
package com.tcs.predix.beta.time.series.ingestion.type;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class CargoGatewayType {

    @SerializedName("h_data")
    @Expose
    private HData hData;
    @SerializedName("current_time")
    @Expose
    private Long currentTime;
    @SerializedName("t_data")
    @Expose
    private TData tData;
    @SerializedName("l_data")
    @Expose
    private LData lData;

    /**
     * 
     * @return
     *     The hData
     */
    public HData getHData() {
        return hData;
    }

    /**
     * 
     * @param hData
     *     The h_data
     */
    public void setHData(HData hData) {
        this.hData = hData;
    }

    /**
     * 
     * @return
     *     The currentTime
     */
    public Long getCurrentTime() {
        return currentTime;
    }

    /**
     * 
     * @param currentTime
     *     The current_time
     */
    public void setCurrentTime(Long currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * 
     * @return
     *     The tData
     */
    public TData getTData() {
        return tData;
    }

    /**
     * 
     * @param tData
     *     The t_data
     */
    public void setTData(TData tData) {
        this.tData = tData;
    }

    /**
     * 
     * @return
     *     The lData
     */
    public LData getLData() {
        return lData;
    }

    /**
     * 
     * @param lData
     *     The l_data
     */
    public void setLData(LData lData) {
        this.lData = lData;
    }

}
