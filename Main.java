import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        String palindrome = s.next();
        boolean isPalindrome = palindrome.replaceAll("[^A-Za-z]", "").toLowerCase()
                .equals(new StringBuilder(palindrome.replaceAll("[^A-Za-z]", "").toLowerCase()).reverse().toString());

        if (isPalindrome) {
            System.out.println("Sim");
        } else {
            System.out.println("Nao");
        }
    }
}
