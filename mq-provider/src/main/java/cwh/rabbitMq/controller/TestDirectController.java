package cwh.rabbitMq.controller;

import cwh.rabbitMq.config.DeadLetterConfig;
import cwh.rabbitMq.config.DelayConfig;
import cwh.rabbitMq.config.myDelayPostProcessor;
import cwh.rabbitMq.config.myMessagePostProcessor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/send")
public class TestDirectController {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @GetMapping("/hello")
    public String hello(){
        String hello = "send a msg";
        rabbitTemplate.convertAndSend("hello",hello);
        return "ok";
    }

    @GetMapping("/direct")
    public String direct(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        rabbitTemplate.convertAndSend("directExchange","directRouting",map);
        return "ok";
    }

    @GetMapping("/topic1")
    public String topic1(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是topic消息man");
        rabbitTemplate.convertAndSend("topicExchange","topic.man",map);
        return "ok";
    }

    @GetMapping("/topic2")
    public String topic2(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是topic消息woman");
        rabbitTemplate.convertAndSend("topicExchange","topic.woman",map);
        return "ok";
    }

    @GetMapping("/fanout1")
    public String fanout1(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是fanout1消息");
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return "ok";
    }

    @GetMapping("/fanout2")
    public String fanout2(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是fanout2消息");
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return "ok";
    }

    @GetMapping("/fanout3")
    public String fanout3(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是fanout3消息");
        rabbitTemplate.convertAndSend("fanoutExchange",null,map);
        return "ok";
    }

    @GetMapping("/deadLetter")
    public String deadLetter(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是deadLetter消息");
        rabbitTemplate.convertAndSend(DeadLetterConfig.DEAD_LETTER_EXCHANGE,DeadLetterConfig.DEAD_LETTER_ROUTING_KEY,map);
        return "ok";
    }

    @GetMapping("/ttlDeadLetter")
    public String ttlDeadLetter(long time){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是ttlDeadLetter"+time+"消息");
        myMessagePostProcessor processor = new myMessagePostProcessor(time);
        rabbitTemplate.convertAndSend(DeadLetterConfig.DEAD_LETTER_EXCHANGE,DeadLetterConfig.DEAD_LETTER_ROUTING_KEY,map,processor);
        return "ok";
    }

    @GetMapping("/ttlDeadLetter50")
    public String ttlDeadLetter15(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是ttlDeadLetter15消息");
        myMessagePostProcessor processor = new myMessagePostProcessor(50);
        rabbitTemplate.convertAndSend(DeadLetterConfig.DEAD_LETTER_EXCHANGE,DeadLetterConfig.DEAD_LETTER_ROUTING_KEY,map,processor);
        return "ok";
    }

    @GetMapping("/delay")
    public String delay(Integer time){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        map.put("msg","推送这是时间为"+time+"的delay消息");
        rabbitTemplate.convertAndSend(DelayConfig.DELAY_EXCHANGE_NAME,DelayConfig.DELAY_ROUTING_KEY,map,new myDelayPostProcessor(time));
        return "ok";
    }


}
