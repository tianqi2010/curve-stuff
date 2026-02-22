import java.nio.channels.Pipe.SinkChannel;

public class Test {

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

}
