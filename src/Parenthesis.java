import java.util.Arrays;
import java.util.Stack;

public class Parenthesis {
    public String reverse(String s ){
        String[]res=s.split(" ");

        StringBuilder tem = new StringBuilder();
        for(int i = res.length-1 ;i>=0 ;i--){
            tem.append(res[i]).append(" ");
        }
    return tem.toString();
    }
    // 有效的括号匹配
    public boolean chkParenthesis(String A, int n) {
        Stack<Character> stack = new Stack<>();
        for(int i = 0 ; i <n;i++){
            if(A.charAt(i)=='(') {
                stack.push(A.charAt(i));
            }else if(A.charAt(i)==')'){
                if(stack.isEmpty())
                    return false;
                stack.pop();
            }else{
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "(()";
        Parenthesis p = new Parenthesis();
        System.out.println(p.chkParenthesis(s,3));
        String ss = " I like Beijing";
        System.out.println(p.reverse(ss));
    }
}
