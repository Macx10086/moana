package com.lab.io.rush.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import com.lab.io.rush.dao.RedisDao;
@Repository("redisDao")
public class RedisDaoImpl implements RedisDao{
	@Resource(name = "redisTemplate")
    private RedisTemplate<String, String> template; // inject the template as ListOperations
    //至于这个为什么可以注入。需要参考AbstractBeanFactory doGetBean
    //super.setValue(((RedisOperations) value).opsForValue());就这一行代码  依靠一个editor
    @Resource(name = "redisTemplate")
    private ValueOperations<String, Object> vOps;
    
    
    public boolean existsKey( final String key){
    	boolean status=template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				return connection.exists(k);
			}
		});
		return status;
    	
    }
    
    public boolean addKey(final String key,final String value){
    	boolean status=template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				byte[] v=value.getBytes();
				try{
					connection.decr(k);
				}catch(Exception e){
					return false;
					
				}
				return true;
			}
    		
		});
    	return status;
		
    }

	

	@Override
	public boolean addSetKey(final String key, final String value) {
		// TODO Auto-generated method stub
		boolean status=template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				byte[] v=value.getBytes();
				try{
					connection.sAdd(k, v);
				}catch(Exception e){
					return false;
				}
				return true;
				
			}
			
		});
		return status;
	}
	

	@Override
	public String getValue(final String key) {
		// TODO Auto-generated method stub
		String value =template.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				try{
					return new String(connection.get(k));
					
				}catch(Exception e){
					return null;
				}
				
			}
		});
		return value;
	}

	@Override
	public boolean existsKeyInSet(final String key, final String value) {
		boolean status=template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				byte[] v=value.getBytes();
				try{
					return connection.sIsMember(k, v);
				}catch(Exception e){
					return false;
				}
				
			}
			
		});
		return status;
	}

	@Override
	public Set<byte[]> setMembers(final String key) {
		// TODO Auto-generated method stub
		Set<byte[]> value =template.execute(new RedisCallback<Set<byte[]>>() {

			@Override
			public Set<byte[]> doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				try{
					return connection.sMembers(k);
				}catch(Exception e){
					return null;
				}
				
				
			}
		});
		return value;
	}
	
	public boolean deleteValueInSet(final String key,final String value){
		boolean status=template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] k=key.getBytes();
				byte[] v=value.getBytes();
				
				return connection.sRem(k, v);
			}
		});
		return status;
	}

	@Override
	public boolean moveValueToRushed(final String srckey, final String dstkey, final String value,final int id) {
		// TODO Auto-generated method stub
		boolean status=template.execute(new RedisCallback<Boolean>() {

			@Override
			public Boolean doInRedis(RedisConnection connection)
					throws DataAccessException {
				// TODO Auto-generated method stub
				byte[] sk=srckey.getBytes();
				byte[] dk=dstkey.getBytes();
				byte[] sv=(value).getBytes();
				byte[] dv=(value+id).getBytes();
//				connection.sRem(sk, sv);
				connection.sAdd(dk, dv);
				return true;
			}
		});
		return status;
		
	}

	@Override
	public String watch(final String key) {
		String status=template.execute(new RedisCallback<String>() {

			@Override
			public String doInRedis(RedisConnection connection)
					throws DataAccessException {
				byte[] k = key.getBytes();
//				connection.watch(k);
				int num = Integer.valueOf(new String(connection.get(k)));
				if(num<=0)
						return "no";
				return "yes";
			}
			
		});
		return status;
	}

	@Override
	public String rushTicket(final int id, final String email) {
		// TODO Auto-generated method stub
		/*try{
			String result = template.execute(new RedisCallback<String>(){

				@Override
				public String doInRedis(RedisConnection connection)
						throws DataAccessException {
					// TODO Auto-generated method stub
					byte[] watchKey = ("movie"+id).getBytes();
					connection.watch(watchKey);
					connection.multi();
					Integer num = Integer.valueOf(new String(template.opsForValue().get("movie"+id)));
					if(num>0){
						if(connection.sIsMember("rushed".getBytes(), (email+id).getBytes())){
							return "一人只能抢一张票";
						}
						if(connection.sIsMember(("rushMovie"+id).getBytes(), email.getBytes())){
							return "一人只能抢一张票";
						}
						connection.decr(watchKey);
						connection.sAdd(("rushMovie"+id).getBytes(), email.getBytes());
						connection.exec();
					}
					else return "票已被抢完";
					return "您已成功抢到票";
				}
				
			});
		}catch(Exception e){
			e.printStackTrace();
			return "系统异常";
		}*/
		return "抢票失败";
	}

}
