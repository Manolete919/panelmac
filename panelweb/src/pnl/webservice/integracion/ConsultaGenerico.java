package pnl.webservice.integracion;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.axis.message.MessageElement;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import pnl.wsg.Bind;
import pnl.wsg.GenericoPortType;
import pnl.wsg.GenericoServiceLocator;
import pnl.wsg.Servicio;
import pnl.wsg.ServicioElement;
import pnl.wsg.ServicioResponseElement;


public class ConsultaGenerico implements Serializable {
	private static final long serialVersionUID = 1L;


	public Servicio consultarServicioWebGenerico(Document documento,long idServicio, String usuario, String clave) {
		// TODO Auto-generated method stub
		
		try {
			
			GenericoServiceLocator localizadorDeServicio = new GenericoServiceLocator();
			GenericoPortType genericoPortType = localizadorDeServicio.getGenericoPortTypePort();

			
			ServicioElement parameters = new ServicioElement();
			
			parameters.setIdServicio(idServicio);
			parameters.setUsuario(usuario);
			parameters.setClave(clave);
			
			
			Bind sentencias_binds = new Bind();
			MessageElement[] _any = new MessageElement[1];
			
				
			Element element = (Element) documento.getDocumentElement().getChildNodes().item(0);
			_any[0] = new MessageElement(element);
			
			sentencias_binds.set_any(_any);
			parameters.setSentencias_binds(sentencias_binds);
			
			
			ServicioResponseElement servicioResponseElement = genericoPortType.obtenerXml(parameters);
			
			Servicio servicio = servicioResponseElement.getResult();
			
			return servicio;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
		return null;
		
	}
	
			
	
	public List<Generico> procesaDatosDeGraficos(MessageElement[] get_any) {
		
		List<Generico> listaGenerica = new ArrayList<Generico>();
		Iterator<?> it = get_any[0].getChildElements();

		while (it.hasNext()) {
			
			MessageElement me = (MessageElement) it.next();
			Iterator<?> it2 = me.getChildElements();
			
			Object objetoX = "";
			int objetoY = 1;
			//va a la siguiente columna
			if(it2.hasNext()){
				MessageElement me2 = (MessageElement) it2.next();
				objetoX = me2.getValue();
			}
			
			//va a la siguiente columna
			if(it2.hasNext()){
				MessageElement me3 = (MessageElement) it2.next();
				objetoY = Integer.parseInt(me3.getValue());
			}
			
			

			listaGenerica.add(new Generico(objetoX,objetoY));
			
			

		}
		return listaGenerica;

	}



	
	public List<WsgServicio> procesaDatosServiciosDeUsuario(MessageElement[] get_any) {
		// TODO Auto-generated method stub
		Iterator<?> it = get_any[0].getChildElements();
		List<WsgServicio> wsgServicios = new ArrayList<WsgServicio>();

		while (it.hasNext()) {
			MessageElement me = (MessageElement) it.next();
			
			Iterator<?> it2 = me.getChildElements();
			
			WsgServicio wsgServicio = new WsgServicio();
			
			//it2.hasNext();
			MessageElement me2 = (MessageElement) it2.next();
			wsgServicio.setIdServicio(new Long(me2.getValue()));
			
			//it2.hasNext();
			MessageElement me3 = (MessageElement) it2.next();
			wsgServicio.setDescripcion(me3.getValue());
			
			
			wsgServicios.add(wsgServicio);
			

		}
		return wsgServicios;
	}



	public String procesaDatosIdServicio(MessageElement[] get_any) {
		
		Iterator<?> it = get_any[0].getChildElements();

		String query = "";
		while (it.hasNext()) {
			MessageElement me = (MessageElement) it.next();
			
			Iterator<?> it2 = me.getChildElements();
			
			
			
			//it2.hasNext();
			MessageElement me2 = (MessageElement) it2.next();
			query = me2.getValue();
			
			

		}
		return query;
	}
		
	

}