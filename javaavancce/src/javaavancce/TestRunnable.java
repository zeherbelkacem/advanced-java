package javaavancce;

public class TestRunnable implements Runnable {
	
	public static void main(String[] args) {
		Thread thread = new Thread(new TestRunnable());
		thread.start();
	}

	@Override
	public void run() {
		//for (int i = 0; i < 10; i++) {
			System.out.println("*\n"
					+ "**\n"
					+ "***\n"
					+ "****\n"
					+ "*****\n"
					+ "******\n"
					+ "*******\n"
					+ "********\n"
					+ "*********\n"
					+ "**********\n"
					+ "***********\n");
		//}
	}

}
