/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;


/**
 * 工装信息表Entity
 * @author Hexb
 * @version 2019-05-23
 */
public class FrockInfoDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String  id;

	private String froCode;		// 编号

	private String froOem;		// 生产厂家

	private String froModel;		// 工装型号

	private String factoryCode;		// 出厂编号

	private String froPerson;		// 工装负责人

	private Date procurementTime;		// 采购日期

	private Date openTime;		// 启用日期

	private Long deptId;		// 、使用部门

	private String professionalUse;		// 使用专业

	private String storageLocation;		// 存放地点

	private String froStatus;		//

	private String isCheck;		// 是否需要检定

	private String froName;		// 工装名称

	private String  deptName;

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFroCode() {
		return froCode;
	}

	public void setFroCode(String froCode) {
		this.froCode = froCode;
	}

	public String getFroOem() {
		return froOem;
	}

	public void setFroOem(String froOem) {
		this.froOem = froOem;
	}

	public String getFroModel() {
		return froModel;
	}

	public void setFroModel(String froModel) {
		this.froModel = froModel;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getFroPerson() {
		return froPerson;
	}

	public void setFroPerson(String froPerson) {
		this.froPerson = froPerson;
	}

	public Date getProcurementTime() {
		return procurementTime;
	}

	public void setProcurementTime(Date procurementTime) {
		this.procurementTime = procurementTime;
	}

	public Date getOpenTime() {
		return openTime;
	}

	public void setOpenTime(Date openTime) {
		this.openTime = openTime;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getProfessionalUse() {
		return professionalUse;
	}

	public void setProfessionalUse(String professionalUse) {
		this.professionalUse = professionalUse;
	}

	public String getStorageLocation() {
		return storageLocation;
	}

	public void setStorageLocation(String storageLocation) {
		this.storageLocation = storageLocation;
	}

	public String getFroStatus() {
		return froStatus;
	}

	public void setFroStatus(String froStatus) {
		this.froStatus = froStatus;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getFroName() {
		return froName;
	}

	public void setFroName(String froName) {
		this.froName = froName;
	}
}