public class calcLoops {

  public static void main(String[] args) {
    int num = 0;
    int n = 10;

    for (int i = 1; i<=n; i=i*2) {
      for (int j=1; j<=n; j=j+4){
        num = num + i;
        System.out.println(num);
      }
    }

  }

}
