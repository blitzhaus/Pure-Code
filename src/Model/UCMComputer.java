package Model;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Hashtable;

import Common.ReadFile2dStrArray;

class Node {
    
    Node left, right;           // The usual pointers.
    boolean isLeaf;             // Is this a leaf?
    double value;                  // If so, we'll store the number here.
    char op;                    // If not, we need to know which operator.
    boolean getValue;
}

class arrayObj {
	double value;
	char op;
}

class RecordEntry
{
	String defaultToken;
	String prefToken;
	String operator = "*";
	String operation = "";
	
	public String getDefaultToken() {
		return defaultToken;
	}
	public void setDefaultToken(String defaultToken) {
		this.defaultToken = defaultToken;
	}
	public RecordEntry(String defaultToken, String prefToken, String operator,
			String operation) {
		super();
		this.defaultToken = defaultToken;
		this.prefToken = prefToken;
		this.operator = operator;
		this.operation = operation;
	}
	public String getPrefToken() {
		return prefToken;
	}
	public void setPrefToken(String prefToken) {
		this.prefToken = prefToken;
	}
	public String getOperator() {
		return operator;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		RecordEntry other = (RecordEntry) obj;

		if (obj instanceof RecordEntry) {
			RecordEntry recEntry = (RecordEntry) obj;
			return recEntry.getDefaultToken().equals(this.getDefaultToken())
					&& recEntry.getPrefToken().equals(this.getPrefToken())
					&& recEntry.getOperator().equals(this.getOperator());
		} else {
			return false;
		}
	}
}

class Arithmetic {

    Node root;
    ArrayList<arrayObj> inorderArray = new ArrayList<arrayObj>();
    ArrayList<arrayObj> preorderArray = new ArrayList<arrayObj>();
    ArrayList<arrayObj> postorderArray = new ArrayList<arrayObj>();
    public void parseExpression( String exp )
    { 
        root = parse( exp );
    }
    
    
    public Node parse( String exp )
    {
        Node node = new Node();
        
        int m = findMatchingRightParen( exp, 1 );
        String leftExpr = exp.substring( 1, m + 1 );

      
        if( m == exp.length() - 1 ) {
            // It's at the other end => this is a leaf.
            String operandStr = exp.substring( 1, exp.length() - 1 );
            node.isLeaf = true;
            node.value = getValue( operandStr );
            return node;
        }
        
        int n = findMatchingLeftParen( exp, exp.length() - 2 );
        String rightExpr = exp.substring( n, exp.length() - 1 );

        node.left = parse (leftExpr);
        node.right = parse (rightExpr);
        node.op = exp.charAt( m + 1 );

        return node;
    }
    

    int findMatchingRightParen (String s, int leftPos)
    {
        Stack<Character> stack = new Stack<Character>();
        stack.push (s.charAt(leftPos));
        for (int i=leftPos+1; i < s.length(); i++) {
            char ch = s.charAt (i);
            if (ch == '(') {
                stack.push (ch);
            }
            else if (ch == ')') {
                stack.pop ();
                if ( stack.isEmpty() ) {
                    // This is the one.
                    return i;
                }
            }
        }
        System.out.println ("ERROR: findRight: s=" + s + " left=" + leftPos);
        return -1;
    }

    int findMatchingLeftParen (String s, int rightPos)
    {
    	Stack<Character> stack1 = new Stack<Character>();
        stack1.push( s.charAt( rightPos) );
        for( int i = rightPos - 1; i >= 0; i-- ) {
            char ch = s.charAt(i);
            if ( ch == ')' ) {
                stack1.push(ch);
            }
            else if ( ch == '(' ) {
                stack1.pop ();
                if ( stack1.isEmpty() ) {
                  // This is the one.
                    return i;
                }
            }
        }
        System.out.println ("ERROR: findRight: s=" + s + " left=" + rightPos );
        return -1;
    }

