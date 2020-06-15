package cwh.rabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DirectConfig {

    //申明队列
    @Bean
    public Queue directQueue(){
        return new Queue("directQueue",true);
    }

    //申明交换机
    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange("directExchange",true,false);
    }

    //绑定
    @Bean
    public Binding binding(){
        return BindingBuilder.bind(directQueue())
                .to(directExchange())
                .with("directRouting");
    }


}
