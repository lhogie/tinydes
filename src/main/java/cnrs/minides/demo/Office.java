package cnrs.minides.demo;

import java.util.ArrayList;
import java.util.List;

class Office {
	final List<Desk> desks = new ArrayList<Desk>();
	final List<Customer> queue = new ArrayList<Customer>();

	public Office(int numberOfDesks) {
		for (int i = 0; i < numberOfDesks; ++i) {
			desks.add(new Desk());
		}
	}
}