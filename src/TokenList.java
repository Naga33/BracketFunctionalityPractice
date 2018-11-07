import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class TokenList {

    private static TokenList uniqueInstance;
    private String expression;
    private RegexList regexList = RegexList.getInstance();
    private ArrayList<Token> tokenArrayList = new ArrayList<>();
    private Map<String,Double> bindings = new HashMap<>();
    private boolean bracketsExist;


    private TokenList(String expression){
        this.expression = expression;
        createTokenArrayList();
    }

    //singleton
    public static synchronized TokenList getInstance(String expression){
        if(uniqueInstance==null){
            uniqueInstance = new TokenList(expression);
        }
        return uniqueInstance;
    }

    private void createTokenArrayList(){
        createInitialTokenList();
        replaceIds();
        printTokenArrayList();
        findBrackets();
        printDoBracketsExist();
    }


    private void createInitialTokenList(){
        while(!expression.equals("")){

            for (Regex currentRegex:regexList.getRegexArrayList()
            ) {
                //compile regex string and compare to expression
                Matcher matcher = currentRegex.getPattern().matcher(expression);

                if(matcher.find()){
                    //extract substring from expression
                    String exprExtract = matcher.group(0);

                    //add substring and token type to token list
                    tokenArrayList.add(new Token(currentRegex.getName(), exprExtract));

                    //remove substring from expression
                    expression = expression.replaceFirst(currentRegex.getValue(),"");
                }
                if(expression.equals("")){
                    break;
                }
            }
        }
        //removeNumsWithoutValue();
    }

    private void removeNumsWithoutValue(){

        ArrayList<Token> removeTokenList = new ArrayList<>();

        //add valueless tokens to removeTokenList
        for (Token token:tokenArrayList
        ) {
            if(token.getValue().equals("")){
                removeTokenList.add(token);
            }
        }

        //remove valueless tokens from tokenArrayList
        for (Token token:removeTokenList
        ) {
            tokenArrayList.remove(token);
        }

    }

    private void replaceIds(){
        for (Token token:tokenArrayList
        ) {
            if(token.getName().equals("id")){
                //check binding keys for token value
                if(bindings.containsKey(token.getValue())){
                    token.setValue(bindings.get(token.getValue()).toString());
                }
            }
        }
    }

    public void findBrackets(){
        for (Token token:tokenArrayList
             ) {
            if (token.isTokenBracket()){
                bracketsExist = true;
                break;
            }
            else{
                bracketsExist = false;
            }
        }
    }

    private void printDoBracketsExist(){
        System.out.println("Brackets: "+bracketsExist);
    }

    public boolean getBracketsExist(){
        return this.bracketsExist;
    }

//    private void createSubTokenIndexListBrackets(){
//        int outerCount = 0;
//        int innerCount = 1;
//        boolean innerCheck = true;
//
//        while(outerCount < tokenArrayList.size()){
//            System.out.println("outercount = "+outerCount);
//            Token currentToken = tokenArrayList.get(outerCount);
//
//            if(currentToken.getValue().equals("(")){
//                //add current token to subTokenListBrackets
//                subTokenIndexListBrackets.add(outerCount);
//
//                //check each token onwards from this point
//                while (innerCheck){
//                    System.out.println("innercount = "+innerCount);
//                    Token nextToken = tokenArrayList.get(innerCount);
//                    if(nextToken.getValue().equals("(")){
//                        //clear subTokenListBrackets and restart
//                        subTokenIndexListBrackets.clear();
//                        subTokenIndexListBrackets.add(innerCount);
//                        innerCount++;
//                    }
//                    if(nextToken.getValue().equals(")")){
//                        //add to subTokenIndexListBrackets and finish searching
//                        subTokenIndexListBrackets.add(innerCount);
//                        innerCheck = false;
//                    }
//                    else{
//                        subTokenIndexListBrackets.add(innerCount);
//                        innerCount++;
//                    }
//                }
//            }
//            outerCount = outerCount + innerCount;
//        }
//    }
//
//    private void createSubTokenListBrackets(){
//        if(subTokenIndexListBrackets.size()>0){
//            for (Integer index: subTokenIndexListBrackets
//            ) {
//                subTokenListBrackets.add(tokenArrayList.get(index));
//            }
//            removeBrackets();
//            printSubTokenArrayListBrackets();
//        }
//
//    }
//
//    private void removeBrackets(){
//        removeBracketsFromSubTokenListBrackets();
//        removeBracketsFromTokenArrayList();
//        removeBracketsFromSubTokenIndexListBrackets();
//    }
//
//    private void removeBracketsFromSubTokenListBrackets(){
//        subTokenListBrackets.remove(0);
//        subTokenListBrackets.remove(subTokenListBrackets.size()-1);
//        printSubTokenArrayListBrackets();
//    }
//
//    private void removeBracketsFromTokenArrayList(){
//        int openParenIndex = subTokenIndexListBrackets.get(0);
//        int closedParenIndex = subTokenIndexListBrackets.get(subTokenIndexListBrackets.size()-2);
//        tokenArrayList.remove(openParenIndex);
//        tokenArrayList.remove(closedParenIndex);
//    }
//
//    private void removeBracketsFromSubTokenIndexListBrackets(){
//        subTokenIndexListBrackets.remove(subTokenIndexListBrackets.size()-1);
//        subTokenIndexListBrackets.remove(subTokenIndexListBrackets.size()-2);
//    }
//
//    private void createSubTokenIndexList(){
//        for (int i = 0; i < tokenArrayList.size(); i++) {
//            subTokenIndexList.add(i);
//        }
//    }
//
//    private void createSubTokenList(){
//        for (Token token:tokenArrayList
//             ) {
//            subTokenList.add(token);
//        }
//    }
//
//    private void createTokenListToCalculate(){
//        //if brackets exist
//        if(subTokenListBrackets.size()>0){
//            tokenListToCalculate = subTokenListBrackets;
//        }
//        else{
//            tokenListToCalculate = subTokenList;
//        }
//    }
//
//    private void createTokenIndexListToCalculate(){
//        //if brackets exist
//        if(subTokenListBrackets.size()>0){
//            tokenIndexListToCalculate = subTokenIndexListBrackets;
//        }
//        else{
//            tokenIndexListToCalculate = subTokenIndexList;
//        }
//    }
//
//    //at this point we can send the token list to calculator to get result
//
//    //updating token list with result:
//
//    public void refreshTokenListsAfterCalculation(Token result){
//
//        tokenArrayList.set(tokenIndexListToCalculate.get(0),result);
//        int fromIndex = tokenIndexListToCalculate.get(1);
//        int toIndex = tokenIndexListToCalculate.get(tokenIndexListToCalculate.size()-1);//plus one because sublist() toIndex param is exclusive
//        tokenArrayList.subList(fromIndex,toIndex).clear();
//        printTokenArrayList();
//        refreshSubLists();
//    }
//
//    public void refreshSubLists(){
//        subTokenIndexListBrackets.clear();
//        subTokenListBrackets.clear();
//        subTokenIndexList.clear();
//        subTokenList.clear();
//    }
//
//    public void insertResultToTokenArrayList(Token result){
//
//    }
//
////    private void setResultUpdateIndex(){
////        this.resultUpdateIndex = subTokenIndexListBrackets.get(0);
////    }
//
    public ArrayList<Token> getTokenArrayList(){
        return tokenArrayList;
    }
//
//    public ArrayList<Token> getSubTokenListBrackets() { return subTokenListBrackets; }
//
//    public ArrayList<Integer> getSubTokenIndexListBrackets() { return subTokenIndexListBrackets; }
//
//    public ArrayList<Token> getTokenListToCalculate(){
//        return tokenListToCalculate;
//    }
//
//    public void setTokenArrayList(ArrayList<Token> tokenArrayList) {
//        this.tokenArrayList = tokenArrayList;
//    }
//
//    public void setSubTokenListBrackets(ArrayList<Token> subTokenListBrackets) {
//        this.subTokenListBrackets = subTokenListBrackets;
//    }
//
//    public void setSubTokenIndexListBrackets(ArrayList<Integer> subTokenIndexListBrackets) {
//        this.subTokenIndexListBrackets = subTokenIndexListBrackets;
//    }

    //sholuld this method be in this class?
    public void updateBindings(String key, Double value){
        this.bindings.put(key,value);
        printBindings();
    }

    private void printBindings() {
        System.out.println("\n\nPrinting bindings:");
        for (String bindingKey:bindings.keySet()
        ) {
            System.out.println(bindingKey+" : "+bindings.get(bindingKey));
        }
    }

    public void printTokenArrayList(){
        System.out.println("\n\nPrinting Token arraylist:");
        for (Token token:tokenArrayList
             ) {
            System.out.println(token.toString());
        }
    }

    public void printFinalResult(){
        if (tokenArrayList.size() == 1){
            //only one Token left in list: the final result!
            System.out.println("\nFinal Result!\n"+tokenArrayList.get(0).toString());
        }
    }

//    public void printSubTokenIndexListBrackets(){
//        //print
//        System.out.println("\n\nSubTokenList: ");
//        for (Integer index: subTokenIndexListBrackets
//        ) {
//            System.out.println("index: "+ index + "\nToken: "+ tokenArrayList.get(index));
//        }
//    }
//
//    public void printSubTokenArrayListBrackets(){
//        System.out.println("\n\nPrinting Sub Token arraylist:");
//        for (Token token: subTokenListBrackets
//        ) {
//            System.out.println(token.toString());
//        }
//    }
//
//    public void printTokenListToCalculate(){
//        System.out.println("\n\nPrinting tokenListToCalculate:");
//        for (Token token:tokenListToCalculate
//        ) {
//            System.out.println(token.toString());
//        }
//    }


}
