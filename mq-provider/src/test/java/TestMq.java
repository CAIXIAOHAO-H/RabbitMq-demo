import cwh.rabbitMq.rabbitMqApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = rabbitMqApplication.class )
public class TestMq {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testMq(){
        Map<String,Object> map = new HashMap<>();
        map.put("now",new Date());
        rabbitTemplate.convertAndSend("directExchange","directRouting",map);
    }

}
