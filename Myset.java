public class Myset<A>
{
	public node<MobilePhone> head ;
	public node<MobilePhone> tail ;
	public int setsize;
	public boolean IsEmpty()
	{
		return head == null;
	}
	public boolean IsMember(A i)
	{
		if (head == null)
			return false;
		else
		{
			node<MobilePhone> a = head;
			while(a!= null)
			{
				if(a.getdata().equals(i))
					return true;
				a = a.getnext();
			}
			return false;
		}
	}
	public boolean IsMember(int i)
	{
		if (head == null)
			return false;
		else
		{
			node<MobilePhone> a = head;
			while(a!= null)
			{
				if(a.getdata().number==i)
					return true;
				a = a.getnext();
			}
			return false;
		}
	}
	public MobilePhone throwphone(int i)
	{
		node<MobilePhone> a = head;
			while(a!= null)
			{
				if(a.getdata().number==i)
					{return a.getdata();}
				//a = a.getnext();}

					a = a.getnext();
				
			}
			MobilePhone b = new MobilePhone(-1);
			return b;
	}
	public void insert(A i)
	{
		if(!this.IsMember(i))
			{
				if(head==null)
				{
					node c = new node();
					c.setdata(i);
					head = tail = c;setsize++;
					return;
				}
				node c = new node();
				c.setdata(i);
				tail.setnext(c);
				c.setprev(tail);
				tail = c;
				setsize++;
			}  
	}

	public void delete(A i)
	{
		try
		{
			if(head == null)
				throw new Exception();
		}
		catch(Exception e)
		{
			//e.printStackTrace();
		}
		try
		{
			node<MobilePhone> a = head;
		while(a!= null)
			{
				if(a.getdata().equals(i))
					{
						if((a.getprev()==null)||(a.getnext()==null))
						{if(a.getprev()==null)
						{
							if(a.getnext()!=null)
							{a.getnext().setprev(null);
							this.head = a.getnext();}
							else 
							{
								this.head = null;
								this.tail = null;
							}
						}
						if(a.getnext()==null)
						{
							if(a.getprev()!=null)
							{a.getprev().setnext(null);
							this.tail = a.getprev();}
							else 
								{this.head = null;
									this.tail = null;}
						}
						setsize--;
						return;}

						a.getprev().setnext(a.getnext());
						a.getnext().setprev(a.getprev());
						setsize--;
						return ;
					}
				a = a.getnext();
			}
			throw new Exception();
		}
		catch (Exception e)
		{
			//e.printStackTrace();
		}

	}
	public Myset Union(Myset a)
	{
		Myset b = new Myset();
		
		
		
			node c = new node();
			node<MobilePhone> thisnode = this.head;
			node<MobilePhone> anode = a.head;
			while (anode!= null)
			{
				if(b.head == null)
				{
					c.setdata(anode.getdata());
					b.head = c;
					anode = anode.getnext();
				}
				else
				{
					node d = new node();
					d.setdata(anode.getdata());
					d.setprev(c);
					c.setnext(d);
					c = c.getnext();
					anode = anode.getnext();
				}
			}

			while(thisnode!= null)
			{
				if(b.head == null)
				{
					c.setdata(thisnode.getdata());
					b.head = c;
					
				}
				else if((b.head!=null)&&(!a.IsMember(thisnode.getdata())))
				{
					node d = new node();
					d.setdata(thisnode.getdata());
					d.setprev(c);
					c.setnext(d);
					c = c.getnext();
					
				}
				thisnode = thisnode.getnext();
			}
			b.tail = c;
			return b;
		
	}
	public Myset Intersection(Myset a)
	{
		Myset b = new Myset();
		node c = new node();
		node thisnode = this.head;
			node anode = a.head;
			while(thisnode!= null)
			{
				if(a.IsMember(thisnode.getdata()))
				{
					if(b.head == null)
				{
					c.setdata(thisnode.getdata());
					b.head = c;
					
				}
				else 
				{
					node d = new node();
					d.setdata(thisnode.getdata());
					d.setprev(c);
					c.setnext(d);
					c = c.getnext();
					
				}
				}
				thisnode = thisnode.getnext();
			}
			b.tail = c;
			return b;


	}
	/*public static void main(String[] args) 
	{
		Myset a = new Myset();
		Myset b = new Myset();
		Myset c = new Myset();
		Myset d = new Myset();
		a.insert(1);
		a.insert(3);
		a.insert(5);
		//a.delete(5);
		b.insert(2);
		b.insert(4);
		b.insert(6);
		c = a.Union(b);
		d = a.Intersection(b);
		node dnode = d.head;
		while(dnode!=null)
		{
			System.out.println(""+dnode.getdata());
			dnode = dnode.getnext();
		}
	}*/
}
class node<A>
{
	private A data;
	private node next;
	private node prev;
	public node getnext()
	{
		return next;
	}
	public void setnext(node n)
	{
		next = n;
	}
	public node getprev()
	{
		return prev;
	}
	public void setprev(node n)
	{
		prev = n;
	}
	public A getdata()
	{
		return data;
	}
	public void setdata(A n)
	{
		data = n;
	}
}
/*public class Myset
{
	public node head;
	public node tail;
	public boolean IsEmpty()
	{
		return head == null;
	}
	public boolean IsMember(int i)
	{
		if (head == null)
			return false;
		else
		{
			node a = head;
			while((a!= null)&&(a.getdata()<=i))
			{
				if(a.getdata()==i)
					return true;
				a = a.getnext();
			}
			return false;
		}
	}
	public void insert(int i)
	{
		node c = new node();
		c.setdata(i);
		if(head==null)
		{
			head = tail = c;
		}  
		else
		{
			node a = head;
			while((a!= null)&&(a.getdata()<i))
				a = a.getnext();
			if(a==null)
			{
				tail.setnext(c);
				c.setprev(tail);
				tail = c;
			}
			else if(a.getdata()>i)
			{
				c.setnext(a);
				c.setprev(a.getprev());
				a.getprev().setnext(c);
				a.setprev(c);
			}
		}
	}
	public void delete(int i)
	{
		try
		{
			if(head == null)
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			node a = head;
		while((a!= null)&&(a.getdata()<=i))
			{
				if(a.getdata()==i)
					{
						a.getprev().setnext(a.getnext());
						a.getnext().setprev(a.getprev());
						return ;
					}
				a = a.getnext();
			}
			throw new Exception();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public Myset Union(Myset a)
	{
		Myset b = new Myset();
		if(a.head==null)
			return this;
		else if(this.head==null)
			return a;


		else 
		{
			node c = new node();
			node thisnode = this.head;
			node anode = a.head;
			if(thisnode.getdata()<anode.getdata())
				{
					c.setdata(thisnode.getdata());
					b.head = c;
					thisnode = thisnode.getnext();
				}
			else
			{
				c.setdata(anode.getdata());
				b.head = c;
				anode = anode.getnext();
			}

			
			while((anode!= null)&&(thisnode!=null))
			{
				node d = new node();
				if(thisnode.getdata()<=anode.getdata())
				{
					d.setdata(thisnode.getdata());
					d.setprev(c);
					c.setnext(d);
					c = c.getnext();
					thisnode = thisnode.getnext();
				}
				else 
				{
					d.setdata(anode.getdata());
					d.setprev(c);
					c.setnext(anode);
					c = c.getnext();
					anode = anode.getnext();
				}
			}
			if(anode == null)
			{
				while(thisnode!=null)
				{
					node d = new node();
					d.setdata(thisnode.getdata());
					d.setprev(c);
					c.setnext(d);
					c = c.getnext();
					thisnode = thisnode.getnext();

				}
				b.tail = this.tail;
			}
			else
			{
				while(anode!=null)
				{
					node d = new node();
					d.setdata(anode.getdata());
					d.setprev(c);
					c.setnext(anode);
					c = c.getnext();
					anode = anode.getnext();
				}
				b.tail = a.tail;
			}
			return b;
		}
	}
	public Myset Intersection(Myset a)
	{
		Myset b = new Myset();
		node c = new node();
		while((anode! = null)&&(thisnode!=null))
		{

		}

	}
	

}*/
class ExchangeList
{
	public stone head;
	public int size;
	public stone tail;
	public int Size()
	{
		return size;
	}
	public boolean isEmpty()
	{
		return head==null;
	}
	public void insert(Exchange c)
	{
		stone a = new stone();
		if(head==null)
		{
			a.data = c;
			head = tail = a;
		}
		else

		{
			a.data = c;
			tail.next = a;
			a.prev = tail;
			tail = a;
		}
		size = (size + 1);
	}
}
class stone 
{
	public Exchange data;
	public stone next;
	public stone prev;
}
class Exchange
{
	public int identifier;
	public Exchange parent;
	public ExchangeList children = new ExchangeList();
	public MobilePhoneSet residentset = new MobilePhoneSet();
	public Exchange(int number)
	{
		identifier = number;
	}
	public Exchange Parent()
	{
		return parent;
	}
	public int numChildren()
	{
		return children.Size();
	}
	public Exchange child(int i)
	{
		stone a = children.head;
		for(int j=0;j<i;j++)
		{
			a = a.next;
		}
		/*Exchange bit =new Exchange(-1);
		if(a==null)
			return bit;*/
		return a.data;
	}
	public boolean isRoot()
	{
		return parent==null;
	}
	public RoutingMapTree subtree(int i)
	{
		RoutingMapTree a = new RoutingMapTree(this.child(i));
		//a.root = this.child(i);
		return a;
	}
	public RoutingMapTree subtree()
	{
		RoutingMapTree a = new RoutingMapTree(this);
		return a;
	}
	public MobilePhoneSet ResidentSet()
	{
		return residentset;
	}
}
class MobilePhone implements Runnable
{
	public int number;
	public boolean status;
	public Exchange location;
	public boolean busy;
	//public String[] phonequery;
	public queimp threadfns = new queimp();
	public boolean random;
	public RoutingMapTree server;
	public long timeofstart;
	public long simtime;
	public MobilePhone(int i)
	{
		number = i;
	}
	public MobilePhone(int i,RoutingMapTree br,long z,boolean b,long s)
	{
		number = i;
		//threadfns = actions;
		server = br;
		busy = false;
		timeofstart = z;
		random = b;
		simtime =s;
	}
	public int Number()
	{
		return number;
	}
	public boolean Status()
	{
		return status;
	}
	public void SwitchOn(RoutingMapTree centre,int i2)
	{
		status = true;
		Exchange b = centre.throwNode(i2);
		this.location = b;
		
		Exchange c = b;
		while(c!=null)
		{
			c.residentset.insert(this);
			c = c.parent;
		}
		//newphone.location = this.throwNode(i2);
	}
	public void SwitchOff(RoutingMapTree centre)
	{
		status = false;
		Exchange c = this.location;
		while(c!=null)
		{
			c.residentset.delete(this);
			c = c.parent;
		}
	}
	public Exchange Location()
	{
		try
		{
			if(!status)
				throw new Exception();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return location;
	}
	public void movephone(RoutingMapTree centre, int b)
	{
		this.SwitchOff(centre);
		//Exchange locals = centre.throwNode(b);
		this.SwitchOn(centre,b);
	}
	
	public void run(){
		go();
	}

	public synchronized void go()
	{
		if(!random)//System.out.println("==" + threadfns.qsize);
		{while(true)
		{
			
			
			step beta = threadfns.head;
			
				if(beta!=null)
				{
					if(((beta.Element)[1]).equals("switchOnMobile"))
					{
						int i2 = Integer.parseInt((beta.Element)[3]);
						this.SwitchOn(server,i2);
						threadfns.dequeue();
					}
					else if(((beta.Element)[1]).equals("switchOffMobile"))
					{
						this.SwitchOff(server);
						threadfns.dequeue();
					}
					else if(((beta.Element)[1]).equals("movePhone"))
					{
						int i2 = Integer.parseInt((beta.Element)[3]);
						this.movephone(server,i2);
						threadfns.dequeue();
					}
					else if(((beta.Element)[1]).equals("routeCall"))
					{
						//int i2 = Integer.parseInt((beta.Element)[2]);
						int i3 = Integer.parseInt((beta.Element)[3]);
						int i4 = Integer.parseInt((beta.Element)[4]);
						//if((this.number==i2)||(this.number==i3))
							this.busy=true;
							MobilePhone mob2 = server.root.residentset.throwphone(i3);
							mob2.busy =true;
							try{Thread.currentThread().sleep(i4);}
							catch ( InterruptedException e){e.printStackTrace();
								//System.out.println("h");
							}
							
							this.busy=false;
							mob2.busy=false;
							//if(this.number==i2)
						
						int j = beta.Element.length;
							for(int t=0;t<j;t++)
							{
								System.out.print((beta.Element)[t]+" ");
							}
							long endtime= System.currentTimeMillis() - timeofstart;
							System.out.print("call ended at "+endtime);
							System.out.println("");

					
						
						threadfns.dequeue();
						//System.out.println(threadfns.qsize);
						/*int l = sarr.length;
							for(int t=0;t<l;t++)
							{
								System.out.print(sarr[t]+" ");
							}
							//System.out.print("call ended ");
							System.out.println("");*/
						
						//this.busy=false;
						

						
					}
				}
				//beta = beta.next;
			
			if(beta==null)
				{break;}
			
		}}
		else
		{
			while((System.currentTimeMillis() - timeofstart)<simtime)
			{
				int sleeptime = 4000 +(int)(Math.random()*2000);
				try{Thread.sleep(sleeptime);}
				catch ( InterruptedException e){e.printStackTrace();
								//System.out.println("h");
							}
				int fngenerator = (int)(Math.random()*4);
				long c = (System.currentTimeMillis() - timeofstart);
			switch (fngenerator)
			{
				case 0:
				{
					int i2 = 4+(int)(Math.random()*6);
					
						//threadfns.dequeue();
					if(!server.root.residentset.IsMember(this.number))
					{this.SwitchOn(server,i2);System.out.println("switchon "+this.number+" "+i2);}
						break;
				}
				case 1:
				{
					try{if(!server.fullset.IsMember(this.number))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println("switchoff ERROR: mobile with identifier "+this.number+" not present");
		}	
				if((server.fullset.IsMember(this.number))&&(!this.busy))
				{if(server.root.residentset.IsMember(this.number))
					{this.SwitchOff(server);
					System.out.println("switchoff "+this.number+" ");}}
						//threadfns.dequeue();
						break;
				}
				case 2:
				{
					int i2 = 4+(int)(Math.random()*6);
					
					//int i1 = Integer.parseInt(inputs[2]);
				//int i2 = Integer.parseInt(inputs[3]);
				
		try
		{
			if((!server.root.residentset.IsMember(this.number))&&(server.fullset.IsMember(this.number)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("movephone "+this.number+" "+i2+" ERROR: MobilePhone with identifier" + " "+ this.number+" is switched off");
		}
						//threadfns.dequeue();
		
		try{if(!server.containsNode(i2))
					throw new Exception();}
					catch(Exception e)
		{
			System.out.println("movephone ERROR: exchange with identifier "+i2+" not present");
		}
				if((server.root.residentset.IsMember(this.number))&&(server.containsNode(i2)))
				{
					this.movephone(server,i2);
					System.out.println("movephone "+this.number+" "+i2);}
						break;
				}
				case 3:
				{
					int i3 = server.throwrandomphone().number;
					int i4 = 4000+(int)(Math.random()*1000);
					//int i1 = Integer.parseInt(inputs[2]);
				//int i2 = Integer.parseInt(inputs[3]);
				//System.out.println("h");
					if((System.currentTimeMillis() - timeofstart)<simtime-i4)
					{try
		{
			if((!server.root.residentset.IsMember(this.number))&&(server.fullset.IsMember(this.number)))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("routeCall "+this.number+" "+i3+" ERROR: MobilePhone with identifier" + " "+ this.number+" is switched off");
		}
		//this.busy=true;
				
		/*try
		{
			if (!server.fullset.IsMember(i3))
				throw new Exception();
		}
		catch(Exception e)
		{
			System.out.println("routeCall ERROR: MobilePhone with identifier" + " "+ i3+" was not found");
		}*/
		try
		{
			if((!server.root.residentset.IsMember(i3))&&(server.fullset.IsMember(i3)))
				{throw new Exception();}
		}
		catch(Exception e)
		{
			System.out.println("routeCall "+this.number+" "+i3+" ERROR: MobilePhone with identifier" + " "+ i3+" is switched off");
		}
		if((server.root.residentset.IsMember(i3))&&(server.root.residentset.IsMember(this.number)))
		{
			//MobilePhone mob1 = server.root.residentset.throwphone(i1);
			MobilePhone mobnext = server.root.residentset.throwphone(i3);
			
		try
			{
				if((mobnext.busy)||(i3==this.number))
					{busy = false;throw new Exception();}
			}
			catch(Exception e)
		{
			System.out.println("routeCall "+this.number+" "+i3+" ERROR: MobilePhone with identifier" + " "+ i3+" is busy");
		}
					if((!mobnext.busy)&&(mobnext.number!=this.number))
					{long strttime= System.currentTimeMillis() - timeofstart;
						System.out.println("routeCall "+this.number+" "+i3+" "+i4+" call started at "+strttime);
					
							//MobilePhone mob2 = server.root.residentset.throwphone(i3);
						this.busy = true;
							mobnext.busy =true;
							try{Thread.currentThread().sleep(i4);}
							catch ( InterruptedException e){e.printStackTrace();
								//System.out.println("h");
							}
							
							this.busy=false;
							mobnext.busy=false;
							//if(this.number==i2)
						
						/*int j = beta.Element.length;
							for(int t=0;t<j;t++)
							{
								System.out.print((beta.Element)[t]+" ");
							}*/
							long endtime= System.currentTimeMillis() - timeofstart;
							System.out.print("routeCall "+this.number+" "+i3+" "+i4+" call ended at "+endtime);
							System.out.println("");}}}

					
						
						//threadfns.dequeue();
				}
			}}
		}
	}
}
class MobilePhoneSet
{
	public Myset phoneset = new Myset<MobilePhone>();
	public boolean IsEmpty()
	{
		return phoneset.IsEmpty();
	}
	public boolean IsMember(MobilePhone i)
	{
		return phoneset.IsMember(i);
	}
	public boolean IsMember(int i)
	{
		return phoneset.IsMember(i);
	}
	public MobilePhone throwphone(int i)
	{
		return phoneset.throwphone(i);
	}
	public void insert(MobilePhone i)
	{
		phoneset.insert(i);
	}
	public void delete(MobilePhone i)
	{
		phoneset.delete(i);
	}
	public MobilePhoneSet Union(MobilePhoneSet a)
	{
		Myset b = this.phoneset;
		Myset c = a.phoneset;
		Myset d = b.Union(c);
		MobilePhoneSet e = new MobilePhoneSet();
		e.phoneset = d;
		return e;
	}
	public MobilePhoneSet Intersection(MobilePhoneSet a)
	{
		Myset b = this.phoneset;
		Myset c = a.phoneset;
		Myset d = b.Intersection(c);
		MobilePhoneSet e = new MobilePhoneSet();
		e.phoneset = d;
		return e;
	}
}
class queimp
{
	
	public step head;
	public step tail;
	public int qsize;
	public step gethead()
	{
		return head;
	}
	public void sethead(step n)
	{head=n;}
	public step gettail()
	{return tail;}
	public void settail(step n)
	{tail=n;}
	public void enqueue(String[] b)
	{
		if (head==null)
		{
			step newnode = new step(b);
			this.sethead(newnode);
			this.settail(newnode);

		}
		else if(head.getnext()==null)
		{
			step newnode = new step(b);
			head.setnext(newnode);
			this.settail(newnode);
		}
		else 
		{
			step newnode = new step(b);
			tail.setnext(newnode);
			this.settail(newnode);
		}qsize++;

	}
	public String[] dequeue()
	{
		if (head==null) 
		{
			throw new NullPointerException();
		}
		else if (head.getnext()==null)
		{
			String[] temp = head.getElement();
			head = null;
			tail = null;qsize--;
			return temp;
		}
		 
		else
		{
			String[] temp = head.getElement();
			head = head.getnext();qsize--;
			return temp;
		}

	}
	
}
class step
	{
		public String[] Element;
		public step next;
		public step(String[] e,step b)
		{
			Element = e;
			next = b;
		}
		public step(String[] e)
		{
			Element = e;
			next = null;
		}
		public String[] getElement()
		{return Element;}
		public step getnext()
		{return next;}
		public void setnext(step newnext)
		{
			next = newnext;
		}
	}
