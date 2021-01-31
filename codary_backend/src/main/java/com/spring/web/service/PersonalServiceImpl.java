package com.spring.web.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.web.dao.PersonalDao;
import com.spring.web.dto.BlogContentsDto;
import com.spring.web.dto.BlogContentsLikeDto;
import com.spring.web.dto.BlogDto;
import com.spring.web.dto.BlogerLikeDto;
import com.spring.web.dto.JandiDto;
import com.spring.web.dto.MemoContentsDto;
import com.spring.web.dto.MemoDto;

@Service
public class PersonalServiceImpl implements PersonalService{

	@Autowired
	private PersonalDao personalDao;
	
	@Override
	public List<BlogContentsDto> personalContents(String blogid) {
		return personalDao.showBlogContents(blogid);
	}

	@Override
	public List<MemoContentsDto> showMemo(String memoid) {
		return personalDao.showMemo(memoid);
	}

	@Override
	public List<BlogDto> showLikeBloger(String uid) {
		return personalDao.likeBloger(uid);
	}

	@Override
	public List<BlogContentsDto> showLikeBlogContents(String uid) {
		return personalDao.likeBlogContents(uid);
	}

	@Override
	public List<Map<String, Integer>> jandi(String blogid) throws ParseException {
		
		  //캘린더 날짜 세팅
		  Calendar cal = Calendar.getInstance();
	      cal.setTime(new Date());
	      DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	      
	      //오늘 날짜, 1년 전 날짜 Date 타입으로 세팅 
	      Date todaydt = df.parse(df.format(cal.getTime()));
	      cal.add(Calendar.DATE, -364);
	      Date indexdt = df.parse(df.format(cal.getTime()));

	      //날짜 1일씩 추가해주기 위해서 Calendar 타입으로 변경
	      Calendar today = Calendar.getInstance();
	      Calendar index = Calendar.getInstance();
	      today.setTime(todaydt);
	      index.setTime(indexdt);
	      
	      String s=null; //날짜 담을 변수
	      List<JandiDto> list=personalDao.jandi(blogid); //JandiDto 담긴 리스트
	      
	      //TreeMap을 이용하여 key(날짜)를 기준으로 정렬 , Map의 key는 날짜, value는 잔디에 심을 카운트
	      Comparator<String> comparator =(s1, s2) ->s1.compareTo(s2);
	      Map<String, Integer> m=new TreeMap<>(comparator);
	      
	      List<Map<String, Integer>> result= new ArrayList<>(); //결과 담을 result
	      
	      int idx=0;
	      //인덱스날짜가 오늘날짜와 같아질때까지 반복문
	      while (index.compareTo(today) != 1) {
	    	  s=df.format(index.getTime());	// ex) 2021-01-31
	    	  for(int i=idx; i<list.size(); i++) {
	    		  if(s.equals(list.get(i).getBlogDatetime())) {	//jandiDto에 있는 날짜와 같으면
	    			  m.put(s, list.get(i).getJandiCnt());
	    			  idx=i+1;
	    			  break;
	    		  }else {	//jandiDto에 안담긴 날짜라면
	    			  m.put(s, 0);
	    		  }
	    	  }
	    	 
	         index.add(Calendar.DATE, 1);  //날짜 1일 추가
	      }
	     
	     result.add(m); //연산이 끝나면 정렬된 m이 result에 담긴다.
	      
		return result;
	}

	
}
