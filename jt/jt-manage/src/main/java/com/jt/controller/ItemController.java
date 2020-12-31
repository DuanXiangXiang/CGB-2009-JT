package com.jt.controller;

import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.EasyUITable;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.service.ItemService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/item/query")
    public EasyUITable findItemByPage(int page, int rows) {
        EasyUITable itemByPages = itemService.findItemByPages(page, rows);
        System.out.println(itemByPages.getTotal());
        return itemByPages;
    }

    /**
     * url地址：http://localhost:8091/item/save
     * 参数：   form表单数据
     */

    @RequestMapping("/item/save")
    public SysResult itemSave(Item item, ItemDesc itemDesc) {

        itemService.saveItem(item,itemDesc);
        return SysResult.success();
    }

    /**
     * http://localhost:8091/item/update
     */
    @RequestMapping("/item/update")
    public SysResult itemUpdate(Item item,ItemDesc itemDesc) {
        itemService.updateItem(item,itemDesc);
        return SysResult.success();
    }

    /**
     * http://localhost:8091/item/delete
     */
    @RequestMapping("/item/delete")
    public SysResult itemDelete(Long... ids) {
        itemService.deleteItem(ids);
        return SysResult.success();
    }

    /**
     * http://localhost:8091/item/instock	2
     * http://localhost:8091/item/reshelf	1
     */

    @RequestMapping("/item/{status}")
    public SysResult itemUpdateStatus(@PathVariable Integer status, Long... ids) {
        itemService.updateItemStatus(status, ids);
        return SysResult.success();
    }

    /**
     * http://localhost:8091/item/query/item/desc/1474391963
     */
    @RequestMapping("/item/query/item/desc/{itemId}")
    public SysResult findItemDescById(@PathVariable Long itemId){
        ItemDesc itemDesc = itemService.findItemDescById(itemId);
        return SysResult.success(itemDesc);
    }
}
