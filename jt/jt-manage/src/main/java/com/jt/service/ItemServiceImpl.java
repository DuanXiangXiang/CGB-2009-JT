package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jt.mapper.ItemMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;


    @Override
    public EasyUITable findItemByPages(int page, int rows) {
//        int starIndex = rows * (page - 1);
//		List<Item> items  =  itemMapper.findItemByPages(starIndex,rows);
//		long total = (long) itemMapper.findTotal();
//		return new EasyUITable(total,items);
        IPage itemPage = new Page(page, rows);
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("updated");
        itemPage = itemMapper.selectPage(itemPage,queryWrapper);
        long total = itemPage.getTotal();
        List<Item> items = itemPage.getRecords();
        return new EasyUITable(total,items);
    }

    @Override
    @Transactional  //控制数据库事物
    public void saveItem(Item item, ItemDesc itemDesc) {
        item.setStatus(1);
//                .setCreated(new Date())
//                .setUpdated(new Date());
        itemMapper.insert(item);

        itemDesc.setItemId(item.getId());
        itemDescMapper.insert(itemDesc);
    }

    @Override
    @Transactional
    public void updateItem(Item item, ItemDesc itemDesc) {
        itemMapper.updateById(item);
        itemDesc.setItemId(item.getId());
        itemDescMapper.updateById(itemDesc);
//        QueryWrapper<Item> queryWrapper = new QueryWrapper();
//        queryWrapper.eq("id", item.getId());
//        itemMapper.update(item, queryWrapper);
    }

    @Override
    @Transactional
    public void deleteItem(Long... ids) {
//        itemMapper.deleteBatchIds(Arrays.asList(ids));
        itemMapper.deleteItem(ids);
        itemDescMapper.deleteBatchIds(Arrays.asList(ids));
    }

    @Override
    @Transactional
    public void updateItemStatus(Integer status, Long[] ids) {
//        itemMapper.updateItemStatus(status,ids);
        Item item = new Item();
        item.setStatus(status);
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id", ids);
        itemMapper.update(item,queryWrapper);
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        return itemDescMapper.selectById(itemId);
    }
}
