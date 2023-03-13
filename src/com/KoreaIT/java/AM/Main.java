package com.KoreaIT.java.AM;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int cmd = sc.nextInt();
		int cmd2 = sc.nextInt();
		
		for(int i =0; i<cmd2; i++) {
			for(int j=0; j<cmd; j++) {
				System.out.printf("*");
			} System.out.println();
		}
	}

}