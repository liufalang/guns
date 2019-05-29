package cn.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

public class EquipmentInfoDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String  id;

    private String eptCode;		// 编号

    private String eptOem;		// 生产厂家

    private String eptModel;		// 设备型号

    private String factoryCode;		// 出厂编号

    private String measurRang;		// 测量范围

    private String eptPerson;		// 设备负责人

    private Date procurementTime;		// 采购日期

    private Date openTime;		// 启用日期

    private String eptValue;		// 设备原值

    private Long deptId;		// 、使用部门

    private String professionalUse;		// 使用专业

    private String storageLocation;		// 存放地点

    private String eptType;		// 管理类别

    private String eptStatus;		// 设备状态

    private String isStandardDevice;		// 是否标准器

    private String uncertainty;		// 不确定度/最大允许误差

    private String eptName;		// 设备名称

    private String deptName;

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

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
