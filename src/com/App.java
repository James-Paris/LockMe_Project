package com;

import java.io.File;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * 
 * 
 */
public class App {

	
	final static String MY_DIR = "C:/Users/jmparis/Desktop/HCLTest";
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		showWelcomeScreen();
		showMainMenu();
		
		
	}
	private static void collectionMainMenuOption() {
		
		System.out.println("Please choose 1, 2 or 3:");
        String option = scan.nextLine();
        
        switch (option) {
        
            case "1":
                showFilesInAscendingOrder();
                break;
                
            case "2":
            	
            	break;

            case "3":
                System.out.println("Thanks for using lockedme.com. Closing application.");
                System.exit(0);
                break;
                
            default:
                System.out.println("Invalid input provided, please choose 1, 2 or 3.");
        }
        showMainMenu();
	}
	
	private static void showFilesInAscendingOrder() {
		System.out.println("------------------");
        System.out.println("Showing files in ascending order at:" + MY_DIR );
        File[] files = new File(MY_DIR).listFiles();
        Set<String> sorted = new TreeSet<>();
        for (File file: files) {
            if (!file.isFile()) {
                continue;
            }
            sorted.add(file.getName());
        }
        sorted.forEach(System.out::println);
        System.out.println("------------------");
	}
	
	private static void showMainMenu() {
		System.out.println("----- MAIN MENU -----");
        System.out.println("1.) Show files in ascending order");
        System.out.println("2.) Perform file operations");
        System.out.println("3.) Close the application");
        System.out.println("---------------------");
        collectionMainMenuOption();
	}
	
	private static void showWelcomeScreen() {
        System.out.println("---------------------------");
        System.out.println(" Application: LockedMe.com");
        System.out.println("  Developer: James Paris");
        System.out.println("---------------------------");
        System.out.println("\n");
	}

	
	
}
