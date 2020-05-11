package cnrs.mas.demo;

import cnrs.mas.MobileAgent;
import octojus.OctojusNode;

public class ObservingAgent extends MobileAgent
{

	@Override
	public void agentHasJoined(MobileAgent ma)
	{
		System.out.println("agent " + ma + " joins!");
	}

	@Override
	public void agentHasGone(MobileAgent ma)
	{
		System.out.println("agent " + ma + " parted.");
	}

	@Override
	public void justArrivedAt(OctojusNode localNode)
	{
	}

	@Override
	protected void justParting()
	{
	}
}
