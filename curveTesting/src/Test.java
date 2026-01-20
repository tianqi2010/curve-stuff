public class Test {

    //y difference should be from top of shooter to height of hub
    public final double HUB_HEIGHT = 1.8288; // m
    public final double SHOOTER_HEIGHT = 0.6096; // kitbot height i think
    public final double y = HUB_HEIGHT - SHOOTER_HEIGHT;
    public final double g = 9.81;


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
    public double[] solveMovingShot(double distance, double thetaDeg, 
                                   double robotVelocity, double betaDeg,
                                   double tolerance, int maxIterations) {
        
        // Uses robotVelocity = 0 as an initial guess 
        double shooterVelocity = calculateTrajectory((int)thetaDeg, distance);
        if (Double.isNaN(shooterVelocity)) {
            shooterVelocity = 10.0; // fallback
        }
        
        double alpha = 0.0;
        double prevVelocity, prevAlpha;
        boolean converged = false;
        
        for (int i = 0; i < maxIterations; i++) {
            prevVelocity = shooterVelocity;
            prevAlpha = alpha;
            
            alpha = calculateAlpha(robotVelocity, shooterVelocity, betaDeg, thetaDeg);

            if (Double.isNaN(alpha) || Math.abs(alpha) > Math.PI/2) {
                shooterVelocity *= 1.1; 
                continue;
            }
            
            double time = time(thetaDeg, shooterVelocity);
            if (Double.isNaN(time) || time <= 0) {
                shooterVelocity *= 1.1; 
                continue;
            }
            
            double newVelocity = calculateVelocity(robotVelocity, alpha, betaDeg, thetaDeg, distance, shooterVelocity);
            
            if (Double.isNaN(newVelocity) || newVelocity <= 0) {
                shooterVelocity *= 1.1;
                continue;
            }
            
            shooterVelocity = 0.2 * newVelocity + 0.8 * shooterVelocity;
            
            double velocityDiff = Math.abs(shooterVelocity - prevVelocity);
            double alphaDiff = Math.abs(alpha - prevAlpha);
            
            if (velocityDiff < tolerance && alphaDiff < Math.toRadians(0.1)) {
                converged = true;
                break;
            }
            
            if (shooterVelocity > 100) {
                System.err.println("Velocity too high; not plausable");
                break;
            }
        }
        
        if (!converged) {
            System.err.println("didn't fully converge");
        }
        
        // Calculate final time of flight
        double finalTime = time(thetaDeg, shooterVelocity);
        
        return new double[]{alpha, shooterVelocity, finalTime};
    }
    
    public double calculateTrajectory(int thetad, double d){

        double initialVelocity;
        
        double thetar = Math.toRadians(thetad);

        initialVelocity = Math.sqrt((g * Math.pow(d, 2)) / (2 * Math.pow(Math.cos(thetar), 2) * (d * Math.tan(thetar) - y)));

        return initialVelocity;

    }

    /*
    beta - angle from vector of velocity of robot to line from robot to target
    
    
    */
    public double calculateAlpha(double robotVelocity, double shooterVelocity, double beta, double theta){

        double alpha;
        double betar = Math.toRadians(beta);
        double thetar = Math.toRadians(theta);

        alpha = Math.asin((robotVelocity * Math.sin(betar)) / (shooterVelocity * Math.cos(thetar)));

        return alpha;
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
}
