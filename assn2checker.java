import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class assn2checker
{
	public static void main ( String args [])
	{
		BufferedReader br = null;
		RoutingMapTree r = new RoutingMapTree();

		try {
			String actionString;
			br = new BufferedReader(new FileReader("input_viva.txt"));

			while ((actionString = br.readLine()) != null) {
				r.performAction(actionString);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		//System.out.println(""+r.fullset.throwphone(568).location.parent.parent.identifier);

	}
}
