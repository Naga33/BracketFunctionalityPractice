import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String expr = scanner.next();

//        TokenList tokenList = TokenList.getInstance(expr);
//        TokenListSender tls = new TokenListSender(expr);
//        tls.createSubTokenListToCalculate(); // create subtokenlist
//        Calculator calculator = new Calculator();
//
//        while(tokenList.getTokenArrayList().size()>1){
//            calculator.setSubExpression(tls.getSubTokenListToCalculate()); //give subtoken list to calculator
//            calculator.calculateFinalResult();
//            Token tempResult = calculator.getResultToken();
//            tls.updateTokenList(tempResult);
//        }


        Arithmetic compute = new Arithmetic(expr);
        compute.calculateFinalResult();
        Token finalResult = compute.getResult();
        System.out.println("final final final!: "+finalResult.toString());
    }
}
