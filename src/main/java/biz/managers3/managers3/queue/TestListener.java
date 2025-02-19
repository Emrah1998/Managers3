package biz.managers3.managers3.queue;

import biz.managers3.managers3.exception.QueueException;
import biz.managers3.managers3.model.response.ChangeManagerStatusDto;
import biz.managers3.managers3.service.ManagersServiceHandle;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class TestListener {
    private final ManagersServiceHandle managersService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "TEST_Q")
    public void consume (String message) {
        try{
            var data = objectMapper.readValue(message, ChangeManagerStatusDto.class);
            managersService.changeManagerStatus(data.getId());
        } catch (JsonProcessingException ex){
            log.error("ActionLog.consume.error message invalid format: {}",message);
        } catch (Exception ex){
            throw new QueueException();
        }
    }
}
