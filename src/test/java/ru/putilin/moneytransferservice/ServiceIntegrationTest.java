package ru.putilin.moneytransferservice;


import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import ru.putilin.moneytransferservice.model.Amount;
import ru.putilin.moneytransferservice.model.ConfirmOperation;
import ru.putilin.moneytransferservice.model.Operation;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ServiceIntegrationTest {

    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    final private static GenericContainer<?> moneyTransfer = new GenericContainer<>("transferapp:latest")
            .withExposedPorts(5500);

    @BeforeAll
    public static void setUp() {
        moneyTransfer.start();
    }

    @Order(1)
    @Test
    void testSuccessTransferShouldReturnSuccessOperation() {
        int serverPort = moneyTransfer.getMappedPort(5500);
        Operation operation1 = new Operation("1111222233334444", "11/23", "345", "1234567812345678",
                new Amount(100, "rub"));
        ResponseEntity<String> fullResponse = testRestTemplate.postForEntity("http://localhost:" + serverPort + "/transfer", operation1, String.class);
        HttpStatusCode responseCode = fullResponse.getStatusCode();
        HttpStatusCode expectCode = HttpStatus.OK;
        Assertions.assertEquals(expectCode, responseCode);

        String responseBody = fullResponse.getBody();
        Assertions.assertEquals("{\"operationId\":\"1\"}",responseBody);
    }




    @Test
    void testUnsuccessfulTransferWithWrongCardNumberShouldReturnExceptionMessage() {
        int serverPort = moneyTransfer.getMappedPort(5500);
        Operation operation1 = new Operation("9111222233334444", "11/23", "345", "1234567812345678",
                new Amount(100, "rub"));
        ResponseEntity<String> fullResponse = testRestTemplate.postForEntity("http://localhost:" + serverPort + "/transfer", operation1, String.class);
        String responseBody = fullResponse.getBody();
        MatcherAssert.assertThat(responseBody, containsString("Карты с номером 9111222233334444 не существует\",\"id\":2}"));

    }

    @Test
    void testUnsuccessfulTransferWithWrongCardDateShouldReturnExceptionCode() {
        int serverPort = moneyTransfer.getMappedPort(5500);
        Operation operation1 = new Operation("1111222233334444", "11/24", "345", "1234567812345678",
                new Amount(100, "rub"));
        ResponseEntity<String> fullResponse = testRestTemplate.postForEntity("http://localhost:" + serverPort + "/transfer", operation1, String.class);
        HttpStatusCode responseCode = fullResponse.getStatusCode();
        MatcherAssert.assertThat(responseCode, equalTo(HttpStatus.BAD_REQUEST));
    }


    @Order(2)
    @Test
    void testConfirmOperation() {
        int serverPort = moneyTransfer.getMappedPort(5500);
        ConfirmOperation confirmOperation = new ConfirmOperation("1111");
        ResponseEntity<String> fullResponse = testRestTemplate.postForEntity("http://localhost:" + serverPort + "/confirmOperation", confirmOperation, String.class);
        String bodyResponse = fullResponse.getBody();
        MatcherAssert.assertThat(bodyResponse, containsString("{\"operationId\":\"1"));

        HttpStatusCode statusCode = fullResponse.getStatusCode();
        MatcherAssert.assertThat(statusCode, equalTo(HttpStatus.OK));
    }


}
