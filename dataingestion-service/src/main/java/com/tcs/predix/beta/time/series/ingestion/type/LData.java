
package com.tcs.predix.beta.time.series.ingestion.type;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LData {

    @SerializedName("lang")
    @Expose
    private String lang;
    @SerializedName("latt")
    @Expose
    private String latt;
    @SerializedName("name")
    @Expose
    private String name;

    /**
     * 
     * @return
     *     The lang
     */
    public String getLang() {
        return lang;
    }

    /**
     * 
     * @param lang
     *     The lang
     */
    public void setLang(String lang) {
        this.lang = lang;
    }

    /**
     * 
     * @return
     *     The latt
     */
    public String getLatt() {
        return latt;
    }

    /**
     * 
     * @param latt
     *     The latt
     */
    public void setLatt(String latt) {
        this.latt = latt;
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
