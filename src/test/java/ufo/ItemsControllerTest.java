package ufo;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode= DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
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

        DocumentContext context = JsonPath.parse(itemsResponse.getBody());

        assertThat((Integer) context.read("$.length()")).isEqualTo(1);
        assertThat((String) context.read("$[0].name")).isEqualTo("Crazy Pizza");
    }
}
