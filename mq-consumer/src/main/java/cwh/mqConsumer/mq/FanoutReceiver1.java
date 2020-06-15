package cwh.mqConsumer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanoutQueueA")
public class FanoutReceiver1 {

    @RabbitHandler
    public void process(Map message){
        System.out.println("扇出消费端1收到"+message.toString());
    }

}
