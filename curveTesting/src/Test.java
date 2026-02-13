import java.nio.channels.Pipe.SinkChannel;

public class Test {

    //y difference should be from top of shooter to height of hub
    public final double HUB_HEIGHT = 1.8288; // m
    public final double SHOOTER_HEIGHT = 0.6096; // kitbot height i think
    public final double y = HUB_HEIGHT - SHOOTER_HEIGHT;
    public final double g = 9.81;

 
    public double calculateTrajectory(int thetad, double d){

        double initialVelocity;
        
        double thetar = Math.toRadians(thetad);

        initialVelocity = Math.sqrt((g * Math.pow(d, 2)) / (2 * Math.pow(Math.cos(thetar), 2) * (d * Math.tan(thetar) - y)));

        return initialVelocity;

    }

    /**
    * @param shootingVelocity velocity needed to shoot the ball while robotVelocity = 0, (m/s)
    * @param robotVelocity robot's velocity (m/s)
    * @param theta polar angle (degrees)
    * @param phi azimuthal angle (degrees)
    * @return double[3], [0] = new velocity (m/s), [1] = new polar angle (degrees), [2] = new azimuthal angle (degrees)
    */


    // subtracts the shootingvelocity vector to robotvelocity vector to get the new velocity vector that hopefully gets the ball in
    public double[] calculateMovingShot(double shootingVelocity, double robotVelocity, double theta, double phi){
        
        double thetar = Math.toRadians(theta);
        double phir = Math.toRadians(phi);

        double x = shootingVelocity * Math.cos(thetar) - robotVelocity * Math.cos(phir);
        double y = -robotVelocity * Math.sin(phir);
        double z = shootingVelocity * Math.sin(thetar);

        double newShootingVelocity = Math.sqrt(x * x + y * y + z * z);
        // double newTheta = Math.toDegrees(Math.atan(z / x));
        // double newPhi = Math.toDegrees(Math.atan(y / x));
        double newTheta = Math.toDegrees(Math.atan2(z, x));
        double newPhi = Math.toDegrees(Math.atan2(y, x));

        return new double[]{newShootingVelocity, newTheta, newPhi};
    }

    public double wrapAngle(double angle){
        angle %= 360.0;
        if (angle < -180.0) {
            angle += 360.0;
        } else if (angle >= 180.0) {
            angle -= 360.0;
        }
        return angle;      
    }
    

    /**
     * Tries to get the values to converge, thus iterating through the two equations
     * increases velocity when alpha, newvelocity, time, etc. are bad
     * @param distance Distance to target (m)
     * @param thetaDeg Launch angle (degrees)
     * @param robotVelocity Robot speed (m/s)
     * @param betaDeg Angle between robot velocity and line to target (degrees)
     * @param tolerance Convergence tolerance for velocity (m/s)
     * @param maxIterations Maximum number of iterations
     * @return double[3] where [0]=alpha (radians), [1]=shooterVelocity (m/s), [2]=timeOfFlight (s)
     */


    // things that just didnt work... :)
    /*
    public double[] solveMovingShot(double distance, double theta,
                               double robotVelocity, double beta,
                               double tolerance, double velocity, int maxIterations) {
    
    
        double initialVelocity = velocity;
        double newVelocity = velocity;
        double alpha, newAlpha = 0.0;

        for (int i = 0; i < maxIterations; i++){


            alpha = calculateAlpha(robotVelocity, initialVelocity, beta, theta);
            System.out.println("alpha " + i + " " + alpha);
            
            System.out.println("time: " + i + " " + time(theta, newVelocity));

            newVelocity = calculateVelocity(robotVelocity, alpha, beta, theta, distance, initialVelocity);
            newVelocity = newVelocity * 0.3 + initialVelocity * 0.7;

            System.out.println("newVelocity " + i + " " + newVelocity);

            newAlpha = calculateAlpha(robotVelocity, newVelocity, beta, theta);

            System.out.println("newAlpha " + i + " " + newAlpha);

            initialVelocity = newVelocity;
            alpha = newAlpha;
        }                        
    
        double finalTime = time(theta, newVelocity);
    
        return new double[]{newAlpha, newVelocity, finalTime};
    }

    public double calculateAlpha(double robotVelocity, double shooterVelocity, double beta, double theta){

        double betar = Math.toRadians(beta);
        double thetar = Math.toRadians(theta);

        double sinAlpha = (robotVelocity * Math.sin(betar)) / (shooterVelocity * Math.cos(thetar));

        // if (Math.abs(sinAlpha) > 1.0){
        //     return 0.0;
        // }

        sinAlpha = Math.max(-1.0, Math.min(1.0, sinAlpha));

        return Math.toDegrees(Math.asin(sinAlpha));
    }   

    public double calculateVelocity (double robotVelocity, double alpha, double beta, double theta, double distance, double shooterVelocity){

        double velocityShooter; 

        double alphar = Math.toRadians(alpha);
        double betar = Math.toRadians(beta);
        double thetar = Math.toRadians(theta);
    
        double time = time(theta, shooterVelocity);
        
        velocityShooter = ((distance/time - robotVelocity * Math.cos(betar)) / (Math.cos(thetar) * Math.cos(alphar)));

        return velocityShooter;
    }
    
    public double time(double theta, double shooterVelocity){
    
        double thetar = Math.toRadians(theta);

        double a = g/2;
        double b = -shooterVelocity * Math.sin(thetar);
        double c = y;

        double discriminant = Math.pow(b, 2) - (4*a*c);

        if (discriminant < 0){
            return Double.NaN;
        }

        double timeNeg = (-b - Math.sqrt(discriminant)) / (2*a); 
        double timePlus = (-b + Math.sqrt(discriminant)) / (2*a); 

        return Math.max(timePlus, timeNeg);
    }
    */
}
