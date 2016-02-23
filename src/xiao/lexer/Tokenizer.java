package xiao.lexer;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.core.ToolFactory;
import org.eclipse.jdt.core.compiler.IScanner;
import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import org.eclipse.jdt.core.compiler.InvalidInputException;

public class Tokenizer {
	
	/**
	 * do tokenization
	 * @param fragement : String source code
	 * @return
	 * @throws IOException
	 * @throws InvalidInputException
	 */
	public List<Token> tokenize(String cf) throws InvalidInputException{
		IScanner scanner = ToolFactory.createScanner(false, false, false, false);
		scanner.setSource(cf.toCharArray());
		List<Token> tokens = new ArrayList<Token>();
		
		int token = scanner.getNextToken();
		int pos = 0;
		while(token != ITerminalSymbols.TokenNameEOF) {
			String symbolAttr = Symbol.symbols.get(token);
			String rawValue = new String(scanner.getRawTokenSource());
			String category = "";
		
			if(Symbol.identifier.contains(symbolAttr)) 
				category = Token.category_identifier;
			else if(Symbol.keyWord.contains(symbolAttr))
				category = Token.category_keyword;
			else if(Symbol.literal.contains(symbolAttr))
				category = Token.category_literal;
			else if(Symbol.operator.contains(symbolAttr))
				category = Token.category_operator;
			else if(Symbol.other.contains(symbolAttr))
				category = Token.category_other;
			else if(Symbol.separator.contains(symbolAttr))
				category = Token.category_separator;
				
			Token t = new Token(symbolAttr, rawValue, category, "");
			
			// added by yupeng
			
			t.setStartPosition(scanner.getCurrentTokenStartPosition());
			t.setEndPosition(scanner.getCurrentTokenEndPosition());
			
			// add ended
			
			t.pos = pos++;
			tokens.add(t);
			//System.out.println(Symbol.symbols.get(token));
			//System.out.println(new String(is.getRawTokenSource()) + "\n");
			token = scanner.getNextToken();
		}
		return tokens;
	}

}
