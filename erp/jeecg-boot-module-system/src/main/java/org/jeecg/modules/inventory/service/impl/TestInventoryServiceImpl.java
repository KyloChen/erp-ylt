package org.jeecg.modules.demo.inventory.service.impl;

import org.jeecg.modules.demo.inventory.entity.TestInventory;
import org.jeecg.modules.demo.inventory.mapper.TestInventoryMapper;
import org.jeecg.modules.demo.inventory.service.ITestInventoryService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 测试库存表
 * @Author: jeecg-boot
 * @Date:   2021-01-27
 * @Version: V1.0
 */
@Service
public class TestInventoryServiceImpl extends ServiceImpl<TestInventoryMapper, TestInventory> implements ITestInventoryService {

}
