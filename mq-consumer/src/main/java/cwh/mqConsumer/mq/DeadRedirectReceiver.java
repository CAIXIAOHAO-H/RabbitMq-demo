package cwh.mqConsumer.mq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
@RabbitListener(queues = "deadRedirectQueue")
public class DeadRedirectReceiver {

    @RabbitHandler
    public void process(Map msg, Channel channel, Message message) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("死信重定向消费端收到"+msg.toString());
    }

}
