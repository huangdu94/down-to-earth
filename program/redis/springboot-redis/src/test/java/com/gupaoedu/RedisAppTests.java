package com.gupaoedu;

import com.gupaoedu.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisAppTests {

    @Autowired
    RedisUtil util;

	@Test
	public void contextLoads() {
        util.set("boot", "2673--" +System.currentTimeMillis());
        System.out.println(util.get("boot"));
	}

}
