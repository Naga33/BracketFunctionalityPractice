import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String expr = scanner.next();

        TokenList tokenList = TokenList.getInstance(expr);

        boolean brackets = tokenList.getBracketsExist();
        System.out.println(brackets);

        TokenListSender tls = new TokenListSender(expr);
//        ArrayList<Integer> indexList = tls.createBracketsIndexList();
//
//        for (Integer num:indexList
//             ) {
//            System.out.println(num);
//        }
//
//        ArrayList<Token> tokenListTest = tls.createBracketsTokenList(indexList);
//
//        for (Token token:tokenListTest
//        ) {
//            System.out.println(token);
//        }

        tls.createSubTokenListToCalculate();

        System.out.println("\n\nsub token list to calculate:");
        for (Token token:tls.getSubTokenListToCalculate()
             ) {
            System.out.println(token);
        }

        Token tempResult = new Token("num","5");

        tls.updateTokenList(tempResult);
        //print
        tokenList.printFinalResult();

        tokenList.printTokenArrayList();

        System.out.println("\n\nsub token list to calculate:");
        for (Token token:tls.getSubTokenListToCalculate()
        ) {
            System.out.println(token);
        }









        //tokenList.refreshTokenArrayListAfterBrackets(resultTest);



    }
}
