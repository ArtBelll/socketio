package com.art.push;

import com.art.RedissonHelper;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;

/**
 * Created by Artur Belogur on 06.02.17.
 */
public class Subscriber {

    private PushService pushService;

    private RTopic<Integer> channel;

    public Subscriber(PushService pushService, RedissonClient redisson) {
        this.pushService = pushService;
        this.channel = redisson.getTopic(RedissonHelper.COUNTER_CHANNEL);
        addSubscribe();
    }

    private void addSubscribe() {
        channel.addListener((ch, msg) -> pushService.updateCounter(msg));
    }
}
