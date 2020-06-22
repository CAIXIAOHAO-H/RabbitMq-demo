package cwh.rabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayConfig {
    public static String DELAY_QUEUE_NAME = "delayQueueName";
    public static String DELAY_EXCHANGE_NAME = "delayExchangeName";
    public static String DELAY_ROUTING_KEY = "delayRoutingKey";

    @Bean
    public Queue delayQueue(){
        return new Queue(DELAY_QUEUE_NAME);
    }

    @Bean
    public CustomExchange delayExchange(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(DELAY_EXCHANGE_NAME,"x-delayed-message",true,false,args);
    }

    @Bean
    public Binding bindingNotify(){
        return BindingBuilder.bind(delayQueue()).to(delayExchange()).with(DELAY_ROUTING_KEY).noargs();
    }


}
