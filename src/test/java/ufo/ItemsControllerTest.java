package ufo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ItemsControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void should_return_ok_when_get_items() throws Exception {
        HashMap payload = new HashMap();
        payload.put("name", "Crazy Pizza");
        ResponseEntity<String> forEntity = this.restTemplate.postForEntity("/items", payload, String.class);
        assertThat(forEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void should_get_created_items() throws Exception {
        HashMap payload = new HashMap();
        payload.put("name", "Crazy Pizza");
        URI uri = this.restTemplate.postForLocation("/items", payload, String.class);
        assertThat(uri).isNotNull();

        ResponseEntity<String> itemsResponse = this.restTemplate.getForEntity(uri, String.class);

        assertThat(itemsResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
}
