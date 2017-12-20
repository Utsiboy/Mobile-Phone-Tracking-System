public class Myset<A>
{
	public node<MobilePhone> head ;
	public node<MobilePhone> tail ;
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
					head = tail = c;
					return;
				}
				node c = new node();
				c.setdata(i);
				tail.setnext(c);
				c.setprev(tail);
				tail = c;
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
			e.printStackTrace();
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
							a.getnext().setprev(null);
							this.head = a.getnext();
						}
						if(a.getnext()==null)
						{
							a.getprev().setnext(null);
							this.tail = a.getprev();
						}
						return;}

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
	public MobilePhoneSet ResidentSet()
	{
		return residentset;
	}
}
class MobilePhone
{
	public int number;
	public boolean status;
	public Exchange location;
	public MobilePhone(int i)
	{
		number = i;
	}	
	public int Number()
	{
		return number;
	}
	public boolean Status()
	{
		return status;
	}
	public void SwitchOn()
	{
		status = true;
	}
	public void SwitchOff()
	{
		status = false;
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