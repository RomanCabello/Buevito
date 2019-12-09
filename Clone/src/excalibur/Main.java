package excalibur;

import java.io.File;
import java.io.IOException;

public class Main {
	
	private static java.lang.Process pro;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String here = System.getProperty("user.dir");
		
		int from = here.lastIndexOf('\\')+1;
		
		String folder = here.substring(from);
		String route = here.substring(0, from);
		
		System.out.println(here);
		System.out.println(folder);
		
		
		String clone = folder+"-Clone";
		String path = "..//"+clone;
		
		String route2 = route+clone;
		
		 new File(path).mkdir();
		
		 System.out.println(route2);
		 
		 try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		 try {
				pro = Runtime.getRuntime().exec("xcopy /s "+here+" "+route2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Couldn't load module");
				
			}
	}

}
