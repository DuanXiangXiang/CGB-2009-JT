package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public String findItemCatNameById(Long itemCatId) {
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", itemCatId);
        ItemCat itemCat = itemCatMapper.selectOne(queryWrapper);
        return itemCat.getName();
    }

    @Override
    public List<EasyUITree> findItemCatList(Long parentId) {
        System.out.println(parentId);
        ArrayList<EasyUITree> treeList = new ArrayList();
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("parent_Id", parentId);
        List<ItemCat> catList = itemCatMapper.selectList(queryWrapper);
        for (ItemCat itemCat : catList) {
            long id = itemCat.getId();
            String text = itemCat.getName();
            String state = itemCat.getIsParent() ? "closed" : "open";
            EasyUITree easyUITree = new EasyUITree(id,text,state);
            treeList.add(easyUITree);
        }
        return treeList;
    }
}
