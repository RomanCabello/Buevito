package main;

public class Ping extends Thread{
	private Event event;
	
	public Ping (Event ev)
	{
		event = ev;
	}
	
	public void run()
	{
		while(event.isLocked())
		{
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println(event.getObj());
		}
	}
}
