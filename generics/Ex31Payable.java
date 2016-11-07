//: generics/MultipleInterfaceVariants.java
package generics; /* Added by Eclipse.py */
// {CompileTimeError} (Won't compile)

interface Ex31Payable<T> {}

class Ex31Employee implements Ex31Payable {}
class Ex31Hourly extends Ex31Employee
  implements Ex31Payable {} ///:~
