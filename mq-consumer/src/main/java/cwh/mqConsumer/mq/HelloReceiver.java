package cwh.mqConsumer.mq;


import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "hello")
public class HelloReceiver {

    @RabbitHandler
    public void hello(String msg, Channel channel, Message message) throws IOException {
        try {
            int test = 5/0;
            System.out.println("this is helloReceiver"+msg);
            //表明消息已收到
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        }
        catch (Exception e){
            //返回消息
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,true);
//            channel.basicReject(message.getMessageProperties().getDeliveryTag(),true);
            System.out.println("消息重新回到队列");
        }

    }

}
