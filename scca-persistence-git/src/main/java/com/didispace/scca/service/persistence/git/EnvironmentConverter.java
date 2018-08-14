package com.didispace.scca.service.persistence.git;

import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;

import java.util.*;

/**
 * 环境配置转换器
 *
 * Created by David.Liu on 2018/8/8.
 */
public class EnvironmentConverter {

    public static Map<String, Object> convert(Environment profiles, String propertiesFile) {
        // Map of unique keys containing full map of properties for each unique
        // key
        Map<String, Map<String, Object>> map = new LinkedHashMap<>();
        List<PropertySource> sources = new ArrayList<>(profiles.getPropertySources());
        Collections.reverse(sources);
        Map<String, Object> combinedMap = new TreeMap<>();
        for (PropertySource source : sources) {

            if (!source.getName().endsWith(propertiesFile)) {
                continue;
            }

            Map<String, Object> value = (Map<String, Object>) source.getSource();
            for (String key : value.keySet()) {

                if (!key.contains("[")) {

                    // Not an array, add unique key to the map
                    combinedMap.put(key, value.get(key));

                }
                else {

                    // An existing array might have already been added to the property map
                    // of an unequal size to the current array. Replace the array key in
                    // the current map.
                    key = key.substring(0, key.indexOf("["));
                    Map<String, Object> filtered = new TreeMap<>();
                    for (String index : value.keySet()) {
                        if (index.startsWith(key + "[")) {
                            filtered.put(index, value.get(index));
                        }
                    }
                    System.out.println("key: " + key + ", filterd: " + filtered);
                    map.put(key, filtered);
                }
            }

        }

        // Combine all unique keys for array values into the combined map
        for (Map.Entry<String, Map<String, Object>> entry : map.entrySet()) {
            combinedMap.putAll(entry.getValue());
        }

        postProcessProperties(combinedMap);
        return combinedMap;
    }

    private static void postProcessProperties(Map<String, Object> propertiesMap) {
        for (Iterator<String> iter = propertiesMap.keySet().iterator(); iter.hasNext();) {
            String key = iter.next();
            if (key.equals("spring.profiles")) {
                iter.remove();
            }
        }
    }

}
