package cn.superion.cssd.entity;

/**
 * CssdChemistryFiles entity. @author MyEclipse Persistence Tools
 */

public class CssdChemistryFiles implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1081090301056206335L;
	private String autoId;
	private String mainAutoId;
	private String serialNo;
	private String sysFile;
	private String fileName;
	private String filePath;

	// Constructors

	/** default constructor */
	public CssdChemistryFiles() {
	}

	/** minimal constructor */
	public CssdChemistryFiles(String autoId, String mainAutoId,
			String serialNo, String sysFile) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
	}

	/** full constructor */
	public CssdChemistryFiles(String autoId, String mainAutoId,
			String serialNo, String sysFile, String fileName, String filePath) {
		this.autoId = autoId;
		this.mainAutoId = mainAutoId;
		this.serialNo = serialNo;
		this.sysFile = sysFile;
		this.fileName = fileName;
		this.filePath = filePath;
	}

	// Property accessors

	public String getAutoId() {
		return this.autoId;
	}

	public void setAutoId(String autoId) {
		this.autoId = autoId;
	}

	public String getMainAutoId() {
		return this.mainAutoId;
	}

	public void setMainAutoId(String mainAutoId) {
		this.mainAutoId = mainAutoId;
	}

	public String getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(String serialNo) {
		this.serialNo = serialNo;
	}

	public String getSysFile() {
		return this.sysFile;
	}

	public void setSysFile(String sysFile) {
		this.sysFile = sysFile;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}