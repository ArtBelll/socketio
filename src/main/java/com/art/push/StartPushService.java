package com.art.push;

import com.art.RedissonHelper;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketConfig;
import com.corundumstudio.socketio.SocketIOServer;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.File;
import java.io.IOException;

/**
 * Created by Artur Belogur on 04.02.17.
 */
@Slf4j
public class StartPushService {

    public static void main(String[] args) throws IOException {
        Configuration conf = new Configuration();
        conf.setHostname("192.168.1.113");
        conf.setPort(8999);
        final SocketConfig socketConfig = new SocketConfig();
        socketConfig.setReuseAddress(true);
        conf.setSocketConfig(socketConfig);
        PushService pushService = new PushService(conf);

        final RedissonClient redisson = Redisson.create(RedissonHelper.getRedissonConfig());
        new Subscriber(pushService, redisson);
    }
}
