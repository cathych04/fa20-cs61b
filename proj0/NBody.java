/** @author Catherine Chao */

public class NBody{

  /**Given a file, return a double corresponding to the raidus of the universe in that file*/
  public static double readRadius(String file){
    In in = new In(file);

    int numPlanets = in.readInt();
    double radius = in.readDouble();
    return radius;
  }

  /**Given a file, return an array of Bodys corresponding to the bodies in that file*/
  public static Body[] readBodies(String file){
    In in = new In(file);

    int i = 0;
    int numPlanets = in.readInt();
    double radius = in.readDouble();
    Body[] Bodies = new Body[numPlanets];

    while (i < Bodies.length){
      double xP = in.readDouble();
      double yP = in.readDouble();
      double xV = in.readDouble();
      double yV = in.readDouble();
      double m = in.readDouble();
      String planet = in.readString();

      Body current = new Body(xP, yP, xV, yV, m, planet);
      Bodies[i] = current;
      i = i + 1;
        }
        return Bodies;
      }

    /**Produces an animation of Universe filename, shows planet's position after every dt amount
    of time until time T*/
    public static void main(String[] args) {
      double T = new Double(args[0]);
      double dt = new Double(args[1]);
      String filename = args[2];

      double radius = readRadius(filename);
      Body[] BArray = readBodies(filename);

      /**Create an animation of the Universe; assigns planet's net force, updates the Body's Pos,Vel,Accel
      then sets the background and draws all the planets*/
      double[] xForces = new double[BArray.length];
      double[] yForces = new double[BArray.length];

      StdDraw.enableDoubleBuffering();

      for (double time = 0; time <= T; time = time + dt){
        for (int i = 0; i < BArray.length; i++) {
          xForces[i] = BArray[i].calcNetForceExertedByX(BArray);
          yForces[i] = BArray[i].calcNetForceExertedByY(BArray);
        }

        for (int k = 0; k < BArray.length; k++){
          BArray[k].update(time, xForces[k], yForces[k]);
        }

        StdDraw.setScale(-radius, radius);
        StdDraw.clear();
        StdDraw.picture(0, 0, "images/starfield.jpg");
        StdDraw.show();

        for (int a = 0; a < BArray.length; a++){
          BArray[a].draw();
        }
        StdDraw.enableDoubleBuffering();
        StdDraw.show();
        StdDraw.pause(100);
      }

      /**Print out the final state of the universe*/
      StdOut.printf("%d\n", BArray.length);
      StdOut.printf("%.2e\n", radius);
      for (int i = 0; i < BArray.length; i++) {
        StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
        BArray[i].xxPos, BArray[i].yyPos, BArray[i].xxVel,
        BArray[i].yyVel, BArray[i].mass, BArray[i].imgFileName);
      }


      /**Draw the image starfield.jpg*/
      StdDraw.setScale(-radius, radius);
      StdDraw.clear();
      StdDraw.picture(0, 0, "images/starfield.jpg");
      StdDraw.show();

      /**Draw each bodies in the Body array created*/
      for (int i = 0; i < BArray.length; i = i + 1){
        BArray[i].draw();
        StdDraw.show();
      }
    }


}
