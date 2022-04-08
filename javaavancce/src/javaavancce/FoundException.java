package javaavancce;

import java.util.Date;

public class FoundException {
	public static void main(String[] args) {
		Date date = null;
		Date today = new Date();

		try {
			System.out.println(date.getClass().getName());
			//System.out.println(today.getClass().getName());
		} catch (NullPointerException e) {
			e.printStackTrace();
		
		} finally {
			try {
				System.out.println(today.getClass().getName());
				//System.out.println(date.getClass().getName());
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
			
		}
		System.out.println("le programme continue");

	}
}
