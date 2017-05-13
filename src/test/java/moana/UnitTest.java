package moana;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jredis.JredisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.lab.io.rush.dao.LoginerDao;
import com.lab.io.rush.dao.MovieDao;
import com.lab.io.rush.dao.RedisDao;
import com.lab.io.rush.dao.RushTicketDao;
import com.lab.io.rush.dao.TestDao;
import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.pojo.TestPo;
import com.lab.io.rush.service.RedisService;
import com.mysql.jdbc.Connection;
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类  
@ContextConfiguration(locations = {"classpath:spring-datanuclenus.xml"}) 
// 
public class UnitTest {
	@Resource
	TestDao testDao;
	@Resource
	RushTicketDao rushTicketDao;
	@Resource
	MovieDao movieDao;
	@Resource
	LoginerDao loginerDao;
	@Resource
	RedisDao redisDao;
	@Resource
	RedisService redisService;
	@Resource(name = "redisTemplate")
    private RedisTemplate<String, String> template; // inject the template as ListOperations
    //至于这个为什么可以注入。需要参考AbstractBeanFactory doGetBean
    //super.setValue(((RedisOperations) value).opsForValue());就这一行代码  依靠一个editor
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> vOps;
	 
	@Test
	public void testConn(){
		String driver="com.mysql.jdbc.Driver";   //获取mysql数据库的驱动类  
        String url="jdbc:mysql://139.199.164.23:3306/movieRush"; //连接数据库（kucun是数据库名）  
        String name="root";//连接mysql的用户名  
        String pwd="root";//连接mysql的密码  
        try{  
            Class.forName(driver);  
            Connection conn=(Connection) DriverManager.getConnection(url,name,pwd);//获取连接对象  
            System.out.println("连接成功");
        }catch(ClassNotFoundException e){  
            e.printStackTrace();  
        }catch(SQLException e){  
            e.printStackTrace();  
        }  
	}
	
	@Test
	public void save(){
		TestPo test=new TestPo();
		test.setID(1);;
		test.setName("Ma");
		 ClassPathXmlApplicationContext ctx = 
				 new ClassPathXmlApplicationContext("spring-datanuclenus.xml"); 
		 		 TestDao testDao=ctx.getBean("testDao", TestDao.class) ; 
				 testDao.save(test);
		
	}
	
	@Test
	public void testAuto(){
		Movie m=(Movie) movieDao.getMovie(2);
//		Loginer l=loginerDao.getLoginerById(1);
//		RushTicket r= new RushTicket();
//		r.setLoginer(l);
//		r.setMovie(m);
//		r.setNum(1);
//		rushTicketDao.save(r);
		
		
		
	}
	@Test
	 public void testSet(){
	        template.execute(new RedisCallback<Boolean>() {
	            @Override
	            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
	                 byte [] key = "rushMovie3".getBytes();
	                 byte[] data1="123@qq.com".getBytes();
	                 byte[] data2="345@qq.com".getBytes();
	                 byte[] data3 = new byte[data1.length + data2.length];  
	                 
	                return true;
	            }
	        });
	    }
@Test 
public void te(){
	List l=movieDao.getMovieIdList();
	System.out.println(l);
}

@Test 
public void ss(){
	movieDao.updateNum(1, 600);
}
@Test
public void testRush(){
	redisService.insertKeyIntoSet(1, "9992@qq.com");
	
}
@Test
public void testSend(){
	Set<byte[]> s=redisDao.setMembers("rushMovie"+2);
	redisService.rangeSet(s, 2);
}

@Test
public void testIsm(){
	boolean ss=redisDao.existsKeyInSet("rushMovie2", "969053992@qq.com");
	System.out.println(ss);
}
}
