/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phesus.facturatron.lib;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import mx.bigdata.sat.cfd.CFDv22;
import mx.bigdata.sat.cfd.v22.schema.Comprobante;
import mx.bigdata.sat.security.KeyLoader;
import phesus.facturatron.lib.entities.CFDv2Tron;
import phesus.facturatron.lib.entities.ComprobanteTron;

/**
 *
 * @author Octavio
 */
public class CFDFactory {
    public CFDv2Tron toCFD(ComprobanteTron comprobante) throws Exception {
        return digitalizar(comprobante);
    }


    private CFDv2Tron digitalizar(ComprobanteTron comprobante) throws Exception {

        CFDv2Tron cfdtron =  new CFDv2Tron();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);

        CFDv22 cfd = new CFDv22(comprobante);
        PrivateKey key = KeyLoader.loadPKCS8PrivateKey(new FileInputStream(new File(comprobante.getURIKey())),
                               comprobante.getPassKey());
        X509Certificate cert = KeyLoader
          .loadX509Certificate(new FileInputStream(new File(comprobante.getURICer())));
        Comprobante sellado = cfd.sellarComprobante(key, cert);
        cfd.validar();
        cfd.verificar();
        cfd.guardar(ps);

        comprobante.setNoCertificado(sellado.getNoCertificado());
        comprobante.setSello(sellado.getSello());
        comprobante.setCadenaOriginal(cfd.getCadenaOriginal());
        cfdtron.setComprobante(comprobante);
        cfdtron.setXML(baos.toString());
        return cfdtron;
    }
}
