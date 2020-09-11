/** @author Catherine Chao */

public class Body {

  /**Its current x position*/
  public double xxPos;

  /**Its current y position*/
  public double yyPos;

  /**Its crrent velocity in the x direction*/
  public double xxVel;

  /**Its current velocity in the y direction*/
  public double yyVel;

  /**Its mass*/
  public double mass;

  /**The name of the file that corresponds to the image that depicts the body*/
  public String imgFileName;

  /**Body Class constructor */
  public Body(double xP, double yP, double xV, double yV, double m, String img){

    xxPos = xP;
    yyPos = yP;
    xxVel = xV;
    yyVel = yV;
    mass = m;
    imgFileName = img;
  }

  /**Take in Body object and initialize an identical Body object (copy)*/
  public Body(Body b){

      this.xxPos = b.xxPos;
      this.yyPos = b.yyPos;
      this.xxVel = b.xxVel;
      this.yyVel = b.yyVel;
      this.mass = b.mass;
      this.imgFileName = b.imgFileName;
  }

  /**Calculate the distance between two Bodys*/
  public double calcDistance (Body given){
    double dx = given.xxPos - this.xxPos;
    double dy = given.yyPos - this.yyPos;
    double sqrR = Math.pow(dx, 2) + Math.pow(dy, 2);

    return Math.pow(sqrR , 0.5);
  }

  /**Takes in a Body and returns a double describing the force exerted on this body by the given body*/
  public double calcForceExertedBy (Body given){
    double G = 6.67e-11;
    double r = this.calcDistance(given);
    double F = (G*this.mass*given.mass) / Math.pow(r, 2);

    if (this.equals(given)){
      return 0;
    }
    return F;
  }

  /**Returns the Force exerted in the X direction*/
  public double calcForceExertedByX (Body given){
    double F = this.calcForceExertedBy(given);
    double dx = given.xxPos - this.xxPos;
    double r = this.calcDistance(given);
    double Fx = (F*dx)/r;

    if (this.equals(given)){
      return 0;
    }
    return Fx;
  }

  /**Returns the Force exerted in the Y direction*/
  public double calcForceExertedByY (Body given){
    double F = this.calcForceExertedBy(given);
    double dy = given.yyPos - this.yyPos;
    double r = this.calcDistance(given);
    double Fy = (F*dy)/r;

    if (this.equals(given)){
      return 0;
    }
    return Fy;
  }

  /**Takes in an array of Bodys and calculates the net X force exerted by all bodies in that array upon the current Body.*/
  public double calcNetForceExertedByX (Body[] array){
    double FnetX = 0;

    for (int i = 0; i < array.length; i = i + 1){
      Body given = array[i];
      FnetX = FnetX + this.calcForceExertedByX(given);
    }
    return FnetX;
  }

  /**Takes in an array of Bodys and calculates the net Y force exerted by all bodies in that array upon the current Body.*/
  public double calcNetForceExertedByY (Body[] array){
    double FnetY = 0;

    for (int i = 0; i < array.length; i = i + 1){
      Body given = array[i];
      FnetY = FnetY + this.calcForceExertedByY(given);
    }
    return FnetY;
  }

  /**Update the Body's velocity and position after the Body is applied X and Y Force for dt amount of time*/
  public void update(double dt, double Fx, double Fy){
    double AccelX = Fx / this.mass;
    double AccelY = Fy / this.mass;

    this.xxVel = this.xxVel + dt*AccelX;
    this.yyVel = this.yyVel + dt*AccelY;
    this.xxPos = this.xxPos + dt*this.xxVel;
    this.yyPos = this.yyPos + dt*this.yyVel;
  }

  /**Uses the StdDraw API to draw a Body's image at the Body's position.*/
  public void draw(){
    StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
  }

}
