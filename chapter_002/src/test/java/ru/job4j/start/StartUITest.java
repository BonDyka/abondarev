package ru.job4j.start;

import ru.job4j.models.Item;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The class created for testing behaviour of StartUI class.
 *
 * @author abondarev
 * @since 23.07.2017.
 */
public class StartUITest {

	/**
	 * The test for adding item from user.
	 */
	@Test
	public void whenUserAddItemThenTrackerHasItemWithTheSameName() {
		Tracker tracker = new Tracker();
		// Stub for input system.
		Input input = new StubInput(new String[] {"1", "test", "test desc", "7"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findAll()[0].getName(), is("test"));
	}

	/**
	 * The test for edit item added before from user.
	 */
	@Test
	public void whenUserUpdateItemThenTrackerHasUpdatedValue() {
		Tracker tracker = new Tracker();
		//Added item cleary.
		Item item = tracker.add(new Item("test", "test desc", System.currentTimeMillis()));
		// Stub for input system.
		Input input = new StubInput(new String[] {"3", item.getId(), "test2", "test desc 2", "7"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findById(item.getId()).getName(), is("test2"));
	}

	/**
	 * The test for edit item added before from user.
	 */
	@Test
	public void whenUserDeleteItemThenTrackerHasNoItem() {
		Tracker tracker = new Tracker();
		//Added item cleary.
		Item item = tracker.add(new Item("test", "test desc", System.currentTimeMillis()));
		// Stub for input system.
		Input input = new StubInput(new String[] {"4", item.getId(), "7"});
		new StartUI(input, tracker).init();
		assertThat(tracker.findAll().length, is(0));
	}
}