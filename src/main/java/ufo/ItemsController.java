package ufo;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import ufo.entities.Item;
import ufo.repositories.ItemRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @Autowired
    ItemRepository itemRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody String payload) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        HashMap hashMap = mapper.readValue(payload, HashMap.class);
        String name = (String) hashMap.get("name");

        Item item = new Item(name);

        itemRepository.save(item);

        UriComponents uriComponents =
                fromMethodName(ItemsController.class, "index").build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(){
        Iterable<Item> items = itemRepository.findAll();

        ArrayList<ItemDto> arrayList = new ArrayList<>();
        for (Item item : items) {
            arrayList.add(new ItemDto(item));
        }
        return ResponseEntity.ok(arrayList);
    }
}
