/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package cn.stylefeng.guns.modular.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 工装信息表Entity
 * @author Hexb
 * @version 2019-05-23
 */
@TableName("frock_info")
public class FrockInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.ID_WORKER)
	private String  id;
	@TableField("fro_code")
	private String froCode;		// 编号
	@TableField("fro_oem")
	private String froOem;		// 生产厂家
	@TableField("fro_model")
	private String froModel;		// 工装型号
	@TableField("factory_code")
	private String factoryCode;		// 出厂编号
	@TableField("fro_person")
	private String froPerson;		// 工装负责人
	@TableField("procurement_time")
	private Date procurementTime;		// 采购日期
	@TableField("open_time")
	private Date openTime;		// 启用日期
	@TableField("dept_id")
	private Long deptId;		// 、使用部门
	@TableField("professional_use")
	private String professionalUse;		// 使用专业
	@TableField("storage_location")
	private String storageLocation;		// 存放地点
	@TableField("fro_status")
	private String froStatus;		//
	@TableField("is_check")
	private String isCheck;		// 是否需要检定
	@TableField("fro_name")
	private String froName;		// 工装名称

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