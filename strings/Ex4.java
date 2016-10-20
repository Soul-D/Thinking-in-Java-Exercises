//: strings/Receipt.java
package strings; /* Added by Eclipse.py */

import java.util.Formatter;

public class Ex4 {
  private static final int ITEM_WIDTH = 20;
  private static final int QTY_WIDTH = 10;
  private static final int PRICE_WIDTH = 15;
  private static final int ITEM_PRECISION = 15;
  private static final int PRICE_PRECISION = 2;
  private static final String TITLE_FORMAT = "%-" + ITEM_WIDTH +"s %" + QTY_WIDTH +"s %" + PRICE_WIDTH + "s\n";
  private static final String PRINT_FORMAT = "%-" + ITEM_WIDTH + "." + ITEM_PRECISION + "s %" + QTY_WIDTH + "d %" + PRICE_WIDTH + "." + PRICE_PRECISION + "f\n";
  private static final String PRINT_TOTAL_FORMAT = "%-" + ITEM_WIDTH + "s %" + QTY_WIDTH + "s %" + PRICE_WIDTH + "." + PRICE_PRECISION + "f\n";
  private double total = 0;
  private Formatter f = new Formatter(System.out);
  public void printTitle() {
    f.format(TITLE_FORMAT, "Item", "Qty", "Price");
    f.format(TITLE_FORMAT, "----", "---", "-----");
  }
  public void print(String name, int qty, double price) {
    f.format(PRINT_FORMAT, name, qty, price);
    total += price;
  }
  public void printTotal() {
    f.format(PRINT_TOTAL_FORMAT, "Tax", "", total*0.06);
    f.format(TITLE_FORMAT, "", "", "-----");
    f.format(PRINT_TOTAL_FORMAT, "Total", "",
      total * 1.06);
  }
  public static void main(String[] args) {
    Ex4 receipt = new Ex4();
    receipt.printTitle();
    receipt.print("Jack's Magic Beans", 4, 4.25);
    receipt.print("Princess Peas", 3, 5.1);
    receipt.print("Three Bears Porridge", 1, 14.29);
    receipt.printTotal();
  }
} /* Output:
Item              Qty      Price
----              ---      -----
Jack's Magic Be     4       4.25
Princess Peas       3       5.10
Three Bears Por     1      14.29
Tax                         1.42
                           -----
Total                      25.06
*///:~
