package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import java.util.HashSet;
import java.util.Set;

/*
 * Halstead Vocabulary is the sum of the number of unique operators and unique
operands
*/
public class HalsteadVocabularyCheck extends AbstractCheck {
    private Set<String> uniqueOperators = new HashSet<>();
    private Set<String> uniqueOperands = new HashSet<>();

    @Override
    public void beginTree(DetailAST rootAST) {
        uniqueOperators.clear();
        uniqueOperands.clear();
    }

    @Override
    public int[] getDefaultTokens() {
        return new int[] {
            TokenTypes.PLUS,
            TokenTypes.MINUS,
            TokenTypes.DIV,
            TokenTypes.STAR,
            TokenTypes.ASSIGN,
            TokenTypes.MOD,
            TokenTypes.NUM_INT,
            TokenTypes.NUM_DOUBLE,
            TokenTypes.IDENT 
        };
    }

    @Override
    public int[] getAcceptableTokens() {
        return getDefaultTokens();
    }

    @Override
    public int[] getRequiredTokens() {
        return getDefaultTokens();
    }

    @Override
    public void visitToken(DetailAST aAST) {
        String tt = aAST.getText();
        int tokenType = aAST.getType();
        switch (tokenType) {
            case TokenTypes.PLUS:
            case TokenTypes.MINUS:
            case TokenTypes.DIV:
            case TokenTypes.STAR:
            case TokenTypes.ASSIGN:
            case TokenTypes.MOD:
                // This is an operator
                uniqueOperators.add(tt);
                break;
            case TokenTypes.NUM_INT:
            case TokenTypes.NUM_DOUBLE:
            case TokenTypes.IDENT:
                // This is an operand
                uniqueOperands.add(tt);
                break;
        }
    }

    @Override
    public void finishTree(DetailAST rootAST) {
        int vocab = uniqueOperators.size() + uniqueOperands.size();
        log(rootAST.getLineNo(), "Halstead Vocabulary is: " + vocab);
    }
}
