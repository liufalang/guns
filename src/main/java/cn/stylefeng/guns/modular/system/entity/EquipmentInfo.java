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
 * 设备信息表Entity
 * @author Hexb
 * @version 2019-05-23
 */
@TableName("equipment_info")
public class EquipmentInfo  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.ID_WORKER)
	private String  id;
	@TableField("ept_code")
	private String eptCode;		// 编号
	@TableField("ept_oem")
	private String eptOem;		// 生产厂家
	@TableField("ept_model")
	private String eptModel;		// 设备型号
	@TableField("factory_code")
	private String factoryCode;		// 出厂编号
	@TableField("measur_rang")
	private String measurRang;		// 测量范围
	@TableField("ept_person")
	private String eptPerson;		// 设备负责人
	@TableField("procurement_time")
	private Date procurementTime;		// 采购日期
	@TableField("open_time")
	private Date openTime;		// 启用日期
	@TableField("ept_value")
	private String eptValue;		// 设备原值
	@TableField("dept_id")
	private Long deptId;		// 、使用部门
	@TableField("professional_use")
	private String professionalUse;		// 使用专业
	@TableField("storage_location")
	private String storageLocation;		// 存放地点
	@TableField("ept_type")
	private String eptType;		// 管理类别
	@TableField("ept_status")
	private String eptStatus;		// 设备状态
	@TableField("is_standard_device")
	private String isStandardDevice;		// 是否标准器
	@TableField("uncertainty")
	private String uncertainty;		// 不确定度/最大允许误差
	@TableField("ept_name")
	private String eptName;		// 设备名称

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEptCode() {
		return eptCode;
	}

	public void setEptCode(String eptCode) {
		this.eptCode = eptCode;
	}

	public String getEptOem() {
		return eptOem;
	}

	public void setEptOem(String eptOem) {
		this.eptOem = eptOem;
	}

	public String getEptModel() {
		return eptModel;
	}

	public void setEptModel(String eptModel) {
		this.eptModel = eptModel;
	}

	public String getFactoryCode() {
		return factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public String getMeasurRang() {
		return measurRang;
	}

	public void setMeasurRang(String measurRang) {
		this.measurRang = measurRang;
	}

	public String getEptPerson() {
		return eptPerson;
	}

	public void setEptPerson(String eptPerson) {
		this.eptPerson = eptPerson;
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

	public String getEptValue() {
		return eptValue;
	}

	public void setEptValue(String eptValue) {
		this.eptValue = eptValue;
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

	public String getEptType() {
		return eptType;
	}

	public void setEptType(String eptType) {
		this.eptType = eptType;
	}

	public String getEptStatus() {
		return eptStatus;
	}

	public void setEptStatus(String eptStatus) {
		this.eptStatus = eptStatus;
	}

	public String getIsStandardDevice() {
		return isStandardDevice;
	}

	public void setIsStandardDevice(String isStandardDevice) {
		this.isStandardDevice = isStandardDevice;
	}

	public String getUncertainty() {
		return uncertainty;
	}

	public void setUncertainty(String uncertainty) {
		this.uncertainty = uncertainty;
	}

	public String getEptName() {
		return eptName;
	}

	public void setEptName(String eptName) {
		this.eptName = eptName;
	}
}