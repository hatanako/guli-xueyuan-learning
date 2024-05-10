package com.atguigu.mybatisplus;

import com.atguigu.mybatisplus.entity.User;
import com.atguigu.mybatisplus.mapper.UserMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Array;
import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
class MybatisPlusApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelectList(){
        System.out.println(("----- selectAll method test ------"));
        //UserMapper 中的 selectList() 方法的参数为 MP 内置的条件封装器 Wrapper
        //所以不填写就是无任何条件
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    @Test
    public void testInsert(){
        User user = new User();
        user.setName("111");
        user.setAge(18);
        user.setEmail("55317332@qq.com");

        int result = userMapper.insert(user);
        System.out.println(result);
        System.out.println(user);
    }
    @Test
    public void testUpdateById(){

        User user = new User();
        user.setId(1L);
        user.setAge(28);
        int result = userMapper.updateById(user);
        System.out.println(result);
    }
    /**
     * 测试 乐观锁插件
     */
    //乐观锁修改成功
    @Test
    public void testOptimisticLocker(){
        //查询
        User user = userMapper.selectById(1788959350483996674L);
        //修改数据
        user.setName("Helen Yao");
        user.setEmail("helen@qq.com");
        //执行更新
        userMapper.updateById(user);
    }
    @Test
    public void testSelectById(){
        User user = userMapper.selectById(1L);
        System.out.println("user = " + user);
    }
    @Test
    public void testSelectPage(){
        Page<User> page = new Page<>(1,5);
        userMapper.selectPage(page,null);

        page.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }
    @Test
    public void testSelectMapsPage() {
        Page<User> page = new Page<>(1, 5);

        IPage<Map<String, Object>> mapIPage = userMapper.selectMapsPage(page, null);

        //注意：此行必须使用 mapIPage 获取记录列表，否则会有数据类型转换错误
        mapIPage.getRecords().forEach(System.out::println);
        System.out.println(page.getCurrent());
        System.out.println(page.getPages());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.hasNext());
        System.out.println(page.hasPrevious());
    }
    @Test
    public void testDeleteById(){
        int result = userMapper.deleteById(5L);
        System.out.println("result = " + result);
    }
    @Test
    public void testDeleteBatchIds(){
        int result = userMapper.deleteBatchIds(Arrays.asList(2,3,4));
        System.out.println("result = " + result);
    }
    @Test
    public void testDeleteByMap() {
        HashMap<String ,Object> map = new HashMap<>();
        map.put("name","Helen");
        map.put("age","18");

        int result = userMapper.deleteByMap(map);
        System.out.println("result = " + result);
    }
    /**
     * 测试逻辑删除
     */
    @Test
    public void testLogicDelete(){
        int result = userMapper.deleteById(1L);
        System.out.println("result = " + result);
    }
    /**
     * 测试逻辑删除后的查询：
     * 不包括被逻辑删除的记录
     */
    @Test
    public void testLogicDeleteSelect(){
        User user = new User();
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }
    /**
     * 测试 性能分析插件
     */
    @Test
    public void testPerformance(){
        User user = new User();
        user.setName("111");
        user.setEmail("helen@sina.com");
        user.setAge(18);
        userMapper.insert(user);
    }
}
