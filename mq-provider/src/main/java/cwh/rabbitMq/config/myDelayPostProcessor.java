package cwh.rabbitMq.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;

/**
 * @ClassName: myMessagePostProcessor
 * @Description: java类作用描述
 * @Author: caiwh
 * @CreateDate: 2020/6/15 15:08
 */
public class myDelayPostProcessor implements MessagePostProcessor {

    private Integer time = 10;

    public myDelayPostProcessor(Integer time) {
        this.time = time;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        MessageProperties messageProperties = message.getMessageProperties();
//            设置编码
        messageProperties.setContentEncoding("utf-8");
//            设置过期时间10*1000毫秒
        messageProperties.setDelay(time*1000);
        return message;
    }
}
