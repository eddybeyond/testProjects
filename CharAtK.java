import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CharAtK {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        //First N line:   (number of strings in the list)
        int n = Integer.valueOf(scan.nextLine());
        //Next  N lines: Si
        ArrayList<String> nLines = new ArrayList<String>();
        for(int i=0; i<n;i++){
            String si = scan.nextLine();
            nLines.add(si);
        }
        //Next line:  Q (number of questions)
        int q = Integer.valueOf(scan.nextLine());
        //Next Q lines: Three space-separated integers,  L, R, and K
        ArrayList<String> qLines = new ArrayList<String>();
        for(int j=0;j<q;j++){
            qLines.add( scan.nextLine());
        }

        for(String ss:qLines){
            if (!ss.equals("")) {
                String[] lrk = ss.split(" ");
                int l = Integer.valueOf(lrk[0]);
                int r = Integer.valueOf(lrk[1]);
                int k = Integer.valueOf(lrk[2]);

                String substring = concatAlphs(l, r, nLines);
                System.out.println(substring.toCharArray()[k]);
            }
        }

    }

    private static String concatAlphs(int x, int y, ArrayList<String> alph) {
        List<String> alphfiltered = alph.subList(x, y + 1);
        alphfiltered.sort((o1, o2) -> o1.compareTo(o2));//sort by alphabeth
        StringBuffer sb = new StringBuffer();
        alphfiltered.forEach(s -> sb.append(s));
        char tempArray[] = sb.toString().toCharArray();
        Arrays.sort(tempArray);//sort letter by letter
        return new String(tempArray);
    }

}
