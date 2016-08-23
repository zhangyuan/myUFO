package ufo.repositories;

import org.springframework.data.repository.CrudRepository;
import ufo.entities.Item;

public interface ItemRepository extends CrudRepository<Item, Long>{
}
