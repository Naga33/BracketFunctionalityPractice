//import java.util.ArrayList;
//
//public class Calculator {
//
//    private static Calculator uniqueInstance;
//    private TokenList tokenList;
//    private ArrayList<Token> calculationTokenList;
//    private String[] operationOrder = {"divide", "multiply", "sum", "subtract"};
//    private Token resultToken;
//
//    private Calculator(String expression){
//        this.tokenList = TokenList.getInstance(expression);
//        this.calculationTokenList = tokenList.getTokenListToCalculate();
//    }
//
//    //singleton
//    public static synchronized Calculator getInstance(String expression){
//        if(uniqueInstance==null){
//            uniqueInstance = new Calculator(expression);
//        }
//        return uniqueInstance;
//    }
//
//    public void solveExpression(){
//
//        //TODO: remove this while??
//        while(tokenList.getTokenArrayList().size()>1){
//
//            for (int i = 0; i < operationOrder.length; i++) {
//                calculateOperators(operationOrder[i]);
//            }
//            calculateAssign();
//            tokenList.refreshTokenListsAfterCalculation(resultToken);
//        }
//        saveResultToBinding();
//    }
//
//
//    private void calculateOperators(String operation){
//        String resultTokenType = "num";
//        CalcTree calculation;
//        //TODO: COMPLETELY FORGOT THAT MULTIPLE SUMS CAN BE SENT E.G. 4-2+6
//        //calculationTokenList is sometimes 0
//        Token firstNum = calculationTokenList.get(0);
//        Token operator = calculationTokenList.get(1);
//        Token secondNum = calculationTokenList.get(2);
//        Token tempResult = null;
//
//        if(operator.getName().equals(operation)){
//
//            calculation = new CalcTree(operator.getValue(),
//                    new CalcTree(firstNum.getValue(), null, null),
//                    new CalcTree(secondNum.getValue(), null, null));
//
//            Double result = calculation.eval(calculation);//this can't be good practice...
//            System.out.println("\n\nResult = "+result);
//
//            tempResult = new Token(resultTokenType,result.toString());
//        }
//        if(tempResult!=null){
//            resultToken = tempResult;
//        }
//    }
//
//    private void calculateAssign(){
//        String resultTokenType = "num";
//        Token id = calculationTokenList.get(0);
//        Token assign = calculationTokenList.get(1);
//        Token value = calculationTokenList.get(2);
//        Token tempResult = null;
//
//        if(assign.getName().equals("assign")){
//            Double bindValue = Double.parseDouble(value.getValue());
//            String bindKey = id.getValue();
//            tokenList.updateBindings(bindKey,bindValue);
//            tempResult = new Token(resultTokenType,value.toString());
//        }
//        if(tempResult!=null){
//            resultToken = tempResult;
//        }
//    }
//
//    private void saveResultToBinding(){
//        if(tokenList.getTokenArrayList().size()==1){
//            String finalValue = tokenList.getTokenArrayList().get(0).getValue();
//            Double finalResult = Double.parseDouble(finalValue);
//            tokenList.updateBindings("_",finalResult);
//            //bindings.put("_",finalResult);
//        }//needs to be saved but won't need serialisation in their version.
//    }
//}
