package ru.job4j.start;

import ru.job4j.models.Item;
import java.text.DateFormat;

/**
 *
 */
public class StartUI {

	/**
	 * The instance of input system.
	 */
	private Input input;

	/**
	 *
	 */
	private Tracker tracker;

	/**
	 * The condition for exit from application.
	 */
	private boolean exit = false;

	/**
	 * The constructor.
	 *
	 * @param input is an instance of input system.
	 * @param tracker is container under that execute action.
	 */
	public StartUI(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * The enter point.
	 *
	 * @param args list of arguments.
	 */
	public static void main(String[] args) {
		new StartUI(new ConsoleInput(), new Tracker()).init();
	}

	/**
	 * The methos initialaizes main loop of application.
	 */
	public void init() {
		while (!this.exit) {
			this.showMenu();
			String action = this.input.ask("Enter number from menu: ");
			if (this.checkAction(action)) {
				this.executeAction(action);
			} else {
				System.out.println("Bad input.");
			}
		}
	}

	/**
	 * The method outputs the menu into console.
	 */
	private void showMenu() {
		String lineSeparator = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		builder.append("******************************").append(lineSeparator)
			   .append("*            MENU            *").append(lineSeparator)
			   .append("******************************").append(lineSeparator)
			   .append("1. Add item.").append(lineSeparator)
			   .append("2. Show all items.").append(lineSeparator)
			   .append("3. Edit item (required id).").append(lineSeparator)
			   .append("4. Delete item (required id).").append(lineSeparator)
			   .append("5. Find item by Id.").append(lineSeparator)
			   .append("6. Find items by name.").append(lineSeparator)
			   .append("7. Exit.").append(lineSeparator)
			   .append("******************************");
		System.out.println(builder);
	}

	/**
	 * The method checks correct choice menu item.
	 *
	 * @param action is user choice of action.
	 * @return <i>true</i> if action in range of menu.
	 */
	private boolean checkAction(String action) {
		boolean result = false;
		int numberAction = Integer.valueOf(action);
		for (int i = 1; i < 8; i++) {
			if (numberAction == i) {
				result = true;
				break;
			}
		}
		return result;
	}

	/**
	 * The method executes an action from menu.
	 *
	 * @param action is actions choose an user.
	 */
	private void executeAction(String action) {
		switch (action) {
			case "1": this.addAction();
					break;
			case "2": this.showAllAction();
					break;
			case "3": this.editAction();
					break;
			case "4": this.deleteAction();
					break;
			case "5": this.findByIdAction();
					break;
			case "6": this.findByNameAction();
					break;
			case "7": this.exitAction();
					break;
			default:
				break;
		}
	}

	/**
	 * The method executes add action.
	 */
	private void addAction() {
		Item item = this.tracker.add(
							new Item(this.input.ask("Please enter name of item: "),
									 this.input.ask("Please enter description of item: "),
									 System.currentTimeMillis()));

		System.out.println(String.format("Id of your item: %s", item.getId()));
	}

	/**
	 * The method executes edit action.
	 */
	private void editAction() {
		String id = this.input.ask("Please enter yours item Id: ");
		Item existed = this.tracker.findById(id);
		if (existed != null) {
			Item updated = new Item(this.input.ask("Please enter name of item: "),
									this.input.ask("Please enter description of item: "),
									existed.getCreate());
			updated.setId(existed.getId());
			this.tracker.update(updated);
			System.out.println("Operation complite.");
		} else {
			System.out.println("Operation failure! Invalid id!");
		}
	}

	/**
	 * The method executes delete action.
	 */
	public void deleteAction() {
		String id = this.input.ask("Please enter yours item Id: ");
		Item item = this.tracker.findById(id);
		if (item != null) {
			this.tracker.delete(item);
		}
		System.out.println("Operation complite.");
	}

	/**
	 * The method executes show all action.
	 */
	public void showAllAction() {
		Item[] items = this.tracker.findAll();
		if (items.length != 0) {
			for (Item item : items) {
					this.showItem(item);
			}
		} else {
			System.out.println("List is empty");
		}
		System.out.println("Done.");
	}

	/**
	 * The method executes find by id action.
	 */
	public void findByIdAction() {
		String id = this.input.ask("Please enter items Id: ");
		Item item = this.tracker.findById(id);
		if (item != null) {
			this.showItem(item);
		} else {
			System.out.println("Not found");
		}
	}

	/**
	 * The method executes show all action.
	 */
	public void findByNameAction() {
		String name = this.input.ask("Please enter name of item: ");
		Item[] items = this.tracker.findByName(name);
		if (items.length != 0) {
			for (Item item : items) {
					this.showItem(item);
			}
		} else {
			System.out.println("Not found");
		}
		System.out.println("Done.");
	}

	/**
	 * The method executes exit action.
	 */
	public void exitAction() {
		this.exit = true;
	}



	/**
	 * The method output into console data of item.
	 *
	 * @param item is an inctance of item.
	 */
	public void showItem(Item item) {
		String lineSeparator = System.getProperty("line.separator");
		String result = String.format("Item id %s%sName: %s%sDescription: %s%sCreated: %s%s%s",
									  item.getId(), lineSeparator, item.getName(), lineSeparator,
									  item.getDesc(), lineSeparator,
									  DateFormat.getDateInstance().format(item.getCreate()),
									  lineSeparator, lineSeparator);
		System.out.println(result);
	}
}