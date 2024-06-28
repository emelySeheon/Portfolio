public class Main {

    public static void main(String[] args) {
        //Save the word the user types in the command line
        String input = args[0];
        //Turning it lower case
        String inputLower = input.toLowerCase();
        //Converting it to a char array
        char [] palindrome = inputLower.toCharArray();

        //Checking each letter to see if it matches
        for (int i=0; i< palindrome.length; ++i){
            if (palindrome[i]==palindrome[palindrome.length-1-i]){
                if(i==palindrome.length-1){
                    System.out.println(input+" is a palindrome!!! :)");
                    break;
                }
                continue;
            }else{
                System.out.println(input+" is not a palindrome :(");
                break;
            }
        }
    }
}