    double getValue (String s)
    {
        try {
            double k = Double.parseDouble (s);
            return k;
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }
    
    double evaluateArithmeticExpression(String expr)
    {
		double value = 0.0;
		String modifiedExp = changeExp(expr);
		Node node = new Node();
		node = parse(modifiedExp);
		postOrderTraversal(node);
		value = node.value;
		return value;
    }
    
      
       public void postOrderTraversal( Node node ) {
		if( node != null ) {
			if( !node.isLeaf ) {
				node.value = compute( node );
				node.getValue = true;
			}
			postOrderTraversal( node.left );
			if( !node.isLeaf ) {
				node.value = compute( node );
				node.getValue = true;
			}
			postOrderTraversal( node.right );
			if( node.isLeaf ) {
				//System.out.println( node.value );
			}
			else {
				arrayObj a = new arrayObj();
				a.op = node.op;
				a.value = node.value;
				postorderArray.add(a);
			}
		}
	}

	public static double computeValue( Node node )
    {
        return compute( node );
    }

    public static double compute( Node node )
    {
        if( node.isLeaf || node.getValue ) {
            return node.value;
        }

        // Otherwise do left and right, and add.
        double leftValue = compute( node.left );
        double rightValue = compute( node.right );
        
        if( node.op == '+' ) {
            return leftValue + rightValue;
        }
        else if( node.op == '-' ) {
            return leftValue - rightValue;
        }
        else if( node.op == '*' ) {
            return leftValue * rightValue;
        }
        else {
            return leftValue / rightValue;
        }
    }
    
    public static String changeExp( String exp ) {
		int len = exp.length();
		String newString = "";
		
		for( int i = 0; i < len; i++ ) {
			String s = exp.substring(i, i + 1);
			if( exp.charAt(i) >= '0' && exp.charAt(i) <= '9' ) {
				newString = newString.concat("(");
				while( exp.charAt(i) >= '0' && exp.charAt(i) <= '9' ) {
					s = exp.substring( i , i + 1 );
					newString = newString.concat( s );
					i += 1;
				}
				i -= 1;
				newString = newString.concat(")");
			}
			else {
				newString = newString.concat( s );
			}
		}
		return newString;
	}
    
    public void printArrays() {
    	for( int i = 0; i < postorderArray.size(); i++ ) {
			System.out.print( postorderArray.get(i).op + " " + postorderArray.get(i).value + " " );
		}
		System.out.println();
    }
    
}

public class UCMComputer {
	
	public String[][] inputMatrix;
	public int rowCount = 0;
	public int colCount = 0;
	public String [] stringArray;
	public String [] descrArray;
	Hashtable htUCTokens;

	public UCMComputer() {
		ReadFile2dStrArray RF = new ReadFile2dStrArray("unit.txt");
		rowCount = RF.rowCount;
		colCount = RF.colCount;
		inputMatrix = new String[rowCount][colCount];
		stringArray = new String[rowCount];
		htUCTokens = new Hashtable();
		inputMatrix = RF.fileArray;
		stringArray = RF.stringArray;
		descrArray = RF.descrArray;
		
		for (int i = 0; i < rowCount; i++) {
			
			String defToken = inputMatrix[i][0];
			String prefToken = inputMatrix[i][1];
			String operator = inputMatrix[i][2];
			String operation = inputMatrix[i][3];
			RecordEntry recEntry = new RecordEntry(defToken, prefToken, operator, operation);
			htUCTokens.put(defToken+":"+prefToken+":"+operator, recEntry);
		}
	}
	
	public double traceOutOperationValue(String defToken, String prefToken, String operator)
	{
		String compKey = defToken+":"+prefToken+":"+operator;
		
		RecordEntry recEntry = (RecordEntry) htUCTokens.get(compKey);
		String operation = recEntry.getOperation();
		
		Arithmetic arithRef = new Arithmetic();
		double operationValue = arithRef.evaluateArithmeticExpression(operation);
		
		return operationValue;
	}
}
