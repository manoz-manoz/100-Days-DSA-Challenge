public class CubicTimeExample {
    public static void main(String[] args) {
        int n = 5; // The input size
        int counter = 0;

        // Outer loop runs n times
        for (int i = 0; i < n; i++) {
            // Middle loop runs n times
            for (int j = 0; j < n; j++) {
                // Inner loop runs n times
                for (int k = 0; k < n; k++) {
                    
                    // Simple constant time O(1) operation
                    counter++;
                    System.out.println("i=" + i + ", j=" + j + ", k=" + k);
                    
                }
            }
        }

        System.out.println("\nTotal operations for n=" + n + " is: " + counter);
    }
}
