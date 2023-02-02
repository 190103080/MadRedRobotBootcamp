package bootcampzero.bootcampRedMadRobotZero.service.impl;

import bootcampzero.bootcampRedMadRobotZero.dto.ItemDto;
import bootcampzero.bootcampRedMadRobotZero.mapper.ItemMapper;
import bootcampzero.bootcampRedMadRobotZero.models.Item;
import bootcampzero.bootcampRedMadRobotZero.repository.ItemRepository;
import bootcampzero.bootcampRedMadRobotZero.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDto> getAllItems() {
        return itemMapper.toDtoList(getItems());
    }

    @Override
    public ItemDto getItemOne(Long id) {
        return itemMapper.toDto(getItem(id));
    }

    @Override
    public ItemDto addOneItem(ItemDto itemDto) {
        return itemMapper.toDto(addItem(itemMapper.toEntity(itemDto)));
    }

    @Override
    public ItemDto saveOneItem(ItemDto itemDto) {
        return itemMapper.toDto(saveItem(itemMapper.toEntity(itemDto)));
    }

    @Override
    public Item addItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item getCurrentItem() {
        return null;
    }

    @Override
    public Item saveItem(Item item) {
        Item itemCheck = itemRepository.findById(item.getId()).orElse(null);

        if (itemCheck != null) {
            itemCheck.setName(item.getName());
            itemCheck.setDescription(item.getDescription());
            itemCheck.setPrice(item.getPrice());
            itemCheck.setImage(item.getImage());
            itemCheck.setActive(true);
        }

        return itemRepository.save(item);
    }

    @Override
    public Item getItem(Long id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Item> getItems() {
        return itemRepository.findAll();
    }

    @Override
    public void deleteItem(Long id) {
        itemRepository.deleteById(id);
    }
}
