package com.wercher.knowledgebase;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;
import java.util.List;

@RestController
public class HelloController {

	private ItemRepository itemRepository;

	public HelloController(ItemRepository itemRepository) {
		this.itemRepository = itemRepository;
	}

	@GetMapping("/")
	public String index() {
		List<Item> allItems = this.itemRepository.findAll();
		System.out.println(String.format("Number of items: %d", allItems.size()));
		for (Item item: allItems) {
			System.out.println(item.toString());
		}
		return "ok2" + String.join("\n", allItems.stream().map(Item::toString).collect(Collectors.toList()));
	}

}