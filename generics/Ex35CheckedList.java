package generics;

import typeinfo.coffee.Coffee;
import typeinfo.coffee.Latte;
import typeinfo.coffee.Mocha;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ex35CheckedList {
  @SuppressWarnings("unchecked")
  static void oldStyleMethod(List probablyMocha) {
    probablyMocha.add(new Latte());
  }	
  public static void main(String[] args) {
    List<Mocha> mochas = new ArrayList<Mocha>();
    oldStyleMethod(mochas); // Quietly accepts a Cat
    List<Mocha> mochas1 = Collections.checkedList(
      new ArrayList<Mocha>(), Mocha.class);
    try {
      oldStyleMethod(mochas1); // Throws an exception
    } catch(Exception e) {
      System.out.println(e);
    }
    // Derived types work fine:
    List<Coffee> coffees = Collections.checkedList(
      new ArrayList<Coffee>(), Coffee.class);
    coffees.add(new Mocha());
    coffees.add(new Latte());
  }
}