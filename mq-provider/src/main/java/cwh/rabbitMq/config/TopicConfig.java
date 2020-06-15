package cwh.rabbitMq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicConfig {

    private static final String man = "topic.man";
    private static final String woman = "topic.woman";

    @Bean
    public Queue firstQueue(){
        return new Queue(TopicConfig.man);
    }

    @Bean
    public Queue secondQueue(){
        return new Queue(TopicConfig.woman);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange("topicExchange");
    }

    @Bean
    public Binding binding1(){
        return BindingBuilder.bind(firstQueue()).to(topicExchange()).with(man);
    }

    @Bean
    public Binding binding2(){
        return BindingBuilder.bind(secondQueue()).to(topicExchange()).with("topic.#");
    }

}
