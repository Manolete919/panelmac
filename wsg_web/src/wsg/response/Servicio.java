package wsg.response;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Servicio", propOrder={"proveedorBase","codigoError","xml","mensajeError"}, namespace = "http://axis/EISApiOnlineWS.wsdl/types/") 
public class Servicio implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String proveedorBase;
	private int codigoError;
	private Object xml;
	private String mensajeError;
	
	
	public String getProveedorBase() {
		return proveedorBase;
	}
	public void setProveedorBase(String proveedorBase) {
		this.proveedorBase = proveedorBase;
	}
	public int getCodigoError() {
		return codigoError;
	}
	public void setCodigoError(int codigoError) {
		this.codigoError = codigoError;
	}
	@XmlAnyElement
	public Object getXml() {
		return xml;
	}
	@XmlAnyElement
	public void setXml(Object xml) {
		this.xml = xml;
	}
	public String getMensajeError() {
		return mensajeError;
	}
	public void setMensajeError(String mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	

}
