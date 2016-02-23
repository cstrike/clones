package xiao.clones;

public class CloneInstance {
	private CodeFragment cf;
	private CloneGroup cg;
	
	public CloneInstance(CodeFragment cf, CloneGroup cg){
		this.cf = cf;
		this.cg = cg;
	}
	
	public CloneInstance(CodeFragment cf){
		this.cf = cf;
	}

	public CodeFragment getCodeFragment() {
		return cf;
	}

	public void setCodeFragment(CodeFragment cf) {
		this.cf = cf;
	}

	public CloneGroup getCloneGroup() {
		return cg;
	}

	public void setCloneGroup(CloneGroup cg) {
		this.cg = cg;
	}

}
