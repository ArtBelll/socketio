package com.art;

import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * Created by Artur Belogur on 06.02.17.
 */
public class RedissonHelper {

    public static final String COUNTER_CHANNEL = "counter";

    private static final String REDISSON_CONFIG = "/tmp/redisson.json";

    public static Config getRedissonConfig() throws IOException {
        final String config = System.getenv("REDIS_CONFIG");
        if (config != null) {
            int i = config.lastIndexOf('.');
            final String ext = i > 0 ? config.substring(i + 1) : null;
            if (ext != null && ext.toLowerCase().equals("yaml"))
                return Config.fromYAML(new File(config));
            return Config.fromJSON(new File(config));
        }
        return Config.fromJSON(new File(REDISSON_CONFIG));
    }
}
