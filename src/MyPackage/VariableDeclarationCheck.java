package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class VariableDeclarationCheck extends AbstractCheck{

	int vc = 0;
	
	@Override
	public void beginTree(DetailAST rootAST)
	{
		vc = 0;
	}
	
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] { 
	            TokenTypes.VARIABLE_DEF 
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
        switch (aAST.getType()) {
            case TokenTypes.VARIABLE_DEF:
                vc++;
                break;
            default:
                break;
        }
    }
	
	@Override
	public void finishTree(DetailAST rootAST) {
        log(rootAST.getLineNo(), "Total number of variable declarations: " + vc);
        vc= 0;
    }
	
}
