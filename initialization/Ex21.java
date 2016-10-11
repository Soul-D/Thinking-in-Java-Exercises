package initialization;

public enum Ex21 {
    CENT, FIFTY_CENTS, DOLLAR, TEN_DOLLARS, FIFTY_DOLLARS, HUNDRED_DOLLARS
}

class Run{
    public static void main(String[] args) {
        for (Ex21 paper : Ex21.values()){
            System.out.println(paper + " ordinal = " + paper.ordinal());
        }
    }
}
