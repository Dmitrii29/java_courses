package ru.stqa.pft.sandbox;

public class MyFirstProgram {

  public static void main(String[] args) {
    hello("world");
    hello("user");
    hello("Dmitrii");


    Square s = new Square(5);
    System.out.println("Площадь квадрата со стороной " + s.l + " = " + s.area());

    Rectangle r = new Rectangle(4 , 6);
    System.out.println("Площадь прямоугольника со сторонами " + r.a + " и " + r.b + " = " + r.area());

    Point p1 = new Point(0, 0);
    Point p2 = new Point(17, 22);
    System.out.println("Расстояние между точками p1 и p2 = " + p1.distance(p2));

    TwoNumbers c1 = new TwoNumbers(15,2);
    TwoNumbers c2 = new TwoNumbers(10, 5);
    System.out.println("Сумма = " + c1.plus());
    System.out.println("Сумма = " + c2.plus());
  }

  public static void hello(String somebody) {
    System.out.println("Hello, " + somebody + "!");
  }

}