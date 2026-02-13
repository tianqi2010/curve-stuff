public class Main {

    public static int maxAngle = 90;
    // public static final double theta = 60; 
    public static double beta = 45;
    // public static double robotVelocity = 3; //m/s
    public static double distance = 2; //m
    public static double tolerance = 0.1;

    public static double alpha;
    public static double newAlpha;
    // public static double velocity = 8; //m/s
    public static double newVelocity;

    // testing
    public static double phi = 30; // degrees in where the robot is moving. anything phi is robotvelocity related
    public static double theta = 50; // degrees of shooter. anything theta related is shootingvelocity related
    public static double robotVelocity = 3; // m/s 
    public static double shooterVelocity = 10; // m/s

    public static void main(String[] args) throws Exception {
        
        Test test = new Test();

        double ans[] = test.calculateMovingShot(shooterVelocity, robotVelocity, theta, phi);

        System.out.println("shooter velocity: " + ans[0]);
        System.out.println("new theta: " + ans[1]);
        System.out.println("new phi: " + ans[2]);

        double testAngle = -730;
        testAngle = test.wrapAngle(testAngle);
        System.out.println(testAngle);
    
        // testing curve w/o movement
        /*for (int i = 0; i < maxAngle; i++){
            double v0 = test.calculateTrajectory(i, 4);
            if (!Double.isNaN(v0)){
                System.out.println(i + "," + v0);
            }
        }
       
        alpha = test.calculateAlpha(robotVelocity, shooterVelocity, beta, theta);
        System.out.println(alpha);

        velocity = test.calculateVelocity(robotVelocity, alpha, beta, theta, distance, shooterVelocity);
        System.out.println(velocity);
        */

        // stuff that didnt work... :P
        /*
        // testing movement
        double[] ans = test.solveMovingShot(distance, theta, robotVelocity, beta, tolerance, velocity, 5);
        double alpha = ans[0];        // radians
        double shooterVelocity = ans[1]; // m/s
        double timeOfFlight = ans[2]; // seconds
        
        // Display results
        System.out.println("Distance: " + distance);
        System.out.println("RobotVelocity :" + robotVelocity);
        System.out.println("\nSolutions:");
        System.out.printf("Turret Angle (α): %.2f°\n", alpha);
        System.out.printf("Shooter Velocity: %.3f m/s\n", shooterVelocity);
        System.out.printf("Time of Flight: %.3f s\n", timeOfFlight);
        */
        
        //polynomial regression
        /*double[][] testShooterData = {

            // meters, degrees
            {1.0, 12.1},
            {2.0, 22.0},
            {3.0, 36.9},    
            {4.0, 57.1},
            {5.0, 82.0}
        };

        System.out.println("test: Cubic Polynomial");
        PolynomialRegression model = new PolynomialRegression(testShooterData, 3);
    
        System.out.println("Model: " + model.toString());
        System.out.printf("R² Score: %.4f%n", model.R2());

        System.out.println("\nPredictions:");
        System.out.println("  At 1.5m: " + model.predict(1.5) + "°");
        System.out.println("  At 2.5m: " + model.predict(2.5) + "°");
        System.out.println("  At 3.5m: " + model.predict(3.5) + "°");
        System.out.println("  At 4.5m: " + model.predict(4.5) + "°");*/
    }
}