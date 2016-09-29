package com.mfg;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.date.GoodRepository;
import com.model.Good;

@SpringBootApplication  
@RestController
public class InterviewApplication {
	
	@Autowired
	GoodRepository goodRepository;
	
	//查前十条
	@RequestMapping(value = "/api/good/{pageNumber}", method = RequestMethod.GET)
    public List<Good> getGoodList(@PathVariable  int pageNumber) {
        //构建分页信息
        PageRequest pageRequest = buildPageRequest(pageNumber,10,"productionDate");
        //查询指定分页的内容
        Iterator<Good> goods =  goodRepository.findAll(pageRequest).iterator();
        List<Good> goodList = new ArrayList<Good>();
        while(goods.hasNext()){
        	goodList.add(goods.next());
        }
        return goodList;
    }

	//按name查找
    @RequestMapping(value = "/api/getGoodByName/{name}", method = RequestMethod.GET)
    public Good getGoodByName(@PathVariable String name) {
        return goodRepository.findByName(name);
    }
    
    //增加Good对象
    @RequestMapping(value = "/api/addGood", method = RequestMethod.POST)
    public Good addGood(@RequestParam("Good") Good good) {
        return goodRepository.insert(good);
    }
    
    //根据name删除
    @RequestMapping(value = "/api/deleteGoodByName/{name}", method = RequestMethod.DELETE)
    public boolean deleteCustomerById(@PathVariable String name) {
    	goodRepository.delete(name);
        return true;
    }
    
    //修改Good对象
    @RequestMapping(value = "/api/updateGood", method = RequestMethod.PUT)
    public String updateGood(@RequestParam("Good") Good good) {
    	if(goodRepository.exists(good.getName())){
        		goodRepository.save(good);
        		return "update success!";
    	}
    	return "update fail, the Good name not exists!";
    }
	
	public static void main(String[] args) {
		SpringApplication.run(InterviewApplication.class, args);
	}
	
	 /**
     * 创建分页请求.      
     */
    private PageRequest buildPageRequest(int pageNumber, int pageSize,String sortType) {
        Sort sort = new Sort(Sort.Direction.DESC, sortType);
        //参数1表示当前第几页,参数2表示每页的大小,参数3表示排序
        return new PageRequest(pageNumber-1,pageSize,sort);
    }
}
