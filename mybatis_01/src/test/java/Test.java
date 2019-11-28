import com.paulniu.domain.QueryVo;
import com.paulniu.domain.User;
import com.paulniu.mybatis_01.IUserDao;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class Test {
    private static InputStream is;

    public static void main(String[] args) throws IOException {
        is = Resources.getResourceAsStream("SQLMapConfig.xml");
        findByVo();
        is.close();
    }

    /**
     * 查询所有用户
     */
    public static void findAll() {
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("user = " + user.toString());
        }
        session.close();

    }

    /**
     * 根据id查询用户
     */
    public static void findById(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        User user = userDao.findById(41);
        System.out.println(user);
        session.close();
    }

    /**
     * 保存新用户
     */
    public static void saveUser(){
        User user = new User();
        user.setUsername("孙悟空");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("北京通州");
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        int count = userDao.saveUser(user);
        System.out.println(count);
        session.commit();
        session.close();
    }

    /**
     * 更新用户信息
     */
    public static void updateUser(){
        User user = new User();
        user.setUsername("孙悟饭");
        user.setSex("男");
        user.setBirthday(new Date());
        user.setAddress("北京朝阳");
        user.setId(46);
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        int count = userDao.updateUser(user);
        System.out.println(count);
        session.commit();
        session.close();
    }

    /**
     * 删除用户
     */
    public static void deleteUser(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        int count = userDao.deleteUser(43);
        System.out.println(count);
        session.commit();
        session.close();
    }

    /**
     * 查询数据总数
     */
    public static void findTotal(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        int count = userDao.findTotal();
        System.out.println(count);
        session.close();
    }

    /**
     * 使用pojo封装类
     */
    public static void findByVo(){
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        SqlSession session = factory.openSession(true);
        IUserDao userDao = session.getMapper(IUserDao.class);
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUsername("%小%");
        vo.setUser(user);
        List<User> users = userDao.findByVo(vo);
        for (User uu:users){
            System.out.println(uu.toString());
        }
        session.close();
    }

}
