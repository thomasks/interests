package cn.freeexchange.conf;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cn.freeexchange.common.base.event.EventSourcingEnum;

@Configuration
public class RabbitConfig {
	
	@Bean
    public Queue CouponEventQueue() {
        return new Queue(EventSourcingEnum.COUPON_EVENT_SOURCING.getCode(), true); // true表示持久化该队列
    }
}
