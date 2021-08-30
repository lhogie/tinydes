package cnrs.minides;

public interface EventQueue<S>
{
	void add(Event<S> e);

	Event<S> getNextEvent();

	int size();
}