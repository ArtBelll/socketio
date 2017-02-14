package com.art;

import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

import java.io.IOException;

/**
 * Created by Artur Belogur on 06.02.17.
 */
public class Publisher {

    private RTopic<Integer> channel;

    public Publisher() throws IOException {
        final RedissonClient redisson = Redisson.create(RedissonHelper.getRedissonConfig());
        channel = redisson.getTopic(RedissonHelper.COUNTER_CHANNEL);
    }

    public RTopic<Integer> getChannel() {
        return channel;
    }
}
