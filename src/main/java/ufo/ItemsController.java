package ufo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;

import static org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder.fromMethodName;

@RestController
@RequestMapping("/items")
public class ItemsController {
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(){
        UriComponents uriComponents =
                fromMethodName(ItemsController.class, "index").build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity index(){
        ResponseEntity responseEntity = new ResponseEntity(HttpStatus.OK);
        return responseEntity;
    }
}
