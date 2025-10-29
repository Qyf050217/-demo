package com.sky.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sky.context.BaseContext;
import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.entity.Employee;
import com.sky.mapper.CategoryMapper;
import com.sky.result.PageResult;
import com.sky.service.CategoryService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void save(CategoryDTO categoryDTO) {
        Category res = new Category();

        // 对象属性拷贝
        BeanUtils.copyProperties(categoryDTO, res);

        // 为剩余属性赋值
        res.setStatus(1);
        res.setCreateTime(LocalDateTime.now());
        res.setUpdateTime(LocalDateTime.now());
        res.setCreateUser(BaseContext.getCurrentId());
        res.setUpdateUser(BaseContext.getCurrentId());

        categoryMapper.insert(res);
    }


    public PageResult pageQuery(CategoryPageQueryDTO DTO) {
        PageHelper.startPage(DTO.getPage(), DTO.getPageSize());

        Page<Category> page = categoryMapper.pageQuery(DTO);

        long total = page.getTotal();
        List<Category> records = page.getResult();

        return new PageResult(total, records);
    }
}
