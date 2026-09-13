public class QuadraticExample {
    public static void main(String[] args) {
        int n = 4; // This is the input size (n)
        
        System.out.println("Printing a grid for n = " + n + ":");
        
        // Outer loop runs 'n' times
        for (int i = 1; i <= n; i++) {
            
            // Inner loop runs 'n' times for every single outer loop iteration
            for (int j = 1; j <= n; j++) {
                System.out.print("(" + i + "," + j + ") ");
            }
            
            System.out.println(); // Moves to the next line after completing inner loop
        }
    }
}
