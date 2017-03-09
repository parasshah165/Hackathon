
package com.tcs.predix.beta.time.series.ingestion.type;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class TData {

    @SerializedName("temprature")
    @Expose
    private Double temprature;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The temprature
     */
    public Double getTemprature() {
        return temprature;
    }

    /**
     * 
     * @param temprature
     *     The temprature
     */
    public void setTemprature(Double temprature) {
        this.temprature = temprature;
    }

    /**
     * 
     * @return
     *     The name
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    public void setName(String name) {
        this.name = name;
    }

}
