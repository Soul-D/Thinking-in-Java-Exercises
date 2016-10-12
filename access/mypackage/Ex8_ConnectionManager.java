package access.mypackage;

public class Ex8_ConnectionManager {
    static Ex8_Connection[] arr = {new Ex8_Connection(), new Ex8_Connection(), new Ex8_Connection()};

    public static Ex8_Connection getConnection(){
        if (arr.length != 0) {
            Ex8_Connection result = arr[0];
            Ex8_Connection[] arr2 = new Ex8_Connection[arr.length-1];
            System.arraycopy(arr,1,arr2,0,arr.length-1);
            arr = arr2;
            return result;
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
        System.out.println(getConnection());
        System.out.println(getConnection());
        System.out.println(getConnection());
    }
}

class Ex8_Connection {
    Ex8_Connection(){}
}
