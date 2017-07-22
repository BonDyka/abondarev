package ru.job4j.start;

import java.util.Random;
import java.util.Arrays;
import ru.job4j.models.Item;

/**
 * The class represent container for items.
 *
 * @author abondarev.
 * @since 22.07.2017.
 */
public class Tracker {

	/**
	 * The list of items.
	 */
	private Item[] items = new Item[100];

	/**
	 * The pointer on next empty cell of list of items after last added item.
	 */
	private int position = 0;

	/**
	 * The method adds specified as parameter item to the list of items.
	 * Return Item that was add.
	 *
	 * @param item is item for adding.
	 * @return Item that was add.
	 */
	public Item add(Item item) {
		item.setId(this.generateId());
		items[this.position] = item;
		this.position++;
		return items[this.position - 1];
	}

	/**
	 * The method get item as parameter, searche in list of items na item with
	 * the same id and rewrite on its position specified item.
	 *
	 * @param item is item for replace.
	 */
	public void update(Item item) {
		for (int index = 0; index < this.position; index++) {
			if ((this.items[index].getId()).equals(item.getId())) {
				this.items[index] = item;
				break;
			}
		}
	}

	/**
	 * The method get item as parameter and remove its from list of items if
	 * it exist.
	 *
	 * @param item is item for removing.
	 */
	public void delete(Item item) {
		for (int index = 0; index < this.position; index++) {
			if ((this.items[index].getId()).equals(item.getId())) {
				System.arraycopy(this.items, index + 1, this.items, index,
								 this.position - index - 1);
				this.position--;
				break;
			}
		}
	}

	/**
	 * The method returns list of all items that was contain.
	 *
	 * @return List of all items.
	 */
	public Item[] findAll() {
		return Arrays.copyOf(this.items, this.position);
	}

	/**
	 * The method searche Item by its name (specified as parameter) and return
	 * array of items if it finded. Else return an empty array.
	 *
	 * @param name is name of find out item.
	 * @return array of items with specified name or empty array.
	 */
	public Item[] findByName(String name) {
		Item[] result = new Item[this.position];
		int counter = 0;
		for (int index = 0; index < this.position; index++) {
			if ((this.items[index].getName()).equals(name)) {
				result[index] = this.items[index];
				counter++;
			}
		}
		return Arrays.copyOf(result, counter);
	}

	/**
	 * The method searche Item by its id (specified as parameter) and return
	 * item if it finded. Else return <i>null</i>.
	 *
	 * @param id is id of find out item.
	 * @return item with specified id or null.
	 */
	public Item findById(String id) {
		Item result = null;
		for (Item item : this.items) {
			if (item != null && item.getId().equals(id)) {
				result = item;
				break;
			}
		}
		return result;
	}

	/**
	 * The method generates id for an item.
	 *
	 * @return a string that represent id.
	 */
	private String generateId() {
		Random rn = new Random();
		char[] alphabet = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
						   'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
						   'u', 'v', 'w', 'x', 'y', 'z'};

		int fLetter = rn.nextInt(26);
		int sLetter = rn.nextInt(26);
		int code = rn.nextInt(10_000);

		return String.format("%s%s %s", alphabet[fLetter],
							 alphabet[sLetter], code);
	}
}