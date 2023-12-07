/*
 * Halstead Length is the sum of the total number of operators and operand -including repetitions
 * */
package MyPackage;

import com.puppycrawl.tools.checkstyle.api.*;


public class HalsteadLengthCheck extends AbstractCheck{
	
	private int ors = 0;
	private int ands = 0;
	
	@Override
	public void beginTree(DetailAST rootAST)
	{
		ors = 0;
		ands = 0;
	}

	@Override
	public int[] getDefaultTokens() {
	    return new int[]{
	        TokenTypes.PLUS,
	        TokenTypes.MINUS,
	        TokenTypes.DIV,
	        TokenTypes.STAR,
	        TokenTypes.ASSIGN,
	        TokenTypes.MOD,
	        TokenTypes.NUM_INT,
	        TokenTypes.NUM_DOUBLE,
	        TokenTypes.IDENT // Add more 
	    };
	}


	@Override
	public int[] getAcceptableTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}

	@Override
	public int[] getRequiredTokens() {
		// TODO Auto-generated method stub
		return getDefaultTokens();
	}
	
	@Override
	public void visitToken(DetailAST aAST) {
	    int tokenType = aAST.getType();
	    switch (tokenType) {
	        case TokenTypes.PLUS:
	        case TokenTypes.MINUS:
	        case TokenTypes.DIV:
	        case TokenTypes.STAR:
	        case TokenTypes.ASSIGN:
	        case TokenTypes.MOD:
	            // This is an operator
	            ors++;
	            break;

	        case TokenTypes.NUM_INT:
	        case TokenTypes.NUM_DOUBLE:
	        case TokenTypes.IDENT:
	            // This is an operand
	            ands++;
	            break;
	    }
	}

	@Override
    public void finishTree(DetailAST rootAST)
    {
		int halsLength = ors+ands;
		log(rootAST.getLineNo(), "Total Halstead Length is: "+halsLength, "ands:"+ands , "ors"+ors);
    }

}

