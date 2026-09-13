public class ExponentialExample {

    // This method has O(2^n) time complexity
    public static int fibonacci(int n) {
        // Base cases: if n is 0 or 1, return n
        if (n <= 1) {
            return n;
        }
        
        // The method calls itself TWICE, creating a branching tree structure
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = 6; 
        System.out.println("Fibonacci number at position " + n + " is: " + fibonacci(n));
    }
}
