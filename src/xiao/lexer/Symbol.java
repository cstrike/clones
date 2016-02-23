package xiao.lexer;

import org.eclipse.jdt.core.compiler.ITerminalSymbols;
import java.io.*;
import java.util.*;

public class Symbol {
	public static HashMap<Integer, String> symbols = new HashMap<Integer, String>();
	public static HashSet<String> keyWord = new HashSet<String>();
	public static HashSet<String> separator = new HashSet<String>();
	public static HashSet<String> operator = new HashSet<String>();
	public static HashSet<String> identifier = new HashSet<String>();
	public static HashSet<String> literal = new HashSet<String>();
	public static HashSet<String> other = new HashSet<String>();
	public static HashSet<String> dolar = new HashSet<String>();
	
	static {
		try {
			BufferedReader br1 = new BufferedReader(new FileReader(new File("ini/num.txt")));
			BufferedReader br2 = new BufferedReader(new FileReader(new File("ini/symName.txt")));
			String str1 = br1.readLine();
			String str2 = br2.readLine();
			while(str1 != null) {
				symbols.put(Integer.valueOf(str1), str2);
				str1 = br1.readLine();
				str2 = br2.readLine();
			}
			br1.close();
			br2.close();
			
			readFile("ini/tokens/Identifier.txt", 1);
			readFile("ini/tokens/Keyword.txt", 2);
			readFile("ini/tokens/Literal.txt", 3);
			readFile("ini/tokens/Operator.txt", 4);
			readFile("ini/tokens/Other.txt", 5);
			readFile("ini/tokens/Separator.txt", 6);
			readFile("ini/tokens/Dolar.txt", 7);

		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	public static void readFile(String path, int option) throws IOException {
		List<String> list = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(new File(path)));
		String token = br.readLine();
		while(token != null) {
			list.add(token);
			token = br.readLine();
		}
		
		switch(option) {
			case 1:
				for(String str : list) {
					identifier.add(str);
				}
				break;
			case 2:
				for(String str : list) {
					keyWord.add(str);
				}
				break;
			case 3:
				for(String str : list) {
					literal.add(str);
				}
				break;
			case 4:
				for(String str : list) {
					operator.add(str);
				}
				break;
			case 5:
				for(String str : list) {
					other.add(str);
				}
				break;
			case 6:
				for(String str : list) {
					separator.add(str);
				}
				break;		
			case 7:
				for(String str : list) {
					dolar.add(str);
				}
				break;
			default:
				break;
		}
	}
	
	public static void main(String args[]) throws Exception{
		/*
		Object keys[] = symbols.keySet().toArray();
		for(Object obj : keys) {
			int num = Integer.valueOf(obj.toString());
			System.out.println(num + " " + symbols.get(num));
		}
		System.out.println("------------------------");
		System.out.println(identifier.size());
		System.out.println(keyWord.size());
		System.out.println(literal.size());
		System.out.println(other.size());
		System.out.println(operator.size());
		System.out.println(separator.size());
		*/
	}
	
}
