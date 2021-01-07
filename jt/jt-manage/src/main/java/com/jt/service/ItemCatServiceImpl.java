package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import com.jt.util.ObjectMapperUtil;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired(required = false)    //暂时不注入，需要时再注入
//    @Lazy
    private Jedis jedis;

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

    @Override
    public List<EasyUITree> findItemCatCache(Long parentId) {
        Long startTime = System.currentTimeMillis();
        List<EasyUITree> treeList = new ArrayList<>();
        String key = "ITEM_CAT_PARENTED::"+parentId;
        if (!jedis.exists(key)){
            treeList = findItemCatList(parentId);
            Long endTime = System.currentTimeMillis();
            System.out.println("查询数据库耗时："+(endTime - startTime));
            String json = ObjectMapperUtil.toJSON(treeList);
            jedis.setex(key,7*24*60*60,json);
        }else {
            String json = jedis.get(key);
            treeList= ObjectMapperUtil.toObj(json,treeList.getClass());
            long endTime = System.currentTimeMillis();
            System.out.println("查询缓存耗时："+(endTime - startTime));
        }
        return treeList;
    }
}
