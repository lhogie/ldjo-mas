package cnrs.mas.demo;

import java.util.ArrayList;
import java.util.Random;

import cnrs.mas.MobileAgent;
import cnrs.mas.ThreadedMobileAgent;
import octojus.OctojusNode;

public class RandomlyMovingAgent extends ThreadedMobileAgent
{
	int nbMigrations = 0;

	@Override
	public void run()
	{
		++nbMigrations;
		// System.out.println("**** Arrived at " + node);
		OctojusNode n = pickARandomDestinationNode();
		migrateTo(n);
	}

	private OctojusNode pickARandomDestinationNode()
	{
		ArrayList<OctojusNode> nodeList = new ArrayList<>(getAccessibleNodes());
		return nodeList.get(new Random().nextInt(nodeList.size()));
	}

	@Override
	public void agentHasJoined(MobileAgent ma)
	{
	}

	@Override
	public void agentHasGone(MobileAgent ma)
	{
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
