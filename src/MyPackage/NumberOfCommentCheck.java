package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;

public class NumberOfCommentCheck extends AbstractCheck{
	
	int cc =0;

	@Override
	public void beginTree(DetailAST rootAST)
	{
		cc = 0;
	}
	
	@Override
	public int[] getDefaultTokens() {
		// TODO Auto-generated method stub
		return new int[] {
				TokenTypes.SINGLE_LINE_COMMENT,
				TokenTypes.BLOCK_COMMENT_BEGIN 
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
        case TokenTypes.SINGLE_LINE_COMMENT:
        case TokenTypes.BLOCK_COMMENT_BEGIN:
            cc++;
            break;
        default:
            break;
            }
	}
	
	@Override
    public boolean isCommentNodesRequired() {
        return true;
    }
	
	@Override
	public void finishTree(DetailAST rootAST) {
		log(rootAST.getLineNo(), "Total number of comments: " + cc);
        cc =0;
	}

}
