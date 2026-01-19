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

        double timePlus, timeNeg;
        double a = g/2;
        double b = -shooterVelocity * Math.sin(thetar);
        double c = y;

        double discriminant = Math.pow(b, 2) - (4*a*c);

        if (discriminant < 0){
            return Double.NaN;
        }

        timePlus = ( - b + Math.sqrt(discriminant) ) / (2*a);
        timeNeg = (- b - Math.sqrt(discriminant) ) / (2*a);

        return Math.max(timePlus, timeNeg);
    }
}
