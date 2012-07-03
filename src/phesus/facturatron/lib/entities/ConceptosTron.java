/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package phesus.facturatron.lib.entities;

import java.util.ArrayList;
import java.util.List;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Conceptos;
import mx.bigdata.sat.cfd.v22.schema.Comprobante.Conceptos.Concepto;
import mx.bigdata.sat.cfd.v22.schema.ObjectFactory;

/**
 *
 * @author Octavio
 */
public class ConceptosTron extends ArrayList<ConceptoTron> {

    public Conceptos toConceptos() {
        Conceptos cps = (new ObjectFactory()).createComprobanteConceptos();
        List<Concepto> list = cps.getConcepto();
        for (ConceptoTron conc : this) {
            list.add(conc);
        }
        return cps;
    }

}
