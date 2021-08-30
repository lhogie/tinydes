package cnrs.minides;

import java.util.Comparator;
import java.util.PriorityQueue;

public class EventPriorityQueue<S> implements EventQueue<S>
{
	private final PriorityQueue<Event<S>> q;

	public EventPriorityQueue()
	{
		this.q = new PriorityQueue<Event<S>>(1000, new Comparator<Event<S>>()
		{
			@Override
			public int compare(Event<S> e1, Event<S> e2)
			{
				if (e1.getOccurenceDate() == e2.getOccurenceDate())
					throw new IllegalStateException(
							"events occur at the same date " + e1.getOccurenceDate());

				return Double.compare(e1.getOccurenceDate(), e2.getOccurenceDate());
			}
		});
	}

	@Override
	public void add(Event<S> e)
	{
		q.add(e);
	}

	@Override
	public Event<S> getNextEvent()
	{
		return q.isEmpty() ? null : q.poll();
	}

	@Override
	public int size()
	{
		return q.size();
	}

}