package cn.superion.material.entity;

import java.util.Date;

/**
 * MaterialCurrentStock entity. @author MyEclipse Persistence Tools
 */

public class MaterialCurrentStock implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8606753572936370683L;
	private String autoId;
	private String unitsCode;
	private String storageCode;
	private String materialClass;
	private String barCode;
	private String materialId;
	private String materialCode;
	private String materialName;
	private String materialSpec;
	private String materialUnits;
	private Double tradePrice;
	private Double factTradePrice;
	private Double wholeSalePrice;
	private Double invitePrice;
	private Double retailPrice;
	private Double amount;
	private Double acctAmount;
	private String factoryCode;
	private Date madeDate;
	private String batch;
	private Date availDate;
	private String position;
	private String highValueSign;
	private String agentSign;
	private String remark;
	private Double virtualAmount;

	// Constructors

	public Double getVirtualAmount() {
		return virtualAmount;
	}

	public void setVirtualAmount(Double virtualAmount) {
		this.virtualAmount = virtualAmount;
	}

	/** default constructor */
	public MaterialCurrentStock() {
	}

	public MaterialCurrentStock(String unitsCode, String storageCode,
			String barCode, String materialId, String batch) {
		super();
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.barCode = barCode;
		this.materialId = materialId;
		this.batch = batch;
	}

	/** minimal constructor */
	public MaterialCurrentStock(String autoId, String unitsCode,
			String storageCode, String materialClass, String materialId,
			String materialCode, String materialName) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
	}

	/** full constructor */
	public MaterialCurrentStock(String autoId, String unitsCode,
			String storageCode, String materialClass, String materialId,
			String materialCode, String materialName, String materialSpec,
			String materialUnits, Double tradePrice, Double factTradePrice,
			Double wholeSalePrice, Double invitePrice, Double retailPrice,
			Double amount, Double acctAmount, String factoryCode,
			Date madeDate, String batch, Date availDate, String position,
			String highValueSign, String agentSign, String remark) {
		this.autoId = autoId;
		this.unitsCode = unitsCode;
		this.storageCode = storageCode;
		this.materialClass = materialClass;
		this.materialId = materialId;
		this.materialCode = materialCode;
		this.materialName = materialName;
		this.materialSpec = materialSpec;
		this.materialUnits = materialUnits;
		this.tradePrice = tradePrice;
		this.factTradePrice = factTradePrice;
		this.wholeSalePrice = wholeSalePrice;
		this.invitePrice = invitePrice;
		this.retailPrice = retailPrice;
		this.amount = amount;
		this.acctAmount = acctAmount;
		this.factoryCode = factoryCode;
		this.madeDate = madeDate;
		this.batch = batch;
		this.availDate = availDate;
		this.position = position;
		this.highValueSign = highValueSign;
		this.agentSign = agentSign;
		this.remark = remark;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getUnitsCode() {
		return this.unitsCode;
	}

	public void setUnitsCode(String unitsCode) {
		this.unitsCode = unitsCode;
	}

	public String getStorageCode() {
		return this.storageCode;
	}

	public void setStorageCode(String storageCode) {
		this.storageCode = storageCode;
	}

	public String getMaterialClass() {
		return this.materialClass;
	}

	public void setMaterialClass(String materialClass) {
		this.materialClass = materialClass;
	}

	public String getMaterialId() {
		return this.materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getMaterialCode() {
		return this.materialCode;
	}

	public void setMaterialCode(String materialCode) {
		this.materialCode = materialCode;
	}

	public String getMaterialName() {
		return this.materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialSpec() {
		return this.materialSpec;
	}

	public void setMaterialSpec(String materialSpec) {
		this.materialSpec = materialSpec;
	}

	public String getMaterialUnits() {
		return this.materialUnits;
	}

	public void setMaterialUnits(String materialUnits) {
		this.materialUnits = materialUnits;
	}

	public Double getTradePrice() {
		if (this.tradePrice == null) {
			return 0d;
		}
		return this.tradePrice;
	}

	public void setTradePrice(Double tradePrice) {
		this.tradePrice = tradePrice;
	}

	public Double getFactTradePrice() {
		if (this.factTradePrice == null) {
			return 0d;
		}
		return factTradePrice;
	}

	public void setFactTradePrice(Double factTradePrice) {
		this.factTradePrice = factTradePrice;
	}

	public Double getWholeSalePrice() {
		if (this.wholeSalePrice == null) {
			return 0d;
		}
		return wholeSalePrice;
	}

	public void setWholeSalePrice(Double wholeSalePrice) {
		this.wholeSalePrice = wholeSalePrice;
	}

	public Double getInvitePrice() {
		if (this.invitePrice == null) {
			return 0d;
		}
		return invitePrice;
	}

	public void setInvitePrice(Double invitePrice) {
		this.invitePrice = invitePrice;
	}

	public Double getRetailPrice() {
		if (this.retailPrice == null) {
			return 0d;
		}
		return retailPrice;
	}

	public void setRetailPrice(Double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Double getAmount() {
		if (this.amount == null) {
			return 0d;
		}
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getAcctAmount() {
		if (this.acctAmount == null) {
			return 0d;
		}
		return this.acctAmount;
	}

	public void setAcctAmount(Double acctAmount) {
		this.acctAmount = acctAmount;
	}

	public String getFactoryCode() {
		return this.factoryCode;
	}

	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}

	public Date getMadeDate() {
		return this.madeDate;
	}

	public void setMadeDate(Date madeDate) {
		this.madeDate = madeDate;
	}

	public String getBatch() {
		return this.batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Date getAvailDate() {
		return this.availDate;
	}

	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}

	public String getPosition() {
		return this.position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getHighValueSign() {
		return this.highValueSign;
	}

	public void setHighValueSign(String highValueSign) {
		this.highValueSign = highValueSign;
	}

	public String getAgentSign() {
		return this.agentSign;
	}

	public void setAgentSign(String agentSign) {
		this.agentSign = agentSign;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((barCode == null) ? 0 : barCode.hashCode());
		result = prime * result + ((batch == null) ? 0 : batch.hashCode());
		result = prime * result
				+ ((materialId == null) ? 0 : materialId.hashCode());
		result = prime * result
				+ ((storageCode == null) ? 0 : storageCode.hashCode());
		result = prime * result
				+ ((unitsCode == null) ? 0 : unitsCode.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MaterialCurrentStock other = (MaterialCurrentStock) obj;
		if (barCode == null) {
			if (other.barCode != null)
				return false;
		} else if (!barCode.equals(other.barCode))
			return false;
		if (batch == null) {
			if (other.batch != null)
				return false;
		} else if (!batch.equals(other.batch))
			return false;
		if (materialId == null) {
			if (other.materialId != null)
				return false;
		} else if (!materialId.equals(other.materialId))
			return false;
		if (storageCode == null) {
			if (other.storageCode != null)
				return false;
		} else if (!storageCode.equals(other.storageCode))
			return false;
		if (unitsCode == null) {
			if (other.unitsCode != null)
				return false;
		} else if (!unitsCode.equals(other.unitsCode))
			return false;
		return true;
	}

}