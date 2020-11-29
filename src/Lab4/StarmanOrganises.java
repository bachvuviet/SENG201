package Lab4;

import java.util.ArrayList;

public class StarmanOrganises {
	//This class store data of objects implements RemoteControlable interface
	private ArrayList<RemoteControllable> controlObj = new ArrayList<RemoteControllable>();
	
	public void addControllable(Object obj) {
		if (obj instanceof RemoteControllable) {
			controlObj.add((RemoteControllable) obj);//casting - aka explicit
		}
	}
	
	public void getAllStatusReports() {
		for (RemoteControllable obj:controlObj) {
			System.out.println(obj.getStatusReport());
		}
	}
}
