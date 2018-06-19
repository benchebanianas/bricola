/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bean.Device;
import bean.Manager;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

/**
 *
 * @author Ashen One
 */
@Stateless
public class DeviceFacade extends AbstractFacade<Device> {

    @PersistenceContext(unitName = "bricolagePU")
    private EntityManager em;

    
    public void creerDevice(Device device) {
        device.setId(generateId("Device", "id"));
        create(device);
    }

    public Device verifDevice(Manager manager) {
        Device dev = getManagerDevice(manager);
        List<Device> device = em.createQuery("SELECT d From Device d WHERE d.os='" + dev.getOs() + "'"
                + " and d.manager.id LIKE '" + manager.getLogin() + "'").getResultList();
        if (device.isEmpty()) {
            return null;
        } else {
            return device.get(0);
        }
    }

    public Device getManagerDevice(Manager manager) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
        UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();
        ReadableUserAgent ag = parser.parse(httpServletRequest.getHeader("User-Agent"));
        Device device = new Device();
        device.setNavigateur(ag.getFamily().getName());
        device.setOs(ag.getOperatingSystem().getName());
        device.setDeviceCategorie(ag.getDeviceCategory().getName());
        device.setManager(manager);
        device.setDateConnection(new Date());
        InetAddress ip;
        try {

            ip = InetAddress.getLocalHost();
            device.setIp(ip.getHostAddress());

//            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
//
//            byte[] mac = network.getHardwareAddress();
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < mac.length; i++) {
//                sb.append(String.format("%02X%s", mac[i], (i < mac.length - 1) ? "-" : ""));
//            }
//            device.setMacAdresss(sb.toString());

        } catch (UnknownHostException e) {

            e.printStackTrace();

        } 
        
        return device;
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DeviceFacade() {
        super(Device.class);
    }
    
}
