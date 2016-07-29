import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class App {

	public static void main(String[] args) {
		/*try{
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());

                    AddFriendUI lp = new AddFriendUI();
                    lp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    lp.setSize(700, 220);
                    lp.setVisible(true);
                }
            }
        }catch(Exception e){
            
        }*/
		
		try{
			SQLiteTest test = new SQLiteTest();
			ResultSet rs = null;
			
			rs = test.displayUsers();
			
			while(rs.next()){
				System.out.println(rs.getString("fname") + " " + rs.getString("lname"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
