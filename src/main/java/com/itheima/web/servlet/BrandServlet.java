package com.itheima.web.servlet;


import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.PageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService service = new BrandServiceImpl();

    /**
     * 查询所有
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.调用Service查询
        List<Brand> brands = service.selectAll();

        //2.将List集合转为JSON数据
        String jsonString = JSON.toJSONString(brands);

        //3.响应JSON数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonString);
    }


    /**
     * 添加数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //JSon数据转java对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //2.调用service添加
        service.add(brand);

        //3.返回成功标识
        response.getWriter().write("success");
    }

    /**
     * 修改数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌数据
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //JSon数据转java对象
        Brand brand = JSON.parseObject(params, Brand.class);
//        System.out.println(brand);
        //2.调用service修改
        service.update(brand);
        //3.返回成功标识
        response.getWriter().write("success");
    }

    public void selectById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌数据id数组
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //JSon数据转java对象
        int id = JSON.parseObject(params, int.class);

        //2.调用service删除
        Brand brand = service.selectById(id);
        //3.转为JSon
        String jsonString = JSON.toJSONString(brand);
        //3.写回数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonString);
    }
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌数据id数组
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //JSon数据转java对象
        int id = JSON.parseObject(params, int.class);
;
        //2.调用service删除
        service.deleteById(id);

        //3.返回成功标识
        response.getWriter().write("success");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收品牌数据id数组
        BufferedReader br = request.getReader();
        String params = br.readLine();
        //JSon数据转java对象
        int[] ids = JSON.parseObject(params, int[].class);

        //2.调用service添加
        service.deleteByIds(ids);

        //3.返回成功标识
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取当前页码和每页显示条数   通过get方式
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2.调用service查询
        PageBean<Brand> pageBean = service.selectByPage(currentPage, pageSize);

        //3.转为JSon
        String jsonString = JSON.toJSONString(pageBean);
        //3.写回数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取当前页码和每页显示条数   通过get方式
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage = Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询对象
        BufferedReader br = request.getReader();
        String params = br.readLine();

        //惊JSON数据转为对象
        Brand brand = JSON.parseObject(params, Brand.class);
        /*System.out.println(brand);*/
        //2.调用service查询
        PageBean<Brand> pageBean = service.selectByPageAndCondition(currentPage,pageSize,brand);
//        System.out.println(pageBean.getRows()+"   "+pageBean.getTotalCount());
        //3.转为JSon
        String jsonString = JSON.toJSONString(pageBean);
        //4.写回数据
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
