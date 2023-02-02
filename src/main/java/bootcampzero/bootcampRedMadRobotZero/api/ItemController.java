package bootcampzero.bootcampRedMadRobotZero.api;

import bootcampzero.bootcampRedMadRobotZero.dto.ItemDto;
import bootcampzero.bootcampRedMadRobotZero.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping(value = "/allItems")
    public List<ItemDto> getItems() {
        return itemService.getAllItems();
    }

    @GetMapping(value = "/getItem/{id}")
    public ItemDto getItem(@PathVariable(name = "id") Long id) {
        return itemService.getItemOne(id);
    }

    @PostMapping(value = "/addItem")
    public ItemDto addItem(@RequestBody ItemDto itemDto) {
        return itemService.addOneItem(itemDto);
    }

}
