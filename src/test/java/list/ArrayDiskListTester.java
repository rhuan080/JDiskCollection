/*package list;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.jarraydisk.arraydisklist.list.ArrayDiskList;

public class ArrayDiskListTester {

	@Test
	public void test() {
		
		List<String> diskList = null;
		try {
			diskList = new ArrayDiskList<String>();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i = 0; i < 100000; i++) {
			diskList.add("teste"+i);
			System.out.println("inserted: teste"+i);
		}
		
		for(int i=0; i < diskList.size(); i++) {
			System.out.println("out: "+ diskList.get(i));
		}
		
		
		fail("Not yet implemented");
	}

}
*/