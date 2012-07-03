
 /* Author Phesus        //////////////////////////////
 *  ORC                 /////////////
 *                     /////////////
 *                    /////////////
 *                   /////////////
 * //////////////////////////////                   */

package phesus.facturatron.lib;

import java.io.*;
import java.util.List;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import phesus.facturatron.lib.entities.ComprobanteTron;

public class Reporte {
    JasperPrint jp;
    
    public Reporte (String reporteJasper, List<ComprobanteTron> beans) throws Exception
    {
        try {
            InputStream stream = cargarPlantilla(reporteJasper);
            jp         = JasperFillManager.fillReport(stream, new java.util.HashMap(), new net.sf.jasperreports.engine.data.JRBeanCollectionDataSource(beans));
        } catch(Exception e) {
            throw e;
        }
    }


    public InputStream cargarPlantilla(String archivoReporte) throws Exception
    {
        InputStream stream = new FileInputStream(archivoReporte);
        if(stream == null) { throw new Exception("Plantilla de reporte no encontrada. " + archivoReporte); }
        return stream;
    }
    public void toPDFFile(String pdfFile) throws JRException
    {
        JasperExportManager.exportReportToPdfFile(jp, pdfFile);
    }
    public void lanzarPreview()
    {
        JasperViewer jr = new net.sf.jasperreports.view.JasperViewer(jp, false);
        jr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jr.setVisible(true);
        jr.toFront();
        jr.setAlwaysOnTop(true);
    }
	
}

