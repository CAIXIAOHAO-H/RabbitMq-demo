package cwh.rabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DeadLetterConfig {

    public static String DEAD_LETTER_EXCHANGE = "deadLetterExchange";
    public static String DEAD_LETTER_QUEUE = "deadLetterQueue";
    public static String REDIRECT_QUEUE = "deadRedirectQueue";

    public static String DEAD_LETTER_REDIRECT_ROUTING_KEY = "deadRedirectRoutingKey";
    public static String DEAD_LETTER_ROUTING_KEY ="deadRoutingKey";

    //申明交换机
    @Bean
    public DirectExchange deadLetterExchange(){
        return ExchangeBuilder.directExchange(DEAD_LETTER_EXCHANGE).durable(true).build();
    }

    //申明死信队列
    @Bean
    public Queue deadLetterQueue(){
        Map<String,Object> args = new HashMap<>();
        args.put("x-dead-letter-exchange",DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key",DEAD_LETTER_REDIRECT_ROUTING_KEY);
        return QueueBuilder.durable(DEAD_LETTER_QUEUE).withArguments(args).build();
    }

    //申明重定向队列
    @Bean
    public Queue redirectQueue(){
        return QueueBuilder.durable(REDIRECT_QUEUE).build();
    }

    //绑定死信队列到死信交换机上
    @Bean
    public Binding bindingDead(){
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(DEAD_LETTER_ROUTING_KEY);
    }

    //绑定重定向队列到死信交换机上
    @Bean
    public Binding bindingRedirect(){
        return BindingBuilder.bind(redirectQueue()).to(deadLetterExchange()).with(DEAD_LETTER_REDIRECT_ROUTING_KEY);
    }

}
