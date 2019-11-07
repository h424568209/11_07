import java.util.Scanner;

public class Main {
    //找到字符串中最长的数字字符串
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.next();
        String max = "";
        int j =0 ;
        for(int i =0  ; i <s.length();){
             i = j;
            while(i<s.length()&&(s.charAt(i)>='a'&&s.charAt(i)<='z')){
                i++;
              }
                 j = i;
                while(j<s.length()&&s.charAt(j)>='0'&&s.charAt(j)<='9') {
                    j++;
                }
               max = longth(s,i,j,max);
        }
        System.out.println(max);
    }

    private static String longth(String s,int i , int j,String max) {
       if(s.substring(i,j).length()>max.length()){
           return s.substring(i,j);
       }
       return max;
    }
}
