package cnrs.mas.demo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import octojus.OctojusNode;
import toools.os.OperatingSystem;
import bigobject.Allocation;
import bigobject.BigObjectCluster;
import cnrs.mas.BigMultiAgentSystem;
import cnrs.mas.MobileAgent;
import cnrs.mas.ThreadedMobileAgent;

@SuppressWarnings("serial")
public class TouringAgent extends ThreadedMobileAgent
{
	OctojusNode startingPoint;
	long startingDate = System.currentTimeMillis();
	List<OctojusNode> notYetVisited = new ArrayList<OctojusNode>();

	public TouringAgent(BigObjectCluster cluster)
	{
		for (OctojusNode node : cluster.getNodes())
		{
			if (node != cluster.getLocalNode())
			{
				System.out.println("adding destination " + node);
				notYetVisited.add(node);
			}
		}
		startingPoint = cluster.getLocalNode();
	}

	@Override
	public void run()
	{
		OctojusNode node = getMAS().getAllocation().getLocalNode();
		System.out.println("**** Arrived at " + node + "   OS:" + OperatingSystem.getLocalOperatingSystem().getClass().getName());

		if (notYetVisited.isEmpty())
		{
			if (node.equals(startingPoint))
			{
				System.out.println("back in " + (System.currentTimeMillis() - startingDate) + "ms");
			}
			else
			{
				migrateTo(startingPoint);
			}
		}
		else
		{
			OctojusNode n = notYetVisited.remove(0);
			migrateTo(n);
		}
	}

	@Override
	public void agentHasJoined(MobileAgent ma)
	{
	}

	@Override
	public void agentHasGone(MobileAgent ma)
	{
	}

	public static void main(String[] args) throws IOException
	{
		BigObjectCluster cluster = BigObjectCluster.localhostCluster(4, true);
		cluster.start();

		Allocation i = new Allocation(cluster);
		BigMultiAgentSystem mas = new BigMultiAgentSystem("test", i);
		TouringAgent a = new TouringAgent(cluster);
		mas.addLocally(a);
	}
}
