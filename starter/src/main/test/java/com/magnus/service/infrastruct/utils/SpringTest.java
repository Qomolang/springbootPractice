package com.magnus.service.infrastruct.utils;

import com.magnus.demo.DemoApplication;
import com.magnus.service.entity.User;
import com.magnus.service.mapper.UserMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//加载测试需要的类，一定要加入启动类，其次是本类
@SpringBootTest(classes = DemoApplication.class)
public class SpringTest {

    @Resource
    private UserMapper mapper;

    @Before
    public void init(){

    }

    @Test
    public void test(){

        User user = new User();
        user.setName("小羊");
        user.setAge(3);
        user.setEmail("abc@mp.com");
        assertThat(mapper.insert(user)).isGreaterThan(0);
        // 成功直接拿回写的 ID
        System.out.println(user);
        assertThat(user.getId()).isNotNull();
    }

}
