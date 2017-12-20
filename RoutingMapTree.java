public class RoutingMapTree
{
	public Exchange root= new Exchange(0);
	public MobilePhoneSet fullset =new MobilePhoneSet();
	public RoutingMapTree() 
	{
		
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
	public void switchOn(MobilePhone a, Exchange b)
	{
		a.SwitchOn();
		a.location = b;
		
		Exchange c = b;
		while(c!=null)
		{
			//System.out.println("\n"+c.identifier);
			c.residentset.insert(a);
			
			c = c.parent;
		}
		
	}
	public void switchOff(MobilePhone a)
	{
		a.SwitchOff();
		Exchange c = a.location;
		while(c!=null)
		{
			c.residentset.delete(a);
			c = c.parent;
		}
	}

	public void performAction(String actionMessage) 
	{
		//System.out.println(actionMessage);
		
			String[] inputs = actionMessage.split("\\s+");
			String a = inputs[0];
			//queries function;
			if(a.equals("addExchange"))
			{
				int i1 = Integer.parseInt(inputs[1]);
				int i2 = Integer.parseInt(inputs[2]);
				
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
				
				System.out.println(actionMessage);}
				
			}
			else if(a.equals("switchOnMobile"))
			{
				int i1 = Integer.parseInt(inputs[1]);
				int i2 = Integer.parseInt(inputs[2]);
				try{if(!this.containsNode(i2))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i2+" not present");
		}	
		if(this.containsNode(i2))
			{	if(!fullset.IsMember(i1))
				{
					MobilePhone newphone = new MobilePhone(i1);
					newphone.location = this.throwNode(i2);
					this.switchOn(newphone,newphone.location);
					this.fullset.insert(newphone);
				}
				else
				{
					MobilePhone newphone = fullset.throwphone(i1);
					newphone.location = this.throwNode(i2);
					this.switchOn(newphone,newphone.location);
				}
				System.out.println(actionMessage);}
			}
			else if(a.equals("switchOffMobile"))
			{
				int i1 = Integer.parseInt(inputs[1]);
				try{if(!fullset.IsMember(i1))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: mobile with identifier "+i1+" not present");
		}	
			if(fullset.IsMember(i1))
				{if(root.residentset.IsMember(i1))
					{MobilePhone phone = root.residentset.throwphone(i1);
					this.switchOff(phone);}
				
				System.out.println(actionMessage);}
			}
			else if(a.equals("queryNthChild"))
			{
				int i1 = Integer.parseInt(inputs[1]);
				int i2 = Integer.parseInt(inputs[2]);
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
					System.out.println(actionMessage+": "+ex.child(i2).identifier);}
						catch(Exception e)
		{
			System.out.println(actionMessage+" ERROR: exchange with identifier "+i1+" does not have so many children");
		}	
					}
				
			}
			else if(a.equals("queryMobilePhoneSet"))
			{
				int i1 = Integer.parseInt(inputs[1]);
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
				System.out.println("");}
			}

				
	}
}

