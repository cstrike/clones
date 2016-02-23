package xiao.android.function;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import xiao.android.function.constants.LeakyFunctions;
import xiao.clones.CloneGroup;
import xiao.clones.CloneInstance;
import xiao.clones.util.ResultParser;

public class Filter {
	public List<CloneGroup> getRelatedCloneGroup(List<CloneGroup> cgs)
			throws IOException {
		List<CloneGroup> result = new ArrayList<CloneGroup>();
		for (CloneGroup cg : cgs) {
			for (CloneInstance ci : cg.getCloneInstances()) {
				System.out.println(ci.getCodeFragment().getFileLocation());
				if (LeakyFunctions.checkFunction(ci.getCodeFragment()
						.getSourceCode())) {
					result.add(cg);
					break;
				}
			}
		}
		return result;
	}

	public static void main(String[] args) throws Exception {
		ResultParser rp = new ResultParser("conqat-output/clones-gapped.xml");
		Filter filter = new Filter();
		List<CloneGroup> cgs = filter.getRelatedCloneGroup(rp.getCloneGroups());
		BufferedWriter bw = new BufferedWriter(new FileWriter(new File(
				"output/clone-groups")));
		int gNum = 0;
		for(CloneGroup cg : cgs){
			bw.write("##########################################");
			bw.newLine();
			bw.write("clone group: " + gNum);
			bw.newLine();
			bw.write("##########################################");
			bw.newLine();
			int iNum = 0;
			for(CloneInstance ci : cg.getCloneInstances()){
				bw.write("===============================================");
				bw.newLine();
				bw.write("clone instance " + iNum);
				bw.newLine();
				bw.write("location: " + ci.getCodeFragment().getFileLocation());
				bw.newLine();
				bw.write(ci.getCodeFragment().getStartLine() + " -- " + ci.getCodeFragment().getEndLine());
				bw.newLine();
				bw.write("===============================================");
				bw.newLine();
				bw.write(ci.getCodeFragment().getSourceCode());
				bw.newLine();
				iNum++;
			}
			gNum++;
		}
		bw.flush();
		bw.close();
		System.out.println(rp.getCloneGroups().size());
		System.out.println(cgs.size());
	}
}
