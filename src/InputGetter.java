import java.util.Scanner;

/**
 * @author: Marcus Trujillo
 * @version:
 * This is a helper object that gets text input from the user in order to make their moves.
 *
 */
public class InputGetter {
    Scanner scanner;

    public InputGetter(){
        try{
            scanner = new Scanner(System.in);
        } catch (Exception ex){
            System.out.println("We couldn't make the scanner");
        }
    }

    public String getStringInput(String prompt){
        String input = "";
        try{
            System.out.println(prompt);
            input = scanner.nextLine();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return input;
    }
}
