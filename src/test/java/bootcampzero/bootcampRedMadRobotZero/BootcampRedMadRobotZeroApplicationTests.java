package bootcampzero.bootcampRedMadRobotZero;

import bootcampzero.bootcampRedMadRobotZero.dto.ItemDto;
import bootcampzero.bootcampRedMadRobotZero.dto.UserDto;
import bootcampzero.bootcampRedMadRobotZero.mapper.ItemMapper;
import bootcampzero.bootcampRedMadRobotZero.models.Item;
import bootcampzero.bootcampRedMadRobotZero.models.User;
import bootcampzero.bootcampRedMadRobotZero.service.ItemService;
import bootcampzero.bootcampRedMadRobotZero.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class BootcampRedMadRobotZeroApplicationTests {

	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private ItemService itemService;

	@Autowired
	private UserService userService;

	@Test
	void testItemMapper() {

		ItemDto itemDto = new ItemDto();
		itemDto.setName("New Item");
		itemDto.setId(8L);
		itemDto.setActive(true);
		itemDto.setDescription("New Item for new home");
		itemDto.setPrice(8888);

		Item item = itemMapper.toEntity(itemDto);

		Assertions.assertEquals("New Item", item.getName());
		Assertions.assertEquals(8L, item.getId());
		Assertions.assertEquals(8888, item.getPrice());
		Assertions.assertEquals("New Item for new home", item.getDescription());

	}

	@Test
	void testItemInsert() {
		ItemDto itemDto = new ItemDto();
		Long id = 2L;
		itemDto.setPrice(9999);
		itemDto.setDescription("Hello");
		itemDto.setName("Hi");
		itemDto.setActive(true);
		itemDto.setImage("88dd24a14ffb3e2544ab29af25836bbe89248139.png");


		ItemDto result = itemService.addOneItem(itemDto);
		Assertions.assertNotEquals(null, result.getId());
		Assertions.assertNotEquals(0, result.getPrice());
		Assertions.assertEquals("Hello", result.getName());
		Assertions.assertEquals(true, result.isActive());
		Assertions.assertEquals("Hi", result.getDescription());
		Assertions.assertEquals("88dd24a14ffb3e2544ab29af25836bbe89248139.png", result.getImage());

		itemService.deleteItem(result.getId());

	}

}
