package cwh.mqConsumer.mq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver2 {

    @RabbitHandler
    public void hello(String msg, Channel channel, Message message) throws IOException {
        //表明消息已收到
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        System.out.println("this is helloReceiver2"+msg);

    }

}
