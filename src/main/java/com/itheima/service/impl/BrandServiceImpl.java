package com.itheima.service.impl;

import com.itheima.mapper.BrandMapper;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

public class BrandServiceImpl implements BrandService {
    //1.创建SqlsessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();


    @Override
    public List<Brand> selectAll() {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        List<Brand> brands = mapper.selectAll();
        //5.释放资源
        sqlSession.close();

        return brands;
    }


    @Override
    public void add(Brand brand) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession(true);
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.add(brand);
        //5.释放资源
        sqlSession.close();

    }


    @Override
    public void update(Brand brand) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession(true);  //提交事务
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.update(brand);
        //5.释放资源
        sqlSession.close();
    }

    @Override
    public Brand selectById(int id) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession(true);  //提交事务
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        Brand brand = mapper.selectById(id);
        //5.释放资源
        sqlSession.close();

        return brand;
    }

    public void deleteById(int id) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession(true);  //提交事务
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteById(id);
        //5.释放资源
        sqlSession.close();
    }

    @Override
    public void deleteByIds(int[] its) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession(true);  //提交事务
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.调用方法
        mapper.deleteByIds(its);
        //5.释放资源
        sqlSession.close();
    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;
        //5.查询当前页数据
        List<Brand> rows = mapper.selectByPage(begin, size);
        //6.查询总记录数
        int totalCount = mapper.selectTotalCount();
        //7.封装成PageBean对象
        PageBean pageBean = new PageBean();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8.释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //2.获取SqlSeesion对象
        SqlSession sqlSession = factory.openSession();
        //3.获取BrandMapper
        BrandMapper mapper = sqlSession.getMapper(BrandMapper.class);
        //4.计算开始索引
        int begin = (currentPage - 1) * pageSize;
        //计算查询条目数
        int size = pageSize;
        //处理brand条件模糊表达书
        String brandName = brand.getBrandName();
        if (brandName != null && brandName.length() > 0) {
            brand.setBrandName("%" + brandName + "%");
        }

        String companyName = brand.getCompanyName();
        if (companyName != null && companyName.length() > 0) {
            brand.setCompanyName("%" + companyName + "%");
        }
        //5.查询当前页数据
        List<Brand> rows = mapper.selectByPageAndCondition(begin, size, brand);
        //6.查询总记录数
        int totalCount = mapper.selectTotalCountByCondition(brand);
        //7.封装成PageBean对象
        PageBean<Brand> pageBean = new PageBean<>();
        pageBean.setRows(rows);
        pageBean.setTotalCount(totalCount);

        //8.释放资源
        sqlSession.close();

        return pageBean;
    }

}
