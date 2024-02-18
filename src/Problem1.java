public class Problem1 {
    public static int findIntSolutions(int n) {
        int number = 0;

        for (int a = n + 1; a < n * (n + 1) + 1; a++) {
            double b = (double) (n * a) / (a - n);

            if (b > 0 && b == Math.floor(b)) {
                //System.out.println("Soln for N: (" + a + ", " + (int) b + ")");
                number++;

                if (a == b) {
                    return number*2 -1;
                }
            }
        }
        return number;
    }


    public static void main(String[] args) {

        int a = 4;
        int b = 2;

        int[] nums = new int[]{
                60
//                (int) (Math.pow(2, 1) * Math.pow(3, 1) * Math.pow(5, 2) * Math.pow(7,2)),
//                (int) (Math.pow(2, a) * Math.pow(3, b) * Math.pow(5, 2)),
//                (int) (Math.pow(2, a) * Math.pow(3, b) * Math.pow(5, 3)),
        };

        for (int num : nums) {
            int sol = findIntSolutions(num);
            //System.out.println(num + " : " + a + "(" + (a+1)/2 + " unique solns)");
            System.out.println(num + " : " + sol);
        }


    }
}

