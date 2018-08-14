package com.didispace.scca.rest.server;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by David.Liu on 2018/8/7.
 */
@Component
@ConfigurationProperties(prefix = "list")
public class Config {
    private List<String> data;
    private List<Map<String, Object>> data2;
    private List<Map<String, Object>> data3;

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }

    public List<Map<String, Object>> getData2() {
        return data2;
    }

    public void setData2(List<Map<String, Object>> data2) {
        this.data2 = data2;
    }

    public List<Map<String, Object>> getData3() {
        return data3;
    }

    public void setData3(List<Map<String, Object>> data3) {
        this.data3 = data3;
    }
}
