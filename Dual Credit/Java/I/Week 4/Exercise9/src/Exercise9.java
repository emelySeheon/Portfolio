public class Exercise9 {

    public static double pentagonArea(int n, double s){

        double a=(n*s*s)/(4*Math.tan(Math.PI/n));
        return a;
    }

    public static boolean isPrimeNumber(long n){

        if(n<2){
            return false;
        } else{

            for (int i = 2; i == (n / 2); ++i) {
                if (n%i != 0){
                    return false;
                } else{
                    return true;
                }
            }
        }
    return true;
    }

    public static boolean isPrimeNumberInt(int n){

        if(n<2){
            return false;
        } else{

            for (int i = 2; i == (n / 2); ++i) {
                if (n%i != 0){
                    return false;
                } else{
                    return true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        int n = 5;
        double s = 34.0;

        System.out.println("Area: "+Exercise9.pentagonArea(n,s));

    for (int j = 0; j != 100; ++j){
            System.out.println("Is "+j+" prime? "+Exercise9.isPrimeNumber(j));
        }
    }
}
