package com.lab.io.rush.service.impl;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.lab.io.rush.dao.LoginerDao;
import com.lab.io.rush.dao.MovieDao;
import com.lab.io.rush.dao.RedisDao;
import com.lab.io.rush.dao.RushTicketDao;
import com.lab.io.rush.pojo.Loginer;
import com.lab.io.rush.pojo.Movie;
import com.lab.io.rush.pojo.RushTicket;
import com.lab.io.rush.service.RedisService;

@Service("redisService")
public class RedisServiceImpl  implements RedisService{
	@Resource
	private RedisDao redisDao;
	@Resource 
	private MovieDao movieDao;
	@Resource
	private LoginerDao loginerDao;
	@Resource
	private RushTicketDao rushDao;
	

	@Override
	@Scheduled(cron="0 0/1  *  * * ?")
	public void sendData() {
		// TODO Auto-generated method stub
		List list=movieDao.getMovieIdList();
		Set<byte[]> s;
		for(int i=0;i<list.size();i++){
			Integer mid=(Integer) list.get(i);
			s=redisDao.setMembers("rushMovie"+mid);
			String value=redisDao.getValue("movie"+mid);
			if(value!=null){
				Integer num =Integer.valueOf(value);
				movieDao.updateNum(mid, num);
				if(s!=null){
					rangeSet(s, mid);
				}
			}
			
			
			
			
			
			
		}
		
	}
	
	/**
	
	 * @Title: rangeSet
	
	 * @Description: 遍历Set并将数据持久化到数据库中
	
	 * @param s
	
	 * @return: void
	
	 */
	public void rangeSet(Set<byte[]> s,int id){
		Iterator it=s.iterator();
		while(it.hasNext()){
			byte[] emailByte= (byte[]) it.next();
			String email=new String(emailByte);
			//将已抢购的删除并复制到另外一个集合
			redisDao.moveValueToRushed("rushMovie"+id,"rushed", email,id);
			Loginer l=loginerDao.getLoginerByEmail(email);
			Movie  m=movieDao.getMovie(id);
			RushTicket rushTicket=new RushTicket();
			rushTicket.setNum(1);
			rushTicket.setLoginer(l);
			rushTicket.setMovie(m);
			rushDao.save(rushTicket);
			sendEmail(email, m.getName());
			
		}
	}
	
	
	/**
	
	 * @Title: sendEmail
	
	 * @Description: 向抢到电影票的人发送邮件
	
	
	 * @return: void
	
	 */
	public void sendEmail(String toEmail,String movie){
		String sendEmail="send_context@sina.com";
		Properties properties = new Properties();
		properties.put("mail.smtp.host", "smtp.sina.com");
		properties.put("mail.transport.protocol", "SMTP");
		properties.put("mail.smtp.auth", "true");
		Session session = Session.getInstance(properties,authenticator);
		MimeMessage mess = new MimeMessage(session);
		try{
					
					mess.setFrom(new InternetAddress(sendEmail));
					mess.setRecipient(RecipientType.TO, new InternetAddress(toEmail));	
					String subject="您好，您已成功抢到《"+movie+"》电影票一张!";
					mess.setSubject("订票通知","UTF-8");
					mess.setSentDate(new Date());
					mess.setText(subject,"UTF-8");
			        Transport.send(mess);
				}catch(AddressException ae){
					ae.printStackTrace();
				}catch(MessagingException me){
					me.printStackTrace();
				}
				
		
		
		
	}
	private	static Authenticator authenticator = new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {		
			return new PasswordAuthentication("send_context@sina.com", "sendcontext");
		}
	};	
	
	
	@Override
	public   String insertKeyIntoSet(int id, String email) {
		// TODO Auto-generated method stub
		String movieId="movie"+id;
//		redisDao.existsKey(movieId);
		String num = "0"; 
		try{
			synchronized (this) {
				 if(redisDao.watch(movieId).equals("no"))
					 return "票已被抢完";
				 
			}
//				if(status){
					
					
					if(redisDao.existsKeyInSet("rushed", email+id)){
						
						return "一人只能抢一张票";
					}
					else {
						boolean sis=redisDao.existsKeyInSet("rushMovie"+id, email);
						if(sis) return "一人只能抢一张票";
					}
					boolean add=redisDao.addKey(movieId, (Integer.valueOf(num)-1)+"");
					boolean sadd=redisDao.addSetKey("rushMovie"+id, email);
					if(!sadd||!add) return "系统异常"; 
					
					return "您已成功抢到票，请到您注册的邮箱查看";
			
		}catch(Exception e){
			e.printStackTrace();
			return "系统出错";
		}
		
			
			
			
			
//		}else{
//			 num=movieDao.getMovie(id).getNum()+"";
//			redisDao.addKey(movieId, num);
//		}
		
		
	}

}
