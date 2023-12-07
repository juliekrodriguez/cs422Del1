package MyPackage;

import com.puppycrawl.tools.checkstyle.api.AbstractCheck;
import com.puppycrawl.tools.checkstyle.api.DetailAST;
import com.puppycrawl.tools.checkstyle.api.TokenTypes;
import java.util.HashSet;
import java.util.Set;
import java.lang.Math;

/*
 * Halstead Volume is the program length (N) times the log2 of the program vocabulary (n)
[1,2] : Volume = N log2 n
*/

public class HalsteadVolumeCheck extends AbstractCheck {
    private Set<String> uniqueOperators = new HashSet<>();
    private Set<String> uniqueOperands = new HashSet<>();
    private int programLength = 0;

    @Override
    public void beginTree(DetailAST rootAST) {
        uniqueOperators.clear();
        uniqueOperands.clear();
        programLength = 0;
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
            TokenTypes.IDENT // Add more token types as needed
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
                programLength++;
                break;
            case TokenTypes.NUM_INT:
            case TokenTypes.NUM_DOUBLE:
            case TokenTypes.IDENT:
                // This is an operand
                uniqueOperands.add(tt);
                programLength++;
                break;
        }
    }

    @Override
    public void finishTree(DetailAST rootAST) {
        int vocabulary = uniqueOperators.size() + uniqueOperands.size();
        double halsteadVolume = programLength * Math.log(vocabulary) / Math.log(2); // Calculate Halstead Volume
        log(rootAST.getLineNo(), "Halstead Volume is: " + halsteadVolume);
    }
}
