package chaos.core.commons;

import chaos.core.constant.Constant_;
import com.google.common.io.Files;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.ibatis.jdbc.ScriptRunner;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * ©chaos-parent
 * qq:1413221142
 * 作者：王健(wangjian)
 * 时间：2016-09-12
 */
public class DbUtils_ {

    private static final Logger log = Logger.getLogger(DbUtils_.class);

    public DbUtils_(ApplicationContext context) {

        dataSource = context.getBean(DataSource.class);
        this.context = context;
        check();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    DataSource dataSource;
    private static DbUtils_ instance;
    private ApplicationContext context;

    public static void init(ApplicationContext context) {
        Assert.notNull(context, "ApplicationContext 不能为空！");
        if (instance != null) return;
        instance = new DbUtils_(context);
    }


    private void check() {
        DataSource dataSource = getDataSource();
        if (dataSource == null) {
            log.error("dataSource不可用！");
            return;
        }

        if (!dbIsConnection(dataSource)) {
            log.error("数据库连接失败！");
            return;
        }


        log.info("数据库连接正常");
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            String path = DbUtils_.class.getClassLoader().getResource("db").toURI().getPath();
            if (path == null) {
                /*web上下文获取资源*/
                path = context.getResource("WEB-INF/classes/db").getFile().getPath();
            }
            File temp = new File(path);
            Collection<File> files = FileUtils.listFiles(temp, FileFilterUtils.suffixFileFilter("sql"), null);
            ScriptRunner runner = new ScriptRunner(conn);
            for (File file : files) {
                if (file.isDirectory()) continue;
                String name = Files.getNameWithoutExtension(file.getPath());
                if (!tableExist(conn, name)) {
                    runner.runScript(new InputStreamReader(new FileInputStream(file), Constant_._coding.UTF8));
                    log.info(file.getName() + " 表创建成功！");
                }
            }
        } catch (Exception e) {
            log.warn(e);
        } finally {
            try {
                conn.close();
            } catch (Exception ignored) {
            }
        }
    }


    private boolean dbIsConnection(DataSource dataSource) {

//        String sql = "select version()";
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
            log.info(conn);
            DatabaseMetaData dbmd = conn.getMetaData();

//            创建了这个实例，就可以使用它的方法来获取数据库得信息。主要使用如下的方法：
//
//            (2) 获得当前数据库以及驱动的信息
            log.info(dbmd.getDatabaseProductName() + "-" + dbmd.getDatabaseProductVersion());

//            System.out.println(dbmd.getDatabaseProductName());
//            用以获得当前数据库是什么数据库。比如oracle，access等。返回的是字符串。
//            System.out.println(dbmd.getDatabaseProductVersion());
//            ：获得数据库的版本。返回的字符串。
//            System.out.println(dbmd.getDriverVersion());
//            ：获得驱动程序的版本。返回字符串。
//            ：获得当前数据库的类型信息
//            System.out.println(dbmd.getTypeInfo());
//            JdbcTemplate jdbcTemplate = appContext.getBean(JdbcTemplate.class);

//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//            }
//            rs.close();
//            ps.close();
            return true;
        } catch (SQLException e) {
            log.warn(e);
            return false;
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    log.warn(e);
                }
            }
        }
    }


    /**
     * 查询数据库是否有某表
     *
     * @param tableName
     * @return
     * @throws Exception
     */
    private boolean tableExist(Connection conn, String tableName) {
        ResultSet tabs = null;
//        Connection conn = null;
        try {
//            conn = jt.getDataSource().getConnection();
            DatabaseMetaData dbMetaData = conn.getMetaData();
            String[] types = {"TABLE"};
            tabs = dbMetaData.getTables(conn.getCatalog(), null, null, types);
            while (tabs.next()) {
                if (tabs.getString("TABLE_NAME").equals(tableName)) return true;
            }
        } catch (Exception e) {
            log.warn(e);
        } finally {
            try {
                tabs.close();
//                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * 根据表名称创建一张表
     *
     * @param tableName
     */
    public static int createTable(JdbcTemplate jt, String tableName, Object obj) {
        StringBuffer sb = new StringBuffer("");
//        sb.append("CREATE TABLE `" + tableName + "` (");
//        sb.append(" `id` int(11) NOT NULL AUTO_INCREMENT,");
//        Map<String,String> map = ObjectUtil.getProperty(obj);
//        Set<String> set = map.keySet();
//        for(String key : set){
//            sb.append("`" + key + "` varchar(255) DEFAULT '',");
//        }
//        sb.append(" `tableName` varchar(255) DEFAULT '',");
//        sb.append(" PRIMARY KEY (`id`)");
//        sb.append(") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
        try {
            jt.update(sb.toString());
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


}
