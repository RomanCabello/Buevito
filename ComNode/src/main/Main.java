package main;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Brain brain = new Brain();
		WakeUp wake = new WakeUp(brain);
		wake.run();
	}

}
