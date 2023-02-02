package bootcampzero.bootcampRedMadRobotZero.mapper;

import bootcampzero.bootcampRedMadRobotZero.dto.ItemDto;
import bootcampzero.bootcampRedMadRobotZero.models.Item;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemDto toDto(Item item);
    Item toEntity(ItemDto itemDto);

    List<ItemDto> toDtoList(List<Item> itemList);
    List<Item> toEntityList(List<ItemDto> itemDtoList);

}
