package xiao.clones.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import xiao.clones.CloneGroup;
import xiao.clones.CloneInstance;
import xiao.clones.CodeFragment;

/**
 * @author Conan
 *
 */
public class ResultParser {
	/* The path of the .xml file which contains the result of code clones */
	private String path;

	private Map<String, JavaFile> files;

	private List<CloneGroup> cgs;

	public ResultParser(String path) throws Exception {
		this.path = path;
		files = new HashMap<String, JavaFile>();
		this.cgs = new ArrayList<CloneGroup>();
		this.parse();
	}

	@SuppressWarnings("unused")
	private Map<String, JavaFile> getFiles() {
		return this.files;
	}
	
	public List<CloneGroup> getCloneGroups(){
		return cgs;
	}

	public void parse() throws Exception {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);

		Element rootElement = document.getDocumentElement();
		NodeList childNodes = rootElement.getChildNodes();

		for (int i = 0; i < childNodes.getLength(); i++) {
			Node node = childNodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element ele = (Element) node;
				// <sourceFile>
				if (node.getNodeName().equals("sourceFile")) {
					String id = ele.getAttribute("id");
					String location = ele.getAttribute("location");
					String filePath = ele.getAttribute("path");
					files.put(id, new JavaFile(location, filePath));
				}
				// <cloneClass>
				else if (node.getNodeName().equals("cloneClass")) {
					NodeList clones = ele.getChildNodes();
					CloneGroup cg = new CloneGroup();
					for (int j = 0; j < clones.getLength(); j++) {
						// <clone>
						Node node1 = clones.item(j);
						if (node1.getNodeType() == Node.ELEMENT_NODE) {
							Element cloneElement = (Element) node1;
							if (cloneElement.getNodeName().equals("clone")) {
								String id = cloneElement.getAttribute("id");
								String startLine = cloneElement
										.getAttribute("startLine");
								String endLine = cloneElement
										.getAttribute("endLine");
								String fileId = cloneElement
										.getAttribute("sourceFileId");
								String fileLocation = this.files.get(fileId).getLocation();
								String startOffset = cloneElement
										.getAttribute("startOffset");
								String endOffset = cloneElement
										.getAttribute("endOffset");
								
								
								cg.addCloneInstance(new CloneInstance(
										new CodeFragment(id, startLine,
												endLine, fileLocation, startOffset,
												endOffset)));
							}
						}
					} // end for
					cgs.add(cg);
				}
			}

		} // end for
		System.out.println();
	}

	public static void main(String[] args) throws Exception {
		ResultParser rp = new ResultParser("conqat-output/clones-gapped.xml");
		for(CloneInstance ci : rp.getCloneGroups().get(2).getCloneInstances())
			System.out.println(ci.getCodeFragment().getSourceCode());
	}

}

class JavaFile {
	private String location;
	private String path;
	
	public JavaFile(String loc, String path) {
		this.path = path;
		this.location = loc;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public String getPath() {
		return this.path;
	}
}
