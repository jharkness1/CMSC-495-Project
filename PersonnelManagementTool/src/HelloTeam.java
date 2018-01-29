import java.util.Scanner;

public class HelloTeam {

	public static void main(String[] args) {
		
		System.out.println("Welcome to the project everyone!");
		System.out.print("Please enter your first name: ");
		System.out.println("");
		
		Scanner sc = new Scanner(System.in);
		String input = sc.nextLine();
		
		System.out.println(   "\n*******************************************" 
							+ "\n   Glad you are part of my team " + input + "! " 
							+ "\n            Let's do this!!!!!!          "
							+ "\n*******************************************" );
		
		

	}

}
