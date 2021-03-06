package cn.superion.materialDept.other.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.superion.base.ParameterObject;
import cn.superion.base.ReObject;
import cn.superion.center.config.dao.CdSysParamDAO;
import cn.superion.materialDept.common.service.ICommMaterialService;
import cn.superion.material.common.RdConstant;
import cn.superion.materialDept.dao.MaterialRejectDetailDeptDAO;
import cn.superion.materialDept.dao.MaterialRejectMasterDeptDAO;
import cn.superion.materialDept.entity.MaterialRdsDetailDept;
import cn.superion.materialDept.entity.MaterialRdsMasterDept;
import cn.superion.materialDept.entity.MaterialRejectDetailDept;
import cn.superion.materialDept.entity.MaterialRejectMasterDept;
import cn.superion.system.entity.SysUser;
import cn.superion.util.SessionUtil;
/**
 * 物资报损服务实现
 * @author 曹国魁
 *
 */
public class RejectImpl implements IReject {
	private MaterialRejectMasterDeptDAO materialRejectMasterDeptDAO;
	private MaterialRejectDetailDeptDAO materialRejectDetailDeptDAO;
	private CdSysParamDAO cdSysParamDAO;
	private ICommMaterialService deptCommMaterialServiceImpl;

	public MaterialRejectMasterDeptDAO getMaterialRejectMasterDeptDAO() {
		return materialRejectMasterDeptDAO;
	}

	public void setMaterialRejectMasterDeptDAO(
			MaterialRejectMasterDeptDAO materialRejectMasterDeptDAO) {
		this.materialRejectMasterDeptDAO = materialRejectMasterDeptDAO;
	}

	public MaterialRejectDetailDeptDAO getMaterialRejectDetailDeptDAO() {
		return materialRejectDetailDeptDAO;
	}

	public void setMaterialRejectDetailDeptDAO(
			MaterialRejectDetailDeptDAO materialRejectDetailDeptDAO) {
		this.materialRejectDetailDeptDAO = materialRejectDetailDeptDAO;
	}

	public ICommMaterialService getDeptCommMaterialServiceImpl() {
		return deptCommMaterialServiceImpl;
	}

	public void setDeptCommMaterialServiceImpl(
			ICommMaterialService deptCommMaterialServiceImpl) {
		this.deptCommMaterialServiceImpl = deptCommMaterialServiceImpl;
	}

	public CdSysParamDAO getCdSysParamDAO() {
		return cdSysParamDAO;
	}

	public void setCdSysParamDAO(CdSysParamDAO cdSysParamDAO) {
		this.cdSysParamDAO = cdSysParamDAO;
	}

	@Override
	public ReObject deleteReject(String fstrAutoId) {
		ReObject ro = new ReObject("删除当前未审核的物资报损单");
		MaterialRejectMasterDept master = materialRejectMasterDeptDAO.findById(fstrAutoId);
		if(master == null)
			throw new RuntimeException("物资报损单不存在！");
		if("1".equals(master.getCurrentStatus())){
			throw new RuntimeException("物资报损记录已审核，不能删除！");
		}
		materialRejectDetailDeptDAO.delByMainAutoId(fstrAutoId);
		materialRejectMasterDeptDAO.delete(master);
		return ro;
	}

	@Override
	public ReObject findRejectDetailById(String fstrAutoId) {
		ReObject ro = new ReObject("查看当前物资报损单的详细信息记录");
		MaterialRejectMasterDept master = materialRejectMasterDeptDAO.findById(fstrAutoId);
		List<MaterialRejectDetailDept> details = materialRejectDetailDeptDAO.findByMainAutoId(fstrAutoId);
		List<Object> data = new ArrayList<Object>();
		data.add(master);
		data.add(details);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject findRejectMasterListByCondition(ParameterObject fparameter) {
		ReObject ro = new ReObject("根据查找条件，获取当前符合条件的物资报损单据列表");
		SysUser user = SessionUtil.getSysUser();
		fparameter.getConditions().put("storageCode", user.getDeptCode());
		List<Object> data = materialRejectMasterDeptDAO.findAutoIdsByCondition(user.getUnitsCode(),fparameter.getConditions());
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject saveReject(MaterialRejectMasterDept fmaster,
			List<MaterialRejectDetailDept> fdetails) {
		ReObject ro = new ReObject("保存当前物资报损单信息");
		if(fmaster == null)
			throw new RuntimeException("物资报损单主记录不能为空！");
		if(fdetails == null || fdetails.isEmpty())
			throw new RuntimeException("物资报损单明细记录不能为空！");
		SysUser user = SessionUtil.getSysUser();
		String unitsCode = user.getUnitsCode();
		String personId = user.getPersonId();
		Date curDate = new Date();
		String storageCode = user.getDeptCode();
		if(storageCode == null || "".equals(storageCode)){
			storageCode = "0";
		}
		fmaster.setStorageCode(storageCode);
		String autoId = fmaster.getAutoId(); 
		if(autoId == null || "".equals(autoId)){
			//新增
			String billNo = fmaster.getBillNo();
			if(billNo == null || "".equals(billNo)){
				fmaster.setBillNo(deptCommMaterialServiceImpl.getNextBillNo(RdConstant.OTHERS));
			}else{
				//新增时，校验手工输入的流水号在一个单位，一个仓库中唯一性
				if(!materialRejectMasterDeptDAO.checkBillNoUnique(unitsCode,storageCode,billNo)){
					throw new RuntimeException("手工输入的单据编号["+billNo+"]在单位["+unitsCode+"],仓库["+storageCode+"]下有重复");
				}
			}
			if(fmaster.getBillDate() == null)
				fmaster.setBillDate(curDate);
			if(fmaster.getPersonId() == null || "".equals(fmaster.getPersonId()))
				fmaster.setPersonId(personId);
			fmaster.setUnitsCode(unitsCode);
			fmaster.setMakeDate(curDate);
			fmaster.setMaker(personId);
			fmaster.setCurrentStatus("0");
			materialRejectMasterDeptDAO.save(fmaster);
			autoId = fmaster.getAutoId();
		}else{
			//修改
			MaterialRejectMasterDept original = materialRejectMasterDeptDAO.findById(autoId);
			if(original == null){
				throw new RuntimeException("不存在系统标识号为"+autoId+"的物资报损单主记录！");
			}
			if("1".equals(original.getCurrentStatus())){
				throw new RuntimeException("物资报损单已审核，不能修改！");
			}
			fmaster.setCurrentStatus("0");
			materialRejectMasterDeptDAO.merge(fmaster);
			materialRejectDetailDeptDAO.delByMainAutoId(autoId);
		}
		short i = 0;
		for(MaterialRejectDetailDept detail : fdetails){
			detail.setMainAutoId(autoId);
			detail.setSerialNo(++i);
			materialRejectDetailDeptDAO.save(detail);
		}
		List<Object> data = new ArrayList<Object>();
		data.add(fmaster);
		data.add(fdetails);
		ro.setData(data);
		return ro;
	}

	@Override
	public ReObject verifyReject(String fstrAutoId) {
		ReObject ro = new ReObject("审核当前的物资报损单");
		MaterialRejectMasterDept original = materialRejectMasterDeptDAO.findById(fstrAutoId);
		if(original == null){
			throw new RuntimeException("不存在系统标识号为"+fstrAutoId+"的物资报损单！");
		}
		if("1".equals(original.getCurrentStatus())){
			throw new RuntimeException("物资报损单已审核，不能审核！");
		}
		original.setCurrentStatus("1");
		original.setVerifyDate(new Date());
		original.setVerifier(SessionUtil.getPersonId());
		String appCode = SessionUtil.getAppCode();
		//写出库单
		//是否要更新库存
		boolean isVerified = "1".equals(cdSysParamDAO.findByParaCode(original.getUnitsCode(), appCode, RdConstant.SYS_PARA_CODE_REJECT_LAUNCH,"0"));
		MaterialRdsMasterDept master = new MaterialRdsMasterDept();
		master.setUnitsCode(original.getUnitsCode());
		master.setStorageCode(original.getStorageCode());
		//单据类型
		master.setInvoiceType("1");
		//收发标志
		master.setRdFlag(RdConstant.D);
		//收发类别
		master.setRdType(original.getRdType());
		//业务类型
		master.setOperationType(RdConstant.D_REJECT);
		//业务号
		master.setOperationNo(original.getBillNo());
		master.setCurrentStatus(isVerified?"1":"0");
		List<MaterialRejectDetailDept> rejectDetails =  materialRejectDetailDeptDAO.findByMainAutoId(fstrAutoId);
		List<MaterialRdsDetailDept> details = new ArrayList<MaterialRdsDetailDept>();
		for(MaterialRejectDetailDept rejectDetail : rejectDetails){
			MaterialRdsDetailDept rdsDetail = rejectDetail.buildMaterialRdsDetail();
			details.add(rdsDetail);
		}
		deptCommMaterialServiceImpl.save(master, details);
		List<Object> data = new ArrayList<Object>();
		data.add(original);
		ro.setData(data);
		return ro;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object[] writeRejectDeliverRed(String fstrAutoId) {
		Object[] objs = new Object[2];
		MaterialRejectMasterDept original =  materialRejectMasterDeptDAO.findById(fstrAutoId);
		if(original != null){
			if("1".equals(original.getCurrentStatus())){
				Object[] blueObjs = deptCommMaterialServiceImpl.findByOperationNo(original.getUnitsCode(), original.getStorageCode(), RdConstant.D_REJECT, original.getBillNo());
				MaterialRdsMasterDept blueMaster = (MaterialRdsMasterDept)blueObjs[0];
				//报损出库审核时写报损出库红单
				if(blueMaster != null && "1".equals(blueMaster.getCurrentStatus())){
					MaterialRdsMasterDept redMaster =  blueMaster.clone();
					redMaster.setInvoiceType("2");
					redMaster.setCurrentStatus("1");
					redMaster.setVerifier(SessionUtil.getPersonId());
					redMaster.setVerifyDate(new Date());
					List<MaterialRdsDetailDept> details = (List<MaterialRdsDetailDept>) blueObjs[1];
					List<MaterialRdsDetailDept> redDetails = new ArrayList<MaterialRdsDetailDept>();
					for(MaterialRdsDetailDept detail : details){
						redDetails.add(detail.writeRed());
					}
					return deptCommMaterialServiceImpl.save(redMaster, redDetails);
				}
				//还应写报损单红单
				//TODO
			}
			//未审核的报损单，应清空
			materialRejectDetailDeptDAO.delByMainAutoId(fstrAutoId);
			materialRejectMasterDeptDAO.delete(original);
		}
		return objs;
	}

}
