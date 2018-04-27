package com.gz.service.sys.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gz.beans.dto.SysDeptDto;
import com.gz.beans.po.sys.SysDept;
import com.gz.beans.vo.sys.SysDeptVo;
import com.gz.common.exception.ParamException;
import com.gz.dao.sys.SysDeptDao;
import com.gz.service.sys.SysDeptService;
import com.gz.utils.LevelUtil;

@Service
public class SysDeptServiceImpl implements SysDeptService{

	private static final Logger logger = LoggerFactory.getLogger(SysDeptServiceImpl.class);
	@Autowired
	private SysDeptDao sysDeptDao;
	
	@Override
	public void insertSysDept(SysDeptVo sysDeptVo) {
		// 同一部门下是否存在相同的部门名称
		checkSysDeptName(sysDeptVo.getId(),sysDeptVo.getName(),sysDeptVo.getParentId());
		
		// 封装参数
		SysDept sysDept = new SysDept();
		try {
			BeanUtils.copyProperties(sysDept, sysDeptVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("数据转换失败");
		}
		sysDept.setOperator("insert");// TODO
		sysDept.setOperateTime(new Date());
		sysDept.setOperateIp("127.0.0.1");// TODO
		sysDept.setLevel(getLevel(sysDeptVo.getParentId()));
		
		// 插入数据
		sysDeptDao.insertSelective(sysDept);
	}
	

	@Override
	public void updateSysDept(SysDeptVo sysDeptVo) {
		// 同一部门下是否存在相同的部门名称
		checkSysDeptName(sysDeptVo.getId(),sysDeptVo.getName(),sysDeptVo.getParentId());
		
		// 封装参数 获得更改前和更改后的数据
		SysDept oldSysDept = sysDeptDao.selectById(sysDeptVo.getId());
		SysDept newSysDept = new SysDept();
		try {
			BeanUtils.copyProperties(newSysDept, sysDeptVo);
		} catch (IllegalAccessException | InvocationTargetException e) {
			logger.error("数据转换失败");
		}
		newSysDept.setOperator("update");// TODO
		newSysDept.setOperateTime(new Date());
		newSysDept.setOperateIp("127.0.0.1");// TODO
		if(newSysDept.getParentId() != oldSysDept.getParentId()) {
			newSysDept.setLevel(getLevel(newSysDept.getParentId()));
		}
		
		//更新部门及其部门下的子节点信息
		updateWidthChild(newSysDept, oldSysDept);
	}
	
	
	/**
	 * @Description: 更新部门及其部门下的子节点信息
	 * @param newSysDept
	 * @param oldSysDept    
	 */
	@Transactional
	private void updateWidthChild(SysDept newSysDept,SysDept oldSysDept) {
		String newLevel = newSysDept.getLevel();
		String oldLevel = oldSysDept.getLevel();
		
		// 更新数据，并更新子节点数据
		sysDeptDao.updateByIdSelective(newSysDept);
		if(oldLevel !=null && !oldLevel.equals(newLevel)) {
			List<SysDept> sysDeptChilds = sysDeptDao.findSysDeptChildByLevel(newSysDept.getLevel());
			if(CollectionUtils.isNotEmpty(sysDeptChilds)) {
				for(SysDept sysDeptChild : sysDeptChilds) {
					sysDeptChild.setLevel(sysDeptChild.getLevel().replace(oldLevel, newLevel));
					sysDeptChild.setOperator("UpdateUser");// TODO
					sysDeptChild.setOperateIp("127.0.0.1");// TODO
					sysDeptDao.updateByIdSelective(sysDeptChild);
				}
			}	
		}		
	}
	
	/**
	 * @Description: 同一层级下是否含有相同部门名称
	 * @param id 部门id
	 * @param name 部门名称
	 * @param parentId  部门父类id 
	 */
	private void checkSysDeptName(Integer id,String name,Integer parentId) {
		int num = sysDeptDao.checkSysDeptName(id, name, parentId);
		if(num > 0) throw new ParamException("同一部门下存在相同的部门名称");
	}
	
	/**
	 * @Description: 查询当前层级
	 * @param parentId
	 * @return SysDept    
	 */
	private String getLevel(Integer parentId) {
		SysDept sysDept = sysDeptDao.selectById(parentId);
		return LevelUtil.getLevel(sysDept == null ? null : sysDept.getLevel(), parentId);
	}
	
	@Override
	public List<SysDeptDto> findSysDeptDtoTree() {
		
		List<SysDept> sysDepts = sysDeptDao.findSysDept();
		
		// 封装树节点
		Map<String,List<SysDeptDto>> sysDeptTreeNodes = new HashMap<String,List<SysDeptDto>>();
		for(SysDept sysDept : sysDepts) {
			String level = sysDept.getLevel();
			List<SysDeptDto> sysDeptTreeNode = sysDeptTreeNodes.get(level);
			if(CollectionUtils.isEmpty(sysDeptTreeNode)) {
				sysDeptTreeNode = new ArrayList<SysDeptDto>(); 
				sysDeptTreeNodes.put(level, sysDeptTreeNode);
			}
			SysDeptDto sysDeptDto = new SysDeptDto();
			try {
				BeanUtils.copyProperties(sysDeptDto, sysDept);
			} catch (IllegalAccessException | InvocationTargetException e) {
				logger.error("数据转换失败",e);
			}
			sysDeptTreeNode.add(sysDeptDto);
		}
		
		//找到所有根节点并排序
		List<SysDeptDto> rootSysDepts = sysDeptTreeNodes.get(LevelUtil.getRoot());
		if(CollectionUtils.isNotEmpty(rootSysDepts)) {
			Collections.sort(rootSysDepts, new Comparator<SysDeptDto>() {

				@Override
				public int compare(SysDeptDto o1, SysDeptDto o2) {
					return o1.getSeq() - o2.getSeq();
				}
			});
			for(SysDeptDto rootSysDept : rootSysDepts) {
				transformTree(rootSysDept, sysDeptTreeNodes);
			}
		}
		return rootSysDepts;
	}


	/**
	 * @Description: 循环遍历构建属性结构
	 * @param rootSysDept
	 * @param sysDeptTreeNodes    
	 * @throws
	 */
	private void transformTree(SysDeptDto rootSysDept,Map<String,List<SysDeptDto>> sysDeptTreeNodes) {
		
		String childNodeLevel = StringUtils.join(rootSysDept.getLevel(),".",rootSysDept.getId());
		List<SysDeptDto> sysDeptNodes = sysDeptTreeNodes.get(childNodeLevel);
		if(CollectionUtils.isNotEmpty(sysDeptNodes)) {
			Collections.sort(sysDeptNodes,new Comparator<SysDeptDto>() {
				@Override
				public int compare(SysDeptDto o1, SysDeptDto o2) {
					return o1.getSeq() - o2.getSeq();
				}
				
			});
			rootSysDept.setSysDepts(sysDeptNodes);
			
			for(SysDeptDto sysDeptNode : sysDeptNodes) {
				transformTree(sysDeptNode, sysDeptTreeNodes);
			}
		}
	}

}
