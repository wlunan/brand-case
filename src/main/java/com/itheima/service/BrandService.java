package com.itheima.service;


import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;

import java.util.List;

public interface BrandService {

    /**
     * 查询所有
     * @return
     */
    List<Brand> selectAll();

    /**
     * 新增品牌
     * @param brand
     */
    void add(Brand brand);

    /**
     *  修改数据
     * @param brand
     */
    void update(Brand brand);

    /**
     * 根据id查询数据
     * @param id
     * @return
     */
    Brand selectById(int id);
    /**
     * 删除数据
     * @param id
     */
    void deleteById(int id);
    /**
     * 批量删除
     * @param its
     */
    void deleteByIds(int[] its);

    /**
     * 分页查询
     * @param currentPage   当前页码
     * @param pageSize      每页显示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     * 分页条件查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
