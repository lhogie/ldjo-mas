package cnrs.mas;

import octojus.OctojusNode;

public abstract class ThreadedMobileAgent extends MobileAgent implements Runnable
{
	transient private Thread thread;
	transient protected boolean requestTermination = false;

	@Override
	public void justArrivedAt(OctojusNode n)
	{
		assert thread != null;
		thread = new Thread(this);
		thread.setName("agent thread " + getClass());
		thread.start();
	}

	@Override
	protected void justParting()
	{
		requestTermination = true;

		try
		{
			thread.join();
		}
		catch (InterruptedException e)
		{
			throw new IllegalStateException(e);
		}
	}
}
