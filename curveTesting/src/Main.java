public class Main {

    
    public static double phi = 90; // degrees in where the robot is moving. anything phi is robotvelocity related
    public static double theta = 10; // degrees of shooter. anything theta related is shootingvelocity related
    public static double robotVelocity = 3; // m/s 
    public static double shooterVelocity = 10; // m/s

    public static void main(String[] args) throws Exception {
        
        Test test = new Test();

        double ans[] = test.calculateMovingShot(shooterVelocity, robotVelocity, theta, phi);

        System.out.println("shooter velocity: " + ans[0]);
        System.out.println("new theta: " + ans[1]);
        System.out.println("new phi: " + ans[2]);

        //polynomial regression
        double[][] testShooterData = {

            // meters, degrees
            // {1.44, 0},
            // {1.5, 2},
            // {1.7, 3.5},
            // {1.94, 4},
            // {2.1, 5},
            // {2.52, 6.5},
            // {3.05, 8.5},
            // {3.3, 8.8},
            // {3.6, 9},


            {2.1, 1800},
            {1.5, 1600},
            {1.44, 1550},
            {1.94, 1700},
            {1.7, 1650}, 
            {2.52, 1850},
            {3.05, 2000},
            {3.3, 2100},
            {3.6, 2200},

        };

        System.out.println("test: Cubic Polynomial");
        PolynomialRegression model = new PolynomialRegression(testShooterData, 3);
    
        System.out.println("Model: " + model.toString());
        System.out.printf("R² Score: %.4f%n", model.R2());

        System.out.println("\nPredictions:");
        System.out.println("  At 1.8m: " + model.predict(1.8) + "°");
        System.out.println("  At 2.0m: " + model.predict(2.0) + "°");
        System.out.println("  At 2.3m: " + model.predict(2.3) + "°");
        System.out.println("  At 2.7m: " + model.predict(2.7) + "°");
        System.out.println("  At 3.7m: " + model.predict(3.7) + "°");

    }
}