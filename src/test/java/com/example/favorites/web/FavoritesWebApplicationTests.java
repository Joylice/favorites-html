package com.example.favorites.web;

import com.example.favorites.web.domain.view.IndexCollectorView;
import com.example.favorites.web.service.RedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FavoritesWebApplicationTests {

	@Autowired
	RedisService redisService;

	@Test
	public void contextLoads() {
	}



	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	@Autowired
	private RedisTemplate redisTemplate;

	@Test
	public void test() throws Exception {
		stringRedisTemplate.opsForValue().set("aaa", "111");
		Assert.assertEquals("112", stringRedisTemplate.opsForValue().get("aaa"));
	}
	@Test
	public void redis(){
		System.out.println(redisService.getObject("collector"));
	}

}
