package com.itheima.service;


import com.itheima.pojo.Brand;
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
}
