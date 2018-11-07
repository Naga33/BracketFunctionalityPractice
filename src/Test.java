import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String expr = scanner.next();

//        Arithmetic computer = new Arithmetic(expr);
//        computer.calculateFinalResult();
//        Token finalResult = computer.getResult();
//        System.out.println(finalResult.toString());
//
        TokenList tokenList = TokenList.getInstance(expr);

        boolean brackets = tokenList.getBracketsExist();
        System.out.println(brackets);

        TokenListSender tls = new TokenListSender(expr);
        tls.createSubTokenListToCalculate(); // create subtokenlist




        while(tokenList.getTokenArrayList().size()>1){

            System.out.println("\n\nsub token list to calculate:");
            for (Token token:tls.getSubTokenListToCalculate()
            ) {
                System.out.println(token);
            }

            Calculator calculator = new Calculator(tls.getSubTokenListToCalculate()); //give subtoken list to calculator

            calculator.calculateFinalResult();
            Token tempResult = calculator.getResultToken();

            tls.updateTokenList(tempResult); //should update token list with result and refresh subtoken list




        }

    }
}
