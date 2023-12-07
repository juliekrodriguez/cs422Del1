package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
/*
 * Halstead Difficulty is half of the unique operators multiplied by the total number of
operands, divided by the number of unique operands
*/
public class HalsteadDifficultyCheck extends AbstractCheck{
	

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
	

	
}
