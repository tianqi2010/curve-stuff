public class Main {

    public static int maxAngle = 90;
    public static final double theta = 60; 
    public static double beta = 45;
    public static double robotVelocity = 2; //m/s
    public static double distance = 1; //m
    public static double tolerance = 0.01;
    public static double workingVelocity;
    public static double workingAlpha;

    public static double alpha;
    public static double newAlpha;
    public static double velocity = 9; //m/s
    public static double newVelocity;

    public static void main(String[] args) throws Exception {
        
        Test test = new Test();

        // for (int i = 0; i < maxAngle; i++){
        //     double v0 = test.calculateTrajectory(i, 4);
        //     if (!Double.isNaN(v0)){
        //         System.out.println(i + "," + v0);
        //     }
        // }
       
        //60 5.92154343146653

        // alpha = test.calculateAlpha(robotVelocity, shooterVelocity, beta, theta);
        // System.out.println(alpha);

        // velocity = test.calculateVelocity(robotVelocity, alpha, beta, theta, distance, shooterVelocity);
        // System.out.println(velocity);

        
        System.out.println("velocity:" + velocity);

        alpha = test.calculateAlpha(robotVelocity, velocity, beta, theta);

        System.out.println("alpha:" + alpha);
        
        newVelocity = test.calculateVelocity(robotVelocity, newAlpha, beta, theta, distance, velocity);
        newVelocity = newVelocity * 0.2 + velocity * 0.8;

        System.out.println("newVelocity:" + newVelocity);

        newAlpha = test.calculateAlpha(robotVelocity, newVelocity, beta, theta);

        System.out.println("newAlpha:" + newAlpha);

        for (int i = 0; i < 50; i++){

            alpha = newAlpha;
            velocity = newVelocity;

            newVelocity = test.calculateVelocity(robotVelocity, newAlpha, beta, theta, distance, velocity);
            newVelocity = newVelocity * 0.2 + velocity * 0.8;
            newAlpha = test.calculateAlpha(robotVelocity, newVelocity, beta, theta);

            if (!Double.isNaN(newVelocity)){
                System.out.println("newVelocity:" + i + " " + newVelocity);
                System.out.println("newAlpha:" + i + " " + Math.toDegrees(newAlpha));
                workingAlpha = newAlpha;
                workingVelocity = newVelocity;
            }
        }

        System.out.println("Final velocity = " + workingVelocity);
        System.out.println("Final alpha = " + Math.toDegrees(workingAlpha));   

    }
}