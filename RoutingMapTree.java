public class RoutingMapTree
{
	public Exchange root= new Exchange(0);
	public MobilePhoneSet fullset =new MobilePhoneSet();
	public long starttime;
	public boolean israndom;
	public long simtime;
	//public queimp mobfns = new queimp();
	public RoutingMapTree(long b,boolean z,long s) 
	{
		starttime = b;
		israndom = z;
		simtime = s;
	}
	public RoutingMapTree(int i)
	{
		root.identifier = (i);
	}
	public RoutingMapTree(Exchange a)
	{
		root = a;
	}
	public boolean containsNode(Exchange a)
	{
		boolean flag = false;
		Exchange b = root;
		if(b.identifier == a.identifier)
			flag = true;
		else
		{
			for(int i=0;i<b.numChildren();i++)
			{
				RoutingMapTree c = b.subtree(i);
				flag = (flag||(c.containsNode(a)));
				if(flag)
					break;
			}
			
		}
		return flag;
	}
	public MobilePhone throwrandomphone()
	{
		int i3 = 1+(int)(Math.random()*9);
		node<MobilePhone> a = fullset.phoneset.head;
		for(int j=1;j<i3;j++)
		{
			a = a.getnext();
		}
		return a.getdata();
	}
	public boolean containsNode(int a)
	{
		boolean flag = false;
		Exchange b = root;
		if(b.identifier == a)
			flag = true;
		else
		{
			for(int i=0;i<b.numChildren();i++)
			{
				RoutingMapTree c = b.subtree(i);
				flag = (flag||(c.containsNode(a)));
				if(flag)
					break;
			}
			
		}
		return flag;
	}
	public Exchange throwNode(int a)
	{
		Exchange d;
		//boolean flag = false;
		Exchange b = root;
		if(b.identifier == a)
			return b;
		
		else
		{
			for(int i=0;i<b.numChildren();i++)
			{
				RoutingMapTree c = b.subtree(i);
				if(c.containsNode(a))
				{d = c.throwNode(a);
					return d;}
			}
			Exchange f = new Exchange(-1);
			return f;
		}
	}
	/*public void switchOn(MobilePhone a, Exchange b)
	{
		a.SwitchOn(this,b);
		/*a.location = b;
		
		Exchange c = b;
		while(c!=null)
		{
			c.residentset.insert(a);
			c = c.parent;
		}s
		
	}*/
	/*public void switchOff(MobilePhone a)
	{
		a.SwitchOff(this);
		/*Exchange c = a.location;
		while(c!=null)
		{
			c.residentset.delete(a);
			c = c.parent;
		}
	}*/
	public Exchange findPhone(MobilePhone m)
	{
		try
		{
			if (!fullset.IsMember(m))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("ERROR: MobilePhone with identifier" + " "+ m.number+" was not found");
		}
		try
		{
			if(!root.residentset.IsMember(m))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("ERROR: MobilePhone with identifier" + " "+m.number+" is switched off");
		}		
		return m.location;
	}
	public Exchange findPhone(int m)
	{
		MobilePhone mob = root.residentset.throwphone(m);
		return mob.location;
	}
	public Exchange lowestRouter(Exchange a,Exchange b)
	{
		if(a.equals(b))
			return a;
		else
		{
			Exchange c = a;
			while(!(c.subtree().containsNode(b)))
			{
				c = c.parent;
			}
			return c;
		}
	}
	public Exchange lowestRouter(int a,int b)
	{
		if(a==b)
			return this.throwNode(a);
		else
		{
			Exchange c = this.throwNode(a);
			while(!(c.subtree().containsNode(b)))
			{
				c = c.parent;
			}
			return c;
		}
	}
	public ExchangeList routeCall(MobilePhone a,MobilePhone b)
	{
		Exchange fst = a.location;
		Exchange snd = b.location;
		Exchange lowest = this.lowestRouter(fst.identifier,snd.identifier);
		ExchangeList route = new ExchangeList();
		while(fst.identifier!=lowest.identifier)
		{
			route.insert(fst);
			fst = fst.parent;
		}
		while(fst.identifier!=snd.identifier)
		{
			route.insert(fst);
			for(int i=0;i<fst.numChildren();i++)
			{
				RoutingMapTree r = fst.subtree(i);
				if(r.containsNode(snd.identifier))
					{fst = fst.child(i);break;}

			}
		}
		route.insert(fst);
		return route;
	}
	public ExchangeList routeCall(int a,int b)
	{
		Exchange fst = root.residentset.throwphone(a).location;
		Exchange snd = root.residentset.throwphone(b).location;
		Exchange lowest = this.lowestRouter(fst.identifier,snd.identifier);
		ExchangeList route = new ExchangeList();
		while(fst.identifier!=lowest.identifier)
		{
			route.insert(fst);
			fst = fst.parent;
		}
		while(fst.identifier!=snd.identifier)
		{
			route.insert(fst);
			for(int i=0;i<fst.numChildren();i++)
			{
				RoutingMapTree r = fst.subtree(i);
				if(r.containsNode(snd.identifier))
					{fst = fst.child(i);break;}

			}
		}
		route.insert(fst);
		return route;
	}
	/*public void movePhone(MobilePhone a, Exchange b)
	{
		MobilePhone mob = a;
		mob.SwitchOff(this);
		Exchange locals = b;
		mob.SwitchOn(this,b.identifier);
	}
	/*public void movePhone(int a, int b)
	{
		MobilePhone mob = root.residentset.throwphone(a);
		mob.SwitchOff(this);
		//Exchange locals = this.throwNode(b);
		mob.SwitchOn(this,b);
	}*/

	public void performAction(String actionMessage) 
	{
		//System.out.println(actionMessage);
		
			String[] inputs = actionMessage.split("\\s+");
			String a = inputs[1];
			long b = (System.currentTimeMillis() - starttime);
			long timest = Long.parseLong(inputs[0]);
			//queries function;
			if(timest>b)
				{try{Thread.sleep(timest-b);}
					catch ( InterruptedException e){e.printStackTrace();
								//System.out.println("h");
							}}
							long c = (System.currentTimeMillis() - starttime);
			
			if(a.equals("addExchange"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				
				Exchange newex = new Exchange(i2);
				try{
				if(!this.containsNode(i1))
					throw new Exception();}
				catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i1+" not present");
		}	
				if(this.containsNode(i1))
				
					{this.throwNode(i1).children.insert(newex);
					newex.parent = this.throwNode(i1);
				
				System.out.println(actionMessage+" "+c);}
				
			}
			else if(a.equals("switchOnMobile"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				try{if(!this.containsNode(i2))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i2+" not present");
		}	
		if(this.containsNode(i2))
			{	if(!fullset.IsMember(i1))
				{
					MobilePhone newphone = new MobilePhone(i1,this,starttime,israndom,simtime);
					/*newphone.location = this.throwNode(i2);
					this.switchOn(newphone,newphone.location);*/
					newphone.threadfns.enqueue(inputs);
					newphone.SwitchOn(this,i2);
					//newphone.start();
					Thread t1 = new Thread(newphone);
					t1.start();
					/*try
					{
						t1.join();
					}
					catch(InterruptedException ie)
					{
						System . out . println (ie );
						 ie. printStackTrace ();
					}*/
					this.fullset.insert(newphone);
					System.out.println(actionMessage+" "+c);
				}
				else if((fullset.IsMember(i1))&&(!root.residentset.IsMember(i1)))
				{
					MobilePhone newphone = fullset.throwphone(i1);
					newphone.threadfns.enqueue(inputs);
					Thread t1 = new Thread(newphone);
					//if(!newphone.isAlive())
					//{
						t1.start();
						try
					{
						t1.join();
					}
					catch(InterruptedException ie)
					{
						System . out . println (ie );
						 ie. printStackTrace ();
					}
					System.out.println(actionMessage+" "+c);
					//}
				}
				else 
				{
					System.out.println(actionMessage+" "+i1+" is already switched on");
				}
				}
			}
			else if(a.equals("switchOffMobile"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				try{if(!fullset.IsMember(i1))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: mobile with identifier "+i1+" not present");
		}	
			if(fullset.IsMember(i1))
				{if(root.residentset.IsMember(i1))
					{MobilePhone phone = root.residentset.throwphone(i1);
						phone.threadfns.enqueue(inputs);
					//if(!phone.isAlive())
						//{
						Thread t1 =new Thread(phone);
							t1.start();
					try
					{
						t1.join();
					}
					catch(InterruptedException ie)
					{
						System . out . println (ie );
						 ie. printStackTrace ();
					}
					//System.out.println
						//}
				}
				
				System.out.println(actionMessage+" "+c);}
			}
			else if(a.equals("queryNthChild"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				try{if(!this.containsNode(i1))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i1+" not present");
		}	
				
				if(this.containsNode(i1))
					{Exchange ex = this.throwNode(i1);
					//System.out.println(""+ex.numChildren());
					try{if(ex.numChildren()-1<i2)
						throw new Exception();
					System.out.println(actionMessage+": "+ex.child(i2).identifier+" "+c);}
						catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i1+" does not have so many children");
		}	
					}
				
			}
			else if(a.equals("queryMobilePhoneSet"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				try{if(!this.containsNode(i1))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i1+" not present");
		}	
		if(this.containsNode(i1))
				{Exchange ex = this.throwNode(i1);
				System.out.print(actionMessage+":");
				node<MobilePhone> anode = ex.residentset.phoneset.head;
				while(anode!=null)
				{
					System.out.print(" "+ anode.getdata().number);
					anode = anode.getnext();
				}
				System.out.println(" "+c);}
			}
			else if(a.equals("queryFindPhone"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				//System.out.print(actionMessage+" ");
		try
		{
			if (!fullset.IsMember(i1))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" was not found");
		}
		try
		{
			if((!root.residentset.IsMember(i1))&&(fullset.IsMember(i1)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" is switched off");
		}
				if((root.residentset.IsMember(i1)))
				{Exchange local = this.findPhone(i1);
				System.out.println(actionMessage+": "+local.identifier+" "+c);}
			}
			else if(a.equals("queryLowestRouter"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				try{
				if(!this.containsNode(i1))
					throw new Exception();}
				catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i1+" not present");
		}	
			try{
				if(!this.containsNode(i2))
					throw new Exception();}
				catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i2+" not present");
		}	
		if((this.containsNode(i2))&&(this.containsNode(i1)))
			{Exchange lowest = this.lowestRouter(i1,i2);
			System.out.println(actionMessage+": "+lowest.identifier+" "+c);}
			}
			else if(a.equals("queryFindCallPath"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				try
		{
			if (!fullset.IsMember(i1))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" was not found");
		}
		try
		{
			if((!root.residentset.IsMember(i1))&&(fullset.IsMember(i1)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" is switched off");
		}
		try
		{
			if (!fullset.IsMember(i2))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i2+" was not found");
		}
		try
		{
			if((!root.residentset.IsMember(i2))&&(fullset.IsMember(i2)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i2+" is switched off");
		}
		if((root.residentset.IsMember(i2))&&(root.residentset.IsMember(i1)))
		{System.out.print(actionMessage+"");
		ExchangeList path = this.routeCall(i1,i2);
		stone step = path.head;
		while(step!=null)
		{
			System.out.print(" "+step.data.identifier);
			step = step.next;
		}
		System.out.println(" "+c);}


			}
			else if(a.equals("movePhone"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				try
		{
			if (!fullset.IsMember(i1))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" was not found");
		}
		try
		{
			if((!root.residentset.IsMember(i1))&&(fullset.IsMember(i1)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" is switched off");
		}
		try{if(!this.containsNode(i2))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i2+" not present");
		}	
		if((root.residentset.IsMember(i1))&&(this.containsNode(i2)))
		{
			MobilePhone mob = root.residentset.throwphone(i1);
			mob.threadfns.enqueue(inputs);
			//mob.movephone(this,i2);
			Thread t1 = new Thread(mob);
			t1.start();
			try
					{
						t1.join();
					}
					catch(InterruptedException ie)
					{
						System . out . println (ie );
						 ie. printStackTrace ();
					}
			System.out.println(actionMessage+" "+c);
		}

			}
			else if(a.equals("routeCall"))
			{
				int i1 = Integer.parseInt(inputs[2]);
				int i2 = Integer.parseInt(inputs[3]);
				//System.out.println("h");
				try{
			if (!fullset.IsMember(i1))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" was not found");
		}
		try
		{
			if((!root.residentset.IsMember(i1))&&(fullset.IsMember(i1)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" is switched off");
		}
		try
		{
			if (!fullset.IsMember(i2))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i2+" was not found");
		}
		try
		{
			if((!root.residentset.IsMember(i2))&&(fullset.IsMember(i2)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i2+" is switched off");
		}
		if((root.residentset.IsMember(i1))&&(root.residentset.IsMember(i2)))
		{
			MobilePhone mob1 = root.residentset.throwphone(i1);
			MobilePhone mob2 = root.residentset.throwphone(i2);
			try
			{
				if(mob1.busy)
					throw new Exception();
			}
			catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i1+" is busy");
		}

		try
			{
				if(mob2.busy)
					throw new Exception();
			}
			catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: MobilePhone with identifier" + " "+ i2+" is busy");
		}
		if((!mob1.busy)&&(!mob2.busy))
		{
			mob1.threadfns.enqueue(inputs);
			//mob2.threadfns.enqueue(inputs);
			Thread t9 = new Thread(mob1);
			//Thread t2 = new Thread(mob2);
			//System.out.println("**");
			System.out.println(actionMessage+" call started at "+c);
			t9.start();
			//t2.start();
			/*try
					{
						t1.join();
					}
					catch(InterruptedException ie)
					{
						System . out . println (ie );
						 ie. printStackTrace ();
					}
					try
					{
						t2.join();
					}
					catch(InterruptedException ie)
					{
						System . out . println (ie );
						 ie. printStackTrace ();
					}*/
		}
		}

		}
	

				
	}
}


