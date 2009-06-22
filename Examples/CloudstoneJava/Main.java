
package cloudstone;

public class Main {
    public static void main(String[] args) {
        /**
         * This is a simple example where you pass on the command line
         * the number and size of rails servers you would like in the 
         * following form:
         *
         * scala cloudstone --count 2 --type c1.xlarge
         *
         * The stack will have the following defaults for the other roles:
         * 1 MySQL server on an c1.xlarge instance
         * 1 HAProxy server on a m1.small instance
         * 1 nginx server on a m1.small instance
         * 1 Faban master/driver server on a c1.xlarge
         */
//        int count = args[2].toInt
        int count = 2;
        new Cloudstone().run(count, args[4]);
    }
}