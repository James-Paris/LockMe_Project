package com;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * @author: James Paris
 * @date - 1/18/2021
 */
public class App {
	
	public static boolean matchFlag = false;

	static String MY_DIR = "C:/Users/jmparis/Desktop/HCLTest";
	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		grabPath();
		showWelcomeScreen();
		showMainMenu();
	}
	
	private static void grabPath() {
		System.out.println("\nPlease specify a directory to use, or press [ENTER] to use the default directory: ");
		String temp = scan.nextLine();
		if(temp.isEmpty()) {
			System.out.println("Proceeding with the default directory.");
		} else {
			setPath(temp);
			System.out.println("We will use '" + temp + "' as our working directory.");
		}
		
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
        //System.out.println("Showing files in ascending order at:" + MY_DIR );
        File f = new File(MY_DIR);
        if(f.exists()) {
        	//System.out.println("THIS PATH EXISTS ALREADY");
        } else {
        	//System.out.println("WE JUST MADE THE DIR WE JSUT MADDE THE DIR");
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
		
		System.out.println("Please choose 1, 2, 3, 4");
        String option = scan.nextLine();
        String filename = "";
        int searchResult = 0;
        
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
            	searchResult = searchFiles();
            	switch(searchResult) {
	            	
            		//No match
	            	case 0:
	            		System.out.println("\nThere were no matches for that filename.");
	            		break;
	            		
	            	//Similar string
	            	case 1:
	            		
	            		break;
	            		
	            	//Perfect match
	            	case 2:
	            		System.out.println("\nYes. That files exists within the directory!");
	            		break;
            	
            	}
            	break;
                
            case "4":
            	showMainMenu();
            	break;
                
            default:
                System.out.println("Invalid input provided, please choose 1, 2, 3, or 4.");
        }
        showFileOpMenu();
		
	}
	
	private static void showFileOpMenu() {
		
		System.out.println("------ File Operations Menu ------");
		System.out.println("1) Add a file");
		System.out.println("2) Delete a file");
		System.out.println("3) Search for a file");
		System.out.println("4) Back to main menu");
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
	
	private static void setMatch(boolean b) {
		App.matchFlag = b;
	}
	
	private static int searchFiles() {
		
		System.out.println("\n");
		System.out.println("Please enter a filename you would like to search for (Include file extension):");
		String temp = scan.nextLine();
		boolean matches = false;
		
		//Input validation
		while(temp.isEmpty()) {
			System.out.println("Error! Please enter a valid filename you would like to search for (Include file extension):");
			temp = scan.nextLine();
		}
		
		
		File[] files = new File(MY_DIR).listFiles();
        for(File file : files) {
        	 if (!file.isFile()) {
        		 //System.out.println("cont...");
                 continue;
             }
        	if(file.getName().toLowerCase().contains(temp.toLowerCase())) {
        		if(file.getName().equalsIgnoreCase(temp)) {
        			return 2;
        		}
        		System.out.println("Similar match found: " + file.getName());
        		return 1;
        	}
        	
        	matches = Pattern.matches(temp, file.getName());
        	
       		if(matches) {       	
       			//System.out.println("It matched!");
       			if(file.getName().equalsIgnoreCase(temp)) {
       				//System.out.println("IT SUPER MATCHED");
       				//"perfect match"
       				return 2;
        		}
       			System.out.println("Similar match found: " + file.getName());
       			return 1;
        	}
        }
		
		return 0;
	}

	private static boolean getMatchFlag() {
		return App.matchFlag;
	}

	
	
}
