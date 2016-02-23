package xiao.clones;

import java.util.ArrayList;
import java.util.List;

public class CloneGroup {
	private List<CloneInstance> cis;

	public CloneGroup(List<CloneInstance> cis){
		this.cis = cis;
		for(CloneInstance ci : this.cis){
			ci.setCloneGroup(this);
		}
	}
	
	public CloneGroup(){
		this.cis = new ArrayList<CloneInstance>();
	}

	public void addCloneInstance(CloneInstance ci){
		this.cis.add(ci);
		ci.setCloneGroup(this);
	}
	
	public void rmCloneInstance(CloneInstance ci){
		this.cis.remove(ci);
		ci.setCloneGroup(null);
	}
	
	public List<CloneInstance> getCloneInstances(){
		return this.cis;
	}

	public int size() {
		return this.cis.size();
	}
}
