package cwh.mqConsumer.mq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RabbitListener(queues = "fanoutQueueB")
public class FanoutReceiver2 {

    @RabbitHandler
    public void process(Map message){
        System.out.println("扇出消费端2收到"+message.toString());
    }

}
