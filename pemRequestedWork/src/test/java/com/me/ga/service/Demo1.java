/**
 * 
 */
package com.me.ga.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.me.ga.entity.Case;
import com.me.ga.entity.CaseForDis;
import com.me.ga.entity.CaseProcessInfo;
import com.me.ga.entity.Customer;
import com.me.ga.mapper.CriteriaMapper;
import com.me.ga.util.ExcelUtil;
import com.me.ga.util.LinkedProperties;

/**
 * @author GLAD.Author	
 * @version $Id$
 * @since JDK5.0
 */
public class Demo1 {
    
    

    @Test
    public void demo1Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        //        String[] s = new String[]{"1", "erzi", "30"};
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("param1", "3");
        hashMap.put("param2", "erZi");
        hashMap.put("param3", "30");
        sqlSession.insert("insert$customerBykey", hashMap);

        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo2Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        sqlSession.delete("delete$customerByKey", "1");

        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo3Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Customer customer = new Customer();
        customer.setId(3);
        customer.setName("CzJ");
        customer.setAge(25);
        sqlSession.delete("update$customerByKey", customer);

        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo4Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        Customer customer = null;
        customer = (Customer)sqlSession.selectOne("selectone$customerByKey", 2);
        System.out.println(customer);
        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo5Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Customer> customers =
            sqlSession.selectList("selectall$customerByKey");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo6Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Customer> customers =
            sqlSession.selectList("selectlike$customerByKey", "%Z%");
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo7Test() throws ClassNotFoundException, SQLException,
        IOException {
        InputStream resourceAsStream =
            Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory =
            new SqlSessionFactoryBuilder().build(resourceAsStream);

        SqlSession sqlSession = sqlSessionFactory.openSession();
        CriteriaMapper criteriaMapper =
            sqlSession.getMapper(CriteriaMapper.class);
        //        List<Customer> customers =
        criteriaMapper.insert$customerBykey("4", "zaishuo", "60");
        //        for (Customer customer : customers) {
        //            System.out.println(customer);
        //        }
        sqlSession.commit();
        sqlSession.close();
        resourceAsStream.close();
    }

    @Test
    public void demo8Test() throws ClassNotFoundException, SQLException,
        IOException {

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
            new ClassPathXmlApplicationContext("applicationContext.xml");

//                 CriteriaMapper criteria= (CriteriaMapper)classPathXmlApplicationContext.getBean("editImpl");
        CriteriaMapper criteria =
            (CriteriaMapper)classPathXmlApplicationContext
                .getBean("criteriaMapper");

        List<Customer> customers = criteria.selectall$customerByKey();
        for (Customer customer : customers) {
            System.out.println(customer);
        }
        
        Logger logger = LogManager.getLogger("mylog");
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");
    }

    @Test
    public void demo9Test() {
        String location = "E:/�ێ�Č��Ǘ�/�f�k�`�c�Č����ς���(2016.11~2017) .xlsx";
        ExcelUtil reader = null;
        try {
            reader = new ExcelUtil(location, "18_02");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Sheet Name
        System.out.println(reader.getTopName());
//        JSONObject data = reader.parseSheet(reader.getTopName());
//        System.out.println(data.toString());
    }

    @Test
    public void demo10Test() throws IOException {
        LinkedProperties prop = new LinkedProperties();
        //        File f = new File(this.getClass().getResource("/db.properties"));
        System.out.println(this.getClass().getResource("/db_item_map.properties"));
        FileOutputStream fis =
            new FileOutputStream(this.getClass().getResource("/db_item_map.properties")
                .getFile());
        
        //        System.out.println(this.getClass().getResource("/"));
        //        System.out.println(ClassLoader.getSystemResource(""));
        prop.store(fis, "comment");
        prop.list(System.out);
        System.out.println("The a property: " + prop.getProperty("jdbc.driver"));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(new JSONObject(fis));
    }

    @Test
    public void demo11Test() throws IOException {
        Case case1 = new Case();
        case1.setCaseSeqno("asdasdasdasda");
        case1.setQuNum("ACHYM");
        System.out.println(new JSONObject(case1,new String[]{"caseSeqno","問題管理NO","プロダクトID"}));
        ArrayList arrayList = new ArrayList();
        arrayList.add(case1);
        arrayList.add(case1);
        arrayList.add(case1);
        arrayList.add(case1);
        arrayList.add(case1);
        arrayList.add(case1);
        System.out.println(new JSONArray(arrayList));
        Logger logger = LogManager.getLogger("mylog");
        logger.trace("trace level");
        logger.debug("debug level");
        logger.info("info level");
        logger.warn("warn level");
        logger.error("error level");
        logger.fatal("fatal level");
    }
    
    @Test
    public void demo12Test() throws ClassNotFoundException, SQLException,
        IOException {

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
            new ClassPathXmlApplicationContext("applicationContext.xml");

//                 CriteriaMapper criteria= (CriteriaMapper)classPathXmlApplicationContext.getBean("editImpl");
        CriteriaMapper criteria =
            (CriteriaMapper)classPathXmlApplicationContext
                .getBean("criteriaMapper");
       CaseForDis caseForDis = new CaseForDis();
       caseForDis.setCaseSeqno("849");
        List<CaseForDis> caseForDisList = criteria.select$specificCase(caseForDis);
        
    }
}
