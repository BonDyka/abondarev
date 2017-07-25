package ru.job4j.start;

import java.text.DateFormat;
import ru.job4j.models.Item;

/**
 * The class represents menu.
 *
 * @author abondarev.
 * @since 24.07.2017.
 */
public class MenuTracker {

	/**
	 * The list of all action in menu.
	 */
	private UserAction[] actions = new UserAction[7];

	/**
	 * The input system instance.
	 */
	private Input input;

	/**
	 * The tracker instance.
	 */
	private Tracker tracker;

	/**
	 * The constructor.
	 *
	 * @param input is an input system instance.
	 * @param tracker is the tracker instance.
	 */
	public MenuTracker(Input input, Tracker tracker) {
		this.input = input;
		this.tracker = tracker;
	}

	/**
	 * The method fill actions of the menu.
	 */
	public void fillActions() {
		this.actions[0] = this.new AddAction();
		this.actions[1] = new MenuTracker.ShowAllAction();
		this.actions[2] = this.new EditAction();
		this.actions[3] = this.new DeleteAction();
		this.actions[4] = this.new FindByIdAction();
		this.actions[5] = new FindByNameAction();
		this.actions[6] = this.new ExitAction();
	}

	/**
	 * The method selects an menu action for execution.
	 *
	 * @param selected is an action key.
	 */
	public void select(int selected) {
		for (UserAction action : this.actions) {
			if (action != null && selected == action.key()) {
				action.execute(this.input, this.tracker);
				break;
			}
		}
	}

	/**
	 * The method shows te menu items for users.
	 */
	public void show() {
		String lineSeparator = System.getProperty("line.separator");
		StringBuilder builder = new StringBuilder();
		builder.append("******************************").append(lineSeparator)
			   .append("*            MENU            *").append(lineSeparator)
			   .append("******************************").append(lineSeparator);
		for (UserAction action : this.actions) {
			if (action != null) {
				builder.append(String.format(
								"%s. %s", action.key(), action.info()))
					   .append(lineSeparator);
			}
		}
		builder.append("******************************").append(lineSeparator);
		System.out.println(builder);
	}

	/**
	 * The class implements UserAction for addition an item.
	 */
	private class AddAction implements UserAction {

		/**
		 * <@inheritedDoc>.
		 *
		 * @return the key of the action.
		 */
		public int key() {
			return 1;
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			Item item = tracker.add(
								new Item(input.ask("Please enter name of item: "),
										 input.ask("Please enter description of item: "),
										 System.currentTimeMillis()
								));

			System.out.println(String.format("Id of your item: %s", item.getId()));
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @return a string that describes the action.
		 */
		public String info() {
			return "Add an item.";
		}
	}

	/**
	 * The class implements UserAction for show all items.
	 */
	private class ShowAllAction implements UserAction {

		/**
		 * <@inheritedDoc>.
		 *
		 * @return the key of the action.
		 */
		public int key() {
			return 2;
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			Item[] items = tracker.findAll();
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
		 * <@inheritedDoc>.
		 *
		 * @return a string that describes the action.
		 */
		public String info() {
			return "Show all items.";
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

	/**
	 * The class implements UserAction for updating an item.
	 */
	private class EditAction implements UserAction {

		/**
		 * <@inheritedDoc>.
		 *
		 * @return the key of the action.
		 */
		public int key() {
			return 3;
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter yours item Id: ");
			Item existed = tracker.findById(id);
			if (existed != null) {
				Item updated = new Item(input.ask("Please enter name of item: "),
										input.ask("Please enter description of item: "),
										existed.getCreate());
				updated.setId(existed.getId());
				tracker.update(updated);
				System.out.println("Operation complite.");
			} else {
				System.out.println("Operation failure! Invalid id!");
			}
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @return a string that describes the action.
		 */
		public String info() {
			return "Edit an items.";
		}
	}

	/**
	 * The class implements UserAction for removing an item.
	 */
	private class DeleteAction implements UserAction {

		/**
		 * <@inheritedDoc>.
		 *
		 * @return the key of the action.
		 */
		public int key() {
			return 4;
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter yours item Id: ");
			Item item = tracker.findById(id);
			if (item != null) {
				tracker.delete(item);
			}
			System.out.println("Operation complite.");
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @return a string that describes the action.
		 */
		public String info() {
			return "Delete an item.";
		}
	}

	/**
	 * The class implements UserAction for searching the item by id.
	 */
	private class FindByIdAction implements UserAction {

		/**
		 * <@inheritedDoc>.
		 *
		 * @return the key of the action.
		 */
		public int key() {
			return 5;
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			String id = input.ask("Please enter items Id: ");
			Item item = tracker.findById(id);
			if (item != null) {
				this.showItem(item);
			} else {
				System.out.println("Not found");
			}
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @return a string that describes the action.
		 */
		public String info() {
			return "Find the item by id.";
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

	/**
	 * The class implements UserAction for exit.
	 */
	private class ExitAction implements UserAction {

		/**
		 * <@inheritedDoc>.
		 *
		 * @return the key of the action.
		 */
		public int key() {
			return 7;
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @param input an input instance.
		 * @param tracker an tracker instance.
		 */
		public void execute(Input input, Tracker tracker) {
			System.out.println("Good bye.");
		}

		/**
		 * <@inheritedDoc>.
		 *
		 * @return a string that describes the action.
		 */
		public String info() {
			return "Exit.";
		}
	}
}

/**
 * The class implements UserAction for searching items by name.
 */
class FindByNameAction implements UserAction {

	/**
	 * <@inheritedDoc>.
	 *
	 * @return the key of the action.
	 */
	public int key() {
		return 6;
	}

	/**
	 * <@inheritedDoc>.
	 *
	 * @param input an input instance.
	 * @param tracker an tracker instance.
	 */
	public void execute(Input input, Tracker tracker) {
		String name = input.ask("Please enter name of item: ");
		Item[] items = tracker.findByName(name);
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
	 * <@inheritedDoc>.
	 *
	 * @return a string that describes the action.
	 */
	public String info() {
		return "Find items by name.";
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