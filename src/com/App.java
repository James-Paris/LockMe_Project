package com;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/*
 * 
 * 
 */
public class App {

	
	static String MY_DIR = "C:/Users/jmparis/Desktop/HCLTest";
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		//todo: add a grabpath method that allows user to select their path
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
            	showFileOpMenu();
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
	
	public static void setPath(String pathname) {
		MY_DIR = pathname;
		return;
	}
	
	
	private static void showFilesInAscendingOrder() {
		System.out.println("------------------");
        System.out.println("Showing files in ascending order at:" + MY_DIR );
        File f = new File(MY_DIR);
        if(f.exists()) {
        	System.out.println("THIS PATH EXISTS ALREADY");
        } else {
        	System.out.println("WE JUST MADE THE DIR WE JSUT MADDE THE DIR");
        	f.mkdir();
        }
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
	
	private static void fileOpMenuOption() {
		
		System.out.println("Please choose 1, 2 or 3:");
        String option = scan.nextLine();
        String filename = "";
        
        switch (option) {
                
            case "1":
            	filename = getFileName();
            	addFile(filename);
            	break;

            case "2":
            	filename = getFileName();
            	deleteFile(filename);
                break;
                
            case "3":
            	showMainMenu();
            	break;
                
            default:
                System.out.println("Invalid input provided, please choose 1, 2 or 3.");
        }
        showFileOpMenu();
		
	}
	
	private static void showFileOpMenu() {
		
		System.out.println("------ File Operations Menu ------");
		System.out.println("1) Add a file");
		System.out.println("2) Delete a file");
		System.out.println("3) Back to main menu");
		System.out.println("----------------------------------");
		
		
		
		
		fileOpMenuOption();
	}
	
	private static String getFileName() {
		
		System.out.println("Please enter a filename:");
		String temp = scan.nextLine();
		
		while(temp.isEmpty()) {
			System.out.println("Error! Please enter a valid filename:");
			temp = scan.nextLine();
		}
		
		return temp;
	}
	
	private static void addFile(String filename) {
		
		File file = new File(MY_DIR + "\\" +  filename + ".txt");
		
		if(file.exists()) {
			System.out.println("The file '" + filename + "' already exists.");
			System.out.println("\n");
		} else {
			try {
				System.out.println("File created at location: " + file.getAbsolutePath());
				System.out.println("\n");
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
		
	}
	
	private static void deleteFile(String filename) {
File file = new File(MY_DIR + "\\" + filename + ".txt");
		
		if(file.exists()) {
			file.delete();
			System.out.println("Deleted the file.");
			System.out.println("\n");
		} else {
			System.out.println("That file doesn't exist.");
			System.out.println("\n");
		}
	}

	
	
}
