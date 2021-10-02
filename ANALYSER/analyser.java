import java.io.FileReader;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.regex.*; 

public class analyser {
    static ArrayList<String> tokens = new ArrayList<String>(); //Arraylist to store tokens
    
    public static void analyse(String line){
        //code to create symboltable
        ArrayList<String> keywords = new ArrayList<String>(Arrays.asList("and","as","assert","break", "class", "continue", "def", "del", "del", "elif", "else", "except", "False", "finally", "for", "from", "global", "if", "import", "in", "lambda", "None", "nonlocal", "not", "or", "pass", "raise", "return", "True", "try", "while", "with", "yield"));
        ArrayList<String> operators = new ArrayList<String>(Arrays.asList("+","-","*","/","%","**","//","=","+=","-=","*=","/=","%=","//=","**=","&=","|=","^=",">>=","<<=","==","!=",">","<",">=","<=", "and", "or", "not", "is", "is not", "in", "not in","&","|","^","~","<<",">>"));
        for (String val: line.split("[' '.]+")) //split by whitespace, dot
        {
            if(keywords.contains(val)) //checks keyword
            {
                tokens.add("<keyword, "+ val +" >");
            }
            else if(Pattern.matches("(\")+(.)*(\")",val) || Pattern.matches("(\')+(.)*(\')",val)) //checks string constant
            {
                tokens.add("<sconst, "+ val +" >");
            }
            else if(Pattern.matches("([a-z]|[A-Z]|[_])+([0-9])*", val))//checks identifier
            {
                tokens.add("<id, "+ val +" >");
            }
            else if(Pattern.matches("[0-9]+", val))//checks number constant
            {
                tokens.add("<nconst, "+ val +" >");
            }
            else if(operators.contains(val))//checks operator
            {
                tokens.add("<op, "+ val +" >");
            }
        }
    }
    public static void main(String[] args)throws Exception{        
        FileReader fr=new FileReader("./testcase/simple_test.py"); //get file
        String line=new String(); //python is interpreted, so read line-by-line
        for(int i;(i=fr.read())!=-1;){ //until end-of-file
            if((char)i=='\n' || (char)i==';'){ //end-of-line
                analyse(line); //pass to analysis phase
                line=new String(); //make fresh line
            }
            else{
                line+=((char)i); //add charachters
            }
        }
        fr.close();
        for (int i = 0; i < tokens.size(); i++) // loop for printing tokens
            System.out.print(tokens.get(i) + "\n");
    }
}
