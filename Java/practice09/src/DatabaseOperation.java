import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseOperation extends DatabaseManager {
    private ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> dbConnection(String sql, String... conditions){

        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try{
            //親メソッドでデータベース接続
            Connection con = super.getConnection();

            // ------------------------------- 参照系処理 ------------------------------- //
            if(conditions[0].equals("select")){
                pstmt = con.prepareStatement(sql);

                System.out.println(conditions.length);

                if(conditions.length != 1){
                    for(int i = 1; i < conditions.length; i ++) {
                        pstmt.setString(i, conditions[i]);
                    }
                }
                //参照系SQL実行して値取得
                rs = pstmt.executeQuery();

                //ResultSetMetaDataを使う
                ResultSetMetaData rsmt = rs.getMetaData();

                while(rs.next()){

                    ArrayList<String> rec = new ArrayList<String>();

                    for(int i = 1; i <= rsmt.getColumnCount(); i ++){
                        rec.add(rs.getString(i));
                    }

                    result.add(rec);
                }
            }
            // ------------------------------- 登録系処理 ------------------------------- //
            else if(conditions[0].equals("insert")){
                pstmt = con.prepareStatement(sql,java.sql.Statement.RETURN_GENERATED_KEYS);

                System.out.println(conditions.length);

                if(conditions.length != 1){
                    for(int i = 1; i < conditions.length; i ++) {
                        pstmt.setString(i, conditions[i]);
                    }
                }
            	System.out.println(conditions[0]);
                pstmt.executeUpdate();

                rs = pstmt.getGeneratedKeys();
                ArrayList<String> rec = new ArrayList<String>();
                if(rs.next()){
                    rec.add(String.valueOf(rs.getInt(1)));
                }

                result.add(rec);
            }
            // ------------------------------- 更新系処理 ------------------------------- //
            else if(conditions[0].equals("update")){
                pstmt = con.prepareStatement(sql);
                
                System.out.println(conditions.length);

                if(conditions.length != 1){
                    for(int i = 1; i < conditions.length; i ++) {
                        pstmt.setString(i, conditions[i]);
                    }
                }
                System.out.println(conditions[0]);
                pstmt.executeUpdate();
            }
            
        // ------------------------------- 例外系処理 ------------------------------- //
        }catch(SQLException e){
            //コンソール出力
            System.out.println("SQLException occured!" + e);
        }finally{
            if(rs != null){
                try{
                    rs.close();
                }catch (SQLException e){
                    //コンソール出力
                    System.out.println("ClassNotFoundException occured!");
                }
            }
            if(pstmt != null){
                try{
                    pstmt.close();
                }catch(SQLException e){
                    //コンソール出力
                    System.out.println("SQLException occured!");
                }
            }
        }

        return result;
    }
}
