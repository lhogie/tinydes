package cnrs.minides;

public class StdOutLogger<S> implements DESListener<S>
{

	@Override
	public void eventJustExecuted(Event<S> e)
	{
		System.out.println("* " + e.getSimulation().getTime() + "\t" + e);
	}
}
