package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{
	List<Item> findItemByPages(int starIndex,int pageSize);
	int findTotal();
	int deleteItem( @Param("ids") Long... ids);

	void updateItemStatus(Integer status, Long[] ids);
}
