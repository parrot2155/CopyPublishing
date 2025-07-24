package com.comment.model;

import java.io.InputStream;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.*;

public class SqlMapConfig {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
           
            String resource = "db/sql-mapper-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession(false);
    }
}
