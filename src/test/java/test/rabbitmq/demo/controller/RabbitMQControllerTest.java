package test.rabbitmq.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import test.rabbitmq.demo.notification.Notification;
import test.rabbitmq.demo.service.RabbitMQService;

import javax.servlet.http.HttpServletResponse;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@WebMvcTest(RabbitMQController.class)
class RabbitMQControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    RabbitMQService rabbitMQService;

    @Test
    void shouldReturnOKResponse() throws Exception {
        RequestBuilder request = post("/rabbitmq/test").contentType(MediaType.APPLICATION_JSON).content(asJsonString(new Notification("test", "test", Notification.NotificationPriority.LOW)));
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        assertThat(response.getStatus(),equalTo(HttpServletResponse.SC_OK));
        assertThat(response.getContentAsString(), containsString("Notification sent succesfully"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}