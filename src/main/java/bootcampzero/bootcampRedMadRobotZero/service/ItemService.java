package bootcampzero.bootcampRedMadRobotZero.service;

import bootcampzero.bootcampRedMadRobotZero.dto.ItemDto;
import bootcampzero.bootcampRedMadRobotZero.models.Item;

import java.util.List;

public interface ItemService {

    Item addItem(Item item);
    Item saveItem(Item item);
    Item getItem(Long id);
    List<Item> getItems();
    void deleteItem(Long id);

    Item getCurrentItem();

    List<ItemDto> getAllItems();
    ItemDto getItemOne(Long id);
    ItemDto addOneItem(ItemDto itemDto);
    ItemDto saveOneItem(ItemDto itemDto);

}
