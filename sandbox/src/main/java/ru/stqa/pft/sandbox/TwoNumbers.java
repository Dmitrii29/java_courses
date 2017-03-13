package ru.stqa.pft.sandbox;

/**
 * Created by Митрич on 13.03.2017.
 */
public class TwoNumbers {
  public double x;
  public double y;

  public TwoNumbers(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double plus(){
    double s = this.x + this.y;
    return s;
  }
}
