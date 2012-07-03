/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phesus.facturatron.lib.entities;

import mx.bigdata.sat.cfd.v22.schema.Comprobante.Conceptos.Concepto;



/**
 *
 * @author Octavio
 */
public class ConceptoTron extends Concepto {
    private String etiquetaImpuestos;


    /**
     * @return the etiquetaImpuestos
     */
    public String getEtiquetaImpuestos() {
        return etiquetaImpuestos;
    }

    /**
     * @param etiquetaImpuestos the etiquetaImpuestos to set
     */
    public void setEtiquetaImpuestos(String etiquetaImpuestos) {
        this.etiquetaImpuestos = etiquetaImpuestos;
    }
}
