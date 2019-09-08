import java.util.Scanner;

/**
 * @author: Marcus Trujillo
 * @version:
 * This is a helper object that gets text input from the user in order to make their moves.
 *
 */
public class InputGetter {

    public String getInput(String prompt){
        String input = "";
        try(Scanner scanner = new Scanner(System.in)){
            System.out.println(prompt);
            input = scanner.nextLine();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return input;
    }
}
