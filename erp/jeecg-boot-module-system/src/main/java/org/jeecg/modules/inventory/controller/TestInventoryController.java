package org.jeecg.modules.demo.inventory.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.demo.inventory.entity.TestInventory;
import org.jeecg.modules.demo.inventory.service.ITestInventoryService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: 测试库存表
 * @Author: jeecg-boot
 * @Date:   2021-01-27
 * @Version: V1.0
 */
@Api(tags="测试库存表")
@RestController
@RequestMapping("/inventory/testInventory")
@Slf4j
public class TestInventoryController extends JeecgController<TestInventory, ITestInventoryService> {
	@Autowired
	private ITestInventoryService testInventoryService;
	
	/**
	 * 分页列表查询
	 *
	 * @param testInventory
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "测试库存表-分页列表查询")
	@ApiOperation(value="测试库存表-分页列表查询", notes="测试库存表-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(TestInventory testInventory,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<TestInventory> queryWrapper = QueryGenerator.initQueryWrapper(testInventory, req.getParameterMap());
		Page<TestInventory> page = new Page<TestInventory>(pageNo, pageSize);
		IPage<TestInventory> pageList = testInventoryService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param testInventory
	 * @return
	 */
	@AutoLog(value = "测试库存表-添加")
	@ApiOperation(value="测试库存表-添加", notes="测试库存表-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody TestInventory testInventory) {
		testInventoryService.save(testInventory);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param testInventory
	 * @return
	 */
	@AutoLog(value = "测试库存表-编辑")
	@ApiOperation(value="测试库存表-编辑", notes="测试库存表-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody TestInventory testInventory) {
		testInventoryService.updateById(testInventory);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试库存表-通过id删除")
	@ApiOperation(value="测试库存表-通过id删除", notes="测试库存表-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		testInventoryService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "测试库存表-批量删除")
	@ApiOperation(value="测试库存表-批量删除", notes="测试库存表-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.testInventoryService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "测试库存表-通过id查询")
	@ApiOperation(value="测试库存表-通过id查询", notes="测试库存表-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		TestInventory testInventory = testInventoryService.getById(id);
		if(testInventory==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(testInventory);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param testInventory
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, TestInventory testInventory) {
        return super.exportXls(request, testInventory, TestInventory.class, "测试库存表");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, TestInventory.class);
    }

}
