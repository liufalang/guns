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
 * 设备检定信息表Entity
 * @author Hexb
 * @version 2019-05-23
 */
@TableName("docimasy_info")
public class DocimasyInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@TableId(value = "id", type = IdType.ID_WORKER)
	private String  id;
	@TableField("dcs_code")
	private String dcsCode;		// 检定编号
	@TableField("type_code")
	private String typeCode;		// 设备工装所内统一编号
	@TableField("dcs_inspection")
	private String dcsInspection;		// 送检方式
	@TableField("dcs_Month")
	private String dcsMonth;		// 检定周期
	@TableField("dcs_unit")
	private String dcsUnit;		// 承检单位
	@TableField("is_dcs_scene")
	private String isDcsScene;		// 是否现场检定
	@TableField("dcs_cost")
	private Integer dcsCost;		// 检定费用
	@TableField("other_cost")
	private Integer otherCost;		// 其他费用
	@TableField("dcs_time")
	private Date dcsTime;		// 检定日期
	@TableField("dcs_next_time")
	private Date dcsNextTime;		// 下次检定日期
	@TableField("dcs_certificate_code")
	private String dcsCertificateCode;		// 证书编号
	@TableField("dcs_result")
	private String dcsResult;		// 检定结论
	@TableField("type_id")
	private Long typeId;		// 设备 工装id

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDcsCode() {
		return dcsCode;
	}

	public void setDcsCode(String dcsCode) {
		this.dcsCode = dcsCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getDcsInspection() {
		return dcsInspection;
	}

	public void setDcsInspection(String dcsInspection) {
		this.dcsInspection = dcsInspection;
	}

	public String getDcsMonth() {
		return dcsMonth;
	}

	public void setDcsMonth(String dcsMonth) {
		this.dcsMonth = dcsMonth;
	}

	public String getDcsUnit() {
		return dcsUnit;
	}

	public void setDcsUnit(String dcsUnit) {
		this.dcsUnit = dcsUnit;
	}

	public String getIsDcsScene() {
		return isDcsScene;
	}

	public void setIsDcsScene(String isDcsScene) {
		this.isDcsScene = isDcsScene;
	}

	public Integer getDcsCost() {
		return dcsCost;
	}

	public void setDcsCost(Integer dcsCost) {
		this.dcsCost = dcsCost;
	}

	public Integer getOtherCost() {
		return otherCost;
	}

	public void setOtherCost(Integer otherCost) {
		this.otherCost = otherCost;
	}

	public Date getDcsTime() {
		return dcsTime;
	}

	public void setDcsTime(Date dcsTime) {
		this.dcsTime = dcsTime;
	}

	public Date getDcsNextTime() {
		return dcsNextTime;
	}

	public void setDcsNextTime(Date dcsNextTime) {
		this.dcsNextTime = dcsNextTime;
	}

	public String getDcsCertificateCode() {
		return dcsCertificateCode;
	}

	public void setDcsCertificateCode(String dcsCertificateCode) {
		this.dcsCertificateCode = dcsCertificateCode;
	}

	public String getDcsResult() {
		return dcsResult;
	}

	public void setDcsResult(String dcsResult) {
		this.dcsResult = dcsResult;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}
}