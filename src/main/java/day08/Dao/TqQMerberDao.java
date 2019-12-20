package day08.Dao;


import day08.Model.TMemberShip;
import day08.Model.TQqGroups;
import day08.Model.TQqMerber;
import day08.Utils.dbUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TqQMerberDao {

    public static  void  Insert() throws Exception {
        dbUtil dbUtil=new dbUtil();
        Connection connection=dbUtil.con();
        Statement statement=connection.createStatement();
        String sql="INSERT INTO t_qq_merber VALUES(null,'1249279630','xxxx','yyyy','唐博洋','男');";
        statement.execute(sql);
        statement.close();
        dbUtil.closeCon(connection);
    }
    public static void Delete() throws Exception {
        dbUtil dbUtil=new dbUtil();
        Connection connection=dbUtil.con();
        Statement statement=connection.createStatement();
        String sql="DELETE  FROM t_qq_merber WHERE nick_name='小明'";
        statement.execute(sql);
        statement.close();
        dbUtil.closeCon(connection);
    }
    public static void Update() throws Exception {
        dbUtil dbUtil=new dbUtil();
        Connection connection=dbUtil.con();
        Statement statement=connection.createStatement();
        String sql="UPDATE t_qq_merber SET password='xx' WHERE nick_name='小明';";
        statement.execute(sql);
        statement.close();
        dbUtil.closeCon(connection);
    }
    public static <list> void Find() throws Exception {

        dbUtil dbUtil=new dbUtil();
        Connection connection=dbUtil.con();
//        Statement statement=connection.createStatement();

        String sql="SELECT * " +
                "FROM t_qq_merber AS tqm ,t_qq_groups AS tqg ,t_member_ship AS tms " +
                "WHERE tqm.id=tms.qq_member_id and tms.group_id=tqg.id=?;";
        PreparedStatement preparedStatement= connection.prepareStatement(sql);
//        ResultSet resultSet=statement.executeQuery(sql);

        Scanner scanner=new Scanner(System.in);
        int i=scanner.nextInt();
        preparedStatement.setInt(1,i);
        ResultSet resultSet=preparedStatement.executeQuery();
        List<TQqMerber> tQqMerbers=new ArrayList<TQqMerber>();

        while(resultSet.next()){

            TQqMerber tQqMerber=new TQqMerber();
            TQqGroups tQqGroups=new TQqGroups();
            TMemberShip tMemberShip=new TMemberShip();


            int id=resultSet.getInt("id");
            String qq_number=resultSet.getString("qq_number");
            String password=resultSet.getString("password");
            String img_url=resultSet.getString("img_url");
            String nick_name=resultSet.getString("nick_name");
            String sex=resultSet.getString("sex");

            tQqMerber.setId(id);
            tQqMerber.setImgUrl(img_url);
            tQqMerber.setNickName(nick_name);
            tQqMerber.setPassword(password);
            tQqMerber.setSex(sex);
            tQqMerber.setQqNumber(qq_number);

            String group_name=resultSet.getString("group_name");
            String create_time=resultSet.getString("create_time");
            tQqGroups.setCreateTime(create_time);
            tQqGroups.setGroupName(group_name);
            System.out.println(tQqMerber.toString()+"  "+tQqGroups.toString());
        }

        preparedStatement.close();
        dbUtil.closeCon(connection);

    }

}
