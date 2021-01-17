/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vista.vista;

/**
 *
 * @author Santiago
 */
public class relojCPU extends Thread {
    public vista objvista=new vista();

    public modelo objmodelo = new modelo();
    public void iniciar(){
      
        objvista.setVisible(true);
    }
    @Override  
    public void run() {

              int I=0;
               while(true){
         
            try { 
               this.nuevoAListo();
                this.listoEJecucion();
                this.Ejecucion();
                this.ejecucionATerminado();
                this.ejecucionAListo();
                /*this.actualizarBloqueo();
                this.actualizarEjecucion();
                this.actualizarListo();
                this.actualizarNuevo();
                this.actualizarTerminado();*/
                this.vaciarTablas();
                this.addRowToSmallTable();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(relojCPU.class.getName()).log(Level.SEVERE, null, ex);
            }
      }
   }

   public void nuevoAListo(){
     
       //cuando hayan procesos en nuevo...
       if(objvista.getNuevo().size() > 0){
           objmodelo=new modelo();
           System.out.println(objvista.getNuevo().get(0).getNombre());
           objmodelo.setId(objvista.getNuevo().get(0).getId());
           objmodelo.setNombre(objvista.getNuevo().get(0).getNombre());
           objmodelo.setTamano(objvista.getNuevo().get(0).getTamano());
           objvista.getListo().add(objmodelo);
           objvista.getNuevo().remove(0);
          
       }
   }
  public void listoEJecucion(){
    
       //cuando hayan procesos en nuevo...
       if(objvista.getListo().size() > 0){
           objmodelo=new modelo();
           System.out.println(objvista.getListo().get(0).getNombre());
           objmodelo.setId(objvista.getListo().get(0).getId());
           objmodelo.setNombre(objvista.getListo().get(0).getNombre());
           objmodelo.setTamano(objvista.getListo().get(0).getTamano());
           objvista.getEjecucion().add(objmodelo);
           objvista.getListo().remove(0);
       
       }
  }
  public void Ejecucion(){
       //cuando hayan procesos en nuevo...
       if(objvista.getEjecucion().size() > 0){
           objvista.getEjecucion().get(0).setTamano(objvista.getEjecucion().get(0).getTamano()-1);
      }
      
  }
  public void ejecucionAListo(){
    
    //cuando hayan procesos en nuevo...
       if(objvista.getEjecucion().size() > 0){ 
           objmodelo=new modelo();
           objmodelo.setId(objvista.getEjecucion().get(0).getId());
           objmodelo.setNombre(objvista.getEjecucion().get(0).getNombre());
           objmodelo.setTamano(objvista.getEjecucion().get(0).getTamano());
           objvista.getListo().add(objmodelo);
           objvista.getEjecucion().remove(0);
     
       }
 
  }
  public void vaciarTablas(){
        DefaultTableModel model_listo = (DefaultTableModel) this.objvista.getTbl_listo().getModel();
        DefaultTableModel model_ejecu = (DefaultTableModel) this.objvista.getTbl_ejecucion().getModel();
        DefaultTableModel model_bloq = (DefaultTableModel) this.objvista.getTbl_bloqueado().getModel();
        DefaultTableModel model_term = (DefaultTableModel) this.objvista.getTbl_terminado().getModel();
         DefaultTableModel model_nuevo = (DefaultTableModel) this.objvista.getTbl_nuevo().getModel();
        int tam_listo=model_listo.getRowCount();
        int tam_ejecu=model_ejecu.getRowCount();
        int tam_bloq=model_bloq.getRowCount();
        int tam_term=model_term.getRowCount();
        int tam_nuevo=model_nuevo.getRowCount();
        for (int i = 0;i<tam_listo; i++) {
                model_listo.removeRow(0);
            }
         for (int i = 0;i<tam_ejecu; i++) {
                model_ejecu.removeRow(0);
            }
          for (int i = 0;i<tam_bloq; i++) {
                model_bloq.removeRow(0);
            }
           for (int i = 0;i<tam_nuevo; i++) {
                model_nuevo.removeRow(0);
            }
           
  }
     public void addRowToSmallTable()
    {
        DefaultTableModel model_listo = (DefaultTableModel) this.objvista.getTbl_listo().getModel();
        DefaultTableModel model_ejecu = (DefaultTableModel) this.objvista.getTbl_ejecucion().getModel();
        DefaultTableModel model_bloq = (DefaultTableModel) this.objvista.getTbl_bloqueado().getModel();
        DefaultTableModel model_term = (DefaultTableModel) this.objvista.getTbl_terminado().getModel();
        
        Object rowData[] = new Object[2];
        //mostrar en las tablas los datos que contiene los arraylist en ejecucion
        try{
        for(int i = 0; i < objvista.getListo().size(); i++)
        {
            rowData[0] = objvista.getListo().get(i).getId();
            rowData[1] = objvista.getListo().get(i).getTamano();
              model_listo.addRow(rowData);
        }
        for(int i = 0; i < objvista.getEjecucion().size(); i++)
        {
            rowData[0] = objvista.getEjecucion().get(i).getId();
            rowData[1] = objvista.getEjecucion().get(i).getTamano();
              model_ejecu.addRow(rowData);
        }
        for(int i = 0; i < objvista.getBloqueado().size(); i++)
        {
            rowData[0] = objvista.getBloqueado().get(i).getId();
            rowData[1] = objvista.getBloqueado().get(i).getTamano();
              model_bloq.addRow(rowData);
        }
        for(int i = 0; i < objvista.getTerminado().size(); i++)
        {
            rowData[0] = objvista.getTerminado().get(i).getId();
            rowData[1] = objvista.getTerminado().get(i).getTamano();
              model_term.addRow(rowData);
        }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
  public void ejecucionATerminado(){
     
    //cuando hayan procesos en nuevo...
       if(objvista.getEjecucion().size() > 0){ 
            for(int i=0;i<objvista.getEjecucion().size();i++){
               if(objvista.getEjecucion().get(i).getTamano() <= 0 ){
                   objmodelo=new modelo();
                   objmodelo.setId(objvista.getEjecucion().get(i).getId());
                   objmodelo.setNombre(objvista.getEjecucion().get(i).getNombre());
                   objmodelo.setTamano(objvista.getEjecucion().get(i).getTamano());
                   objvista.getTerminado().add(objmodelo);
                   objvista.getEjecucion().remove(i);
               }
           }
       }
  }
 
  
}
