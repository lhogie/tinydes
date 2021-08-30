package cnrs.minides;

import toools.collections.ArrayFIFO;

public class FIFOEventQueue<S> extends ArrayFIFO<Event<S>> implements EventQueue<S>
{

	@Override
	public void add(Event<S> e)
	{
		super.push(e);
	}

	@Override
	public Event<S> getNextEvent()
	{
		return super.extract();
	}
}