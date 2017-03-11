package ru.stqa.pft.sandbox;

/**
 * Created by Митрич on 11.03.2017.
 */
public class Point {

  public double x;
  public double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public double distance(Point p){
    return Math.sqrt(Math.pow((p.x - this.x), 2) + Math.pow((p.y - this.y), 2));
  }

  /*public static double distance(Point p1, Point p2){
    double c = (p2.x - p1.x) * (p2.x - p1.x);
    double d = (p2.y - p1.y) * (p2.y - p1.y);
    double s = Math.sqrt(c + d);
    return s;
  }*/
}
