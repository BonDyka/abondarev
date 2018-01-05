package ru.job4j.start;

import ru.job4j.start.store.UserStore;

/**
 *
 */
public class StartUI {

	/**
	 * The variable contain value for exit from app.
	 */
	private static final int EXIT = 7;

	/**
	 * The instance of input system.
	 */
	private Input input;

	/**
	 *
	 */
	private Tracker tracker;

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
		new StartUI(new ValidateInput(), new Tracker(new UserStore())).init();
	}

	/**
	 * The methods initializes main loop of application.
	 */
	public void init() {
		MenuTracker menu = new MenuTracker(this.input, this.tracker);
		menu.fillActions();
		int key = 0;
		while (key != EXIT) {
			menu.show();
			key = this.input.ask("Select: ", MenuTracker.FIRST,	MenuTracker.SIZE);
			menu.select(key);
		}
	}
}