public class FactorialTimeExample {

    // This method runs in O(n!) time
    public static void factorialRuntime(int n) {
        if (n <= 0) {
            return;
        }

        // The loop runs 'n' times, spawning 'n' recursive subproblems
        for (int i = 0; i < n; i++) {
            factorialRuntime(n - 1);
        }
    }

    public static void main(String[] args) {
        int n = 4; 
        System.out.println("Starting O(n!) function for n = " + n);
        
        factorialRuntime(n);
        
        System.out.println("Finished successfully!");
    }
}
