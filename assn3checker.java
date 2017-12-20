import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class assn3checker
{
	//public String 
	public static void main ( String args [])
	{
		System.out.println("press 0 for random and 1 otherwise:");
		Scanner s = new Scanner(System.in);
		int a = s.nextInt();boolean b;
		if(a==0)
			b=true;
		else
			b=false;
		//System.out.println("Enter the name of the input file:");
		String j;long simtime=0;
		if(b)
			{j = "actionsr.txt";
				System.out.println("Enter simulation time in milli seconds:");
				simtime = s.nextInt();}
		else 
			j = "actions.txt";
		//j = s.nextLine();
		//System.out.println("ruk jaa");
		BufferedReader br = null;
		long strt = System.currentTimeMillis();
		RoutingMapTree r = new RoutingMapTree(strt,b,simtime);
		dashboard d = new dashboard(r,strt,simtime);
		d.start();
		
		try {
			String actionString;
			br = new BufferedReader(new FileReader(j));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}
class dashboard extends Thread
{
	public RoutingMapTree centre;
	public long stime;
	public long ajeebsimtime;
	public dash t;
	public dashboard(RoutingMapTree r,long s,long z)
	{
		centre = r;
		stime = s;
		ajeebsimtime = z;
	}
	
	public void run()
	{
		while((System.currentTimeMillis()-stime)<ajeebsimtime)
		{try{Thread.sleep(5000);}
			catch(Exception e)
			{e.printStackTrace();}
			t = new dash(centre);
		//printtable(centre);
			t.start();
			try
			{t.join();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
class dash extends Thread
{
	public RoutingMapTree centre;
	public dash(RoutingMapTree c)
	{
		centre = c;
	}
	public void printtable(RoutingMapTree centralserver)
	{
		Exchange b = centralserver.root;
		
		
		System.out.print(""+b.identifier+" ");
		node<MobilePhone> a = b.residentset.phoneset.head;
		while(a!=null)
		{
			String k;
			if(a.getdata().busy)
				k="busy";
			else
				k="not busy";
			System.out.print(""+a.getdata().number+":- "+k+" ");
			a = a.getnext();
		}
		System.out.println("");
		
			for(int i=0;i<b.numChildren();i++)
			{
				RoutingMapTree c = b.subtree(i);
				printtable(c);
			}
			
		
	}
	public void run()
	{
		try{new ProcessBuilder("cmd","/c","cls").inheritIO().start().waitFor();}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		printtable(centre);
	}
}
