package Tool;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class Tool {

    public static JSON resultSetToJSON(ResultSet resultSet) {
//        数据集JSON格式
        JSONArray jsonArray = new JSONArray();
//        数据库中每行的数据对象
        JSONObject rowObj = null;
        try {
//          ResultSetMetaData 有关 ResultSet 中列的名称和类型的信息。
            ResultSetMetaData rsmd = resultSet.getMetaData();
//        遍历数据集
            while (resultSet.next()) {
//          每读取一行，新建对象
                rowObj = new JSONObject();
//              获取表列数
                int columnCount = rsmd.getColumnCount();
//            列从1开始，要等于
                for (int i = 1; i <= columnCount; i++) {
//                获取列名
                    String columnName = rsmd.getColumnName(i);
//                取数据
                    String value = resultSet.getString(columnName);
//                添加到rowObj中
                    rowObj.put(columnName, value);
                }
//                添加到数据集Json
                jsonArray.add(rowObj);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jsonArray;
    }
}
