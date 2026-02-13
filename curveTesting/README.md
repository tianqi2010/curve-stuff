This is all assuming that the turret will always be aligned with the hub, and that we have distance from robot to target.
would be nice to be able to convert rpm to velocity(m/s). if somehow not then cannot do when velocity /= 0;

When robot velocity = 0;
test hood angle and velocity (rpm)
for velocity, we could either
- map that to distance (probably more reliable, will take a bit more testing tho)
- use projectile motion equation using given hood angle (less reliable)

map the cubic/curve using data points, and use that to determine hood angle and velocity based off of distance 

When robot velocity /= 0;
depends on knowing stats from when robot velocity = 0

will need 
angle difference between the vector of robot velocity and vector of robot to hub (phi)
hood angle when robot velocity = 0;
shooter velocity when robot velocity = 0
robot velocity

