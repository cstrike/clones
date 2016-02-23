package xiao.clones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.eclipse.jdt.core.dom.ASTNode;

public class CodeFragment {
	private String id;
	private int startLine;
	private int endLine;
	private int startOffset;
	private int endOffset;
	private String fileLocation;

	public CodeFragment(String id, String startLine, String endLine,
			String fileLocation, String startOffset, String endOffset) {
		this.id = id;
		this.startLine = Integer.valueOf(startLine);
		this.endLine = Integer.valueOf(endLine);
		this.startOffset = Integer.valueOf(startOffset);
		this.endOffset = Integer.valueOf(endOffset);
		;
		this.fileLocation = fileLocation;
	}

	public String getId() {
		return this.id;
	}

	public int getStartLine() {
		return this.startLine;
	}

	public int getEndLine() {
		return this.endLine;
	}

	public String getFileLocation() {
		return this.fileLocation;
	}

	public int getStartOffset() {
		return this.startOffset;
	}

	public int getEndOffset() {
		return this.endOffset;
	}
	

	/**
	 * @return code
	 * @throws IOException
	 * 
	 * Get the source code of the code fragment.
	 */
	public String getSourceCode() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(new File(
				this.getFileLocation())));
		int line = 1;
		String code = "";

		while (line < startLine) {
			br.readLine();
			line++;
		}
		while (line <= endLine) {
			code += br.readLine() + '\n';
			line++;
		}
		br.close();
		return code;
	}
	
	/**
	 * @return node
	 * Search the ASTNode that just covers the code fragment from the AST
	 */
	/*public ASTNode getASTNode(){
		return null;
	}*/
}
