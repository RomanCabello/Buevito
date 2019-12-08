import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	private static int num1;
	private static int num2;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		

		if(args.length == 0)
		{
			System.out.println(2);
		}else {
			num1 = Integer.parseInt(args[0]);

			num2 = Integer.parseInt(args[1]);
			System.out.println(num1 - num2);
		}
			

		

	}

}
