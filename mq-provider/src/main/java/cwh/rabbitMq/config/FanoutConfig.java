package cwh.rabbitMq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FanoutConfig {

    @Bean
    public Queue fanoutQueueA(){
        return new Queue("fanoutQueueA");
    }

    @Bean
    public Queue fanoutQueueB(){
        return new Queue("fanoutQueueB");
    }

    @Bean
    public Queue fanoutQueueC(){
        return new Queue("fanoutQueueC");
    }

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanoutExchange");
    }

    @Bean
    public Binding bindingA(){
        return BindingBuilder.bind(fanoutQueueA()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingB(){
        return BindingBuilder.bind(fanoutQueueB()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingC(){
        return BindingBuilder.bind(fanoutQueueC()).to(fanoutExchange());
    }

}
