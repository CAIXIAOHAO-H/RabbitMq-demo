package cwh.mqConsumer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanoutQueueC")
public class FanoutReceiver3 {

    @RabbitHandler
    public void process(Map message){
        System.out.println("扇出消费端3收到"+message.toString());
    }

}
