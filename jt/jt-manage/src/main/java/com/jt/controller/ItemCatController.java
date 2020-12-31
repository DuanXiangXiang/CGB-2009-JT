package com.jt.controller;

import com.jt.service.ItemCatService;
import com.jt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    /**
     * http://localhost:8091/item/cat/queryItemName?itemCatId=560
     */
    @GetMapping("/item/cat/queryItemName")
    public String findItemCatNameById(Long itemCatId) {
        return itemCatService.findItemCatNameById(itemCatId);
    }

    /**
     * http://localhost:8091/item/cat/list
     */
    @RequestMapping("/item/cat/list")
    public List<EasyUITree> findItemCatList(Long id) {
        Long parentId = id == null ? 0 : id;
        return itemCatService.findItemCatList(parentId);
    }
}
