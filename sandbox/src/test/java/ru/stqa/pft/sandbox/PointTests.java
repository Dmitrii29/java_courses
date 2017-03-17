package ru.stqa.pft.sandbox;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by Митрич on 17.03.2017.
 */
public class PointTests {

  @Test
  public void testDistance2(){
    Point p1 = new Point(10,23);
    Point p2 = new Point(17,22);
    Assert.assertEquals(p2.distance(p1), 7.0710678118654755);
  }

  @Test
  public void testDistance(){
    Point p1 = new Point(10,23);
    Point p2 = new Point(17,22);
    Assert.assertEquals(Math.round(p1.distance(p2)), 7);
  }
}

