package lesson07;

/**
 * denis
 * 27.11.2016.
 */
public class MainConcurrency {

    public static void main(String[] args) {

        for (int i = 1; i < 100; i++) {

/*            new Thread() {
                @Override
                public void run() {
                    System.out.println(this.getName());
                }
            }.start();*/

            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("out");
                }
            }).start();

        }

    }

}
