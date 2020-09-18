package com.kingdee.inte.teamwork.google;

/**
 * description:
 *
 * @author Andy
 * @version 1.0
 * @date 06/23/2020 22:58
 */
public class GooglePrivateConf {
	private String type;
	private String projectId;
	private String projectNum;
	private String privateKeyId;
	private String privateKey;
	private String clientEmail;
	private String clientId;
	private String authUri;
	private String tokenUri;
	private String authProviderX509CertUrl;
	private String clientX509CertUrl;
	private String scope;
	private String vpnHost;
	private int vpnPort;
	private String protocol;
	private String glossaryBucket;
	private String glossaryFilename;
	private String glossaryId;

	public GooglePrivateConf() {
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getPrivateKeyId() {
		return privateKeyId;
	}

	public void setPrivateKeyId(String privateKeyId) {
		this.privateKeyId = privateKeyId;
	}

	public String getPrivateKey() {
		return privateKey;
	}

	public void setPrivateKey(String privateKey) {
		this.privateKey = privateKey;
	}

	public String getClientEmail() {
		return clientEmail;
	}

	public void setClientEmail(String clientEmail) {
		this.clientEmail = clientEmail;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getAuthUri() {
		return authUri;
	}

	public void setAuthUri(String authUri) {
		this.authUri = authUri;
	}

	public String getTokenUri() {
		return tokenUri;
	}

	public void setTokenUri(String tokenUri) {
		this.tokenUri = tokenUri;
	}

	public String getAuthProviderX509CertUrl() {
		return authProviderX509CertUrl;
	}

	public void setAuthProviderX509CertUrl(String authProviderX509CertUrl) {
		this.authProviderX509CertUrl = authProviderX509CertUrl;
	}

	public String getClientX509CertUrl() {
		return clientX509CertUrl;
	}

	public void setClientX509CertUrl(String clientX509CertUrl) {
		this.clientX509CertUrl = clientX509CertUrl;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public String getVpnHost() {
		return vpnHost;
	}

	public void setVpnHost(String vpnHost) {
		this.vpnHost = vpnHost;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getVpnPort() {
		return vpnPort;
	}

	public void setVpnPort(int vpnPort) {
		this.vpnPort = vpnPort;
	}


	public String getGlossaryFilename() {
		return glossaryFilename;
	}

	public void setGlossaryFilename(String glossaryFilename) {
		this.glossaryFilename = glossaryFilename;
	}

	public String getGlossaryId() {
		return glossaryId;
	}

	public void setGlossaryId(String glossaryId) {
		this.glossaryId = glossaryId;
	}

	public String getGlossaryBucket() {
		return glossaryBucket;
	}

	public void setGlossaryBucket(String glossaryBucket) {
		this.glossaryBucket = glossaryBucket;
	}

	public String getProjectNum() {
		return projectNum;
	}

	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}

	@Override
	public String toString() {
		return "GooglePrivateConf{" +
				"type='" + type + '\'' +
				", projectId='" + projectId + '\'' +
				", projectNum='" + projectNum + '\'' +
				", privateKeyId='" + privateKeyId + '\'' +
				", privateKey='" + privateKey + '\'' +
				", clientEmail='" + clientEmail + '\'' +
				", clientId='" + clientId + '\'' +
				", authUri='" + authUri + '\'' +
				", tokenUri='" + tokenUri + '\'' +
				", authProviderX509CertUrl='" + authProviderX509CertUrl + '\'' +
				", clientX509CertUrl='" + clientX509CertUrl + '\'' +
				", scope='" + scope + '\'' +
				", vpnHost='" + vpnHost + '\'' +
				", vpnPort=" + vpnPort +
				", protocol='" + protocol + '\'' +
				", glossaryBucket='" + glossaryBucket + '\'' +
				", glossaryFilename='" + glossaryFilename + '\'' +
				", glossaryId='" + glossaryId + '\'' +
				'}';
	}
}
