package com.iflytransporter.common.bean;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.annotation.JSONField;

public class Area implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	@JSONField(serialize=false)
	private Date createDate;
	
	@JSONField(serialize=false)
	private Date updateDate;
	@JSONField(serialize=false)
	private int dataStatus;
	
	@JSONField(serialize=false)
	private String desp;

	private String name;
	
	@JSONField(serialize=false)
    private String cityId;
	@JSONField(serialize=false)
    private String provinceId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

	public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId == null ? null : cityId.trim();
    }

    public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public int getDataStatus() {
		return dataStatus;
	}

	public void setDataStatus(int dataStatus) {
		this.dataStatus = dataStatus;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String desp) {
		this.desp = desp;
	}
	@Override
  	public String toString() {
    	  return JSONObject.toJSONString(this).toString();
  	}
}