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
    private int tiempo_bloqueo=0;
    public modelo objmodelo = new modelo();
    public void iniciar(){
      
        objvista.setVisible(true);
    }
    @Override  
    public void run() {

  
               while(true){
         
            try { 
                this.nuevoAListo();
                this.actualizarEstados();
                this.dibujarEnEstadisticas();
                this.listoEJecucion();
                this.actualizarEstados();
                this.dibujarEnEstadisticas();
                this.Ejecucion();
                this.actualizarEstados();
                this.dibujarEnEstadisticas();
                this.ejecucionATerminado();
                this.actualizarEstados();
                this.dibujarEnEstadisticas();
                this.ejecucionAListo();
                this.actualizarEstados();
                this.dibujarEnEstadisticas();
                this.dibujar();
                this.mostrarterminidado();
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
         
           objmodelo.setId(objvista.getNuevo().get(0).getId());
           objmodelo.setNombre(objvista.getNuevo().get(0).getNombre());
           objmodelo.setTamano(objvista.getNuevo().get(0).getTamano());
           objmodelo.setSbloq(objvista.getNuevo().get(0).getSbloq());
           objvista.getListo().add(objmodelo);
           objvista.getNuevo().remove(0);
          
       }
   }
   public void verEstados(){
       for(int i=0;i<objvista.getNuevo().size();i++){
           objvista.getNuevo().get(i).setEstado("nuevo");
       }
       for(int i=0;i<objvista.getListo().size();i++){
           objvista.getListo().get(i).setEstado("listo");
       }
       for(int i=0;i<objvista.getBloqueado().size();i++){
           objvista.getBloqueado().get(i).setEstado("bloqueado");
       }
       for(int i=0;i<objvista.getEjecucion().size();i++){
           objvista.getEjecucion().get(i).setEstado("ejecucion");
       }
       for(int i=0;i<objvista.getTerminado().size();i++){
           objvista.getTerminado().get(i).setEstado("nuevo");
       }
   }
  public void listoEJecucion(){
    
       //cuando hayan procesos en nuevo...
       if(objvista.getListo().size() > 0){
           objmodelo=new modelo();
        
           objmodelo.setId(objvista.getListo().get(0).getId());
           objmodelo.setNombre(objvista.getListo().get(0).getNombre());
           objmodelo.setTamano(objvista.getListo().get(0).getTamano());
           objmodelo.setSbloq(objvista.getListo().get(0).getSbloq());
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

  public void dibujarEnListo(){
      
      DefaultTableModel model_listo = (DefaultTableModel) this.objvista.getTbl_listo().getModel();
       int a =model_listo.getRowCount()-1;
      for(int i=a; i>=0;i--){
        model_listo.removeRow(i);
      }
      Object rowData[] = new Object[2];
      for(int i = 0; i < objvista.getListo().size(); i++)
        {
            rowData[0] = objvista.getListo().get(i).getId();
            rowData[1] = objvista.getListo().get(i).getTamano();
              model_listo.addRow(rowData);
        }
  }
  public void dibujarEnEjecucion(){
      DefaultTableModel model_ejec = (DefaultTableModel) this.objvista.getTbl_ejecucion().getModel();
      int a =model_ejec.getRowCount()-1;
      for(int i=a; i>=0;i--){
        model_ejec.removeRow(i);
      }
      Object rowData[] = new Object[2];
      for(int i=0; i< objvista.getEjecucion().size();i++){
          rowData[0] = objvista.getEjecucion().get(i).getId();
          rowData[1] = objvista.getEjecucion().get(i).getTamano();
          model_ejec.addRow(rowData);
      }
  }
  public void dibujarEnTerminado(){
      DefaultTableModel model_term = (DefaultTableModel) this.objvista.getTbl_terminados().getModel();
      int a =model_term.getRowCount()-1;
      for(int i=a; i>=0;i--){
        model_term.removeRow(i);
      }
      Object rowData[] = new Object[2];
      for(int i=0;i<objvista.getTerminado().size();i++){
          
         rowData[0]=objvista.getTerminado().get(i).getId();
         rowData[1]=objvista.getTerminado().get(i).getTamano();
         model_term.addRow(rowData);
      } 
  }
  public void dibujarEnEstadisticas(){
      
  DefaultTableModel model_estad = (DefaultTableModel) this.objvista.getTbl_estadprocesos().getModel();
   int a =model_estad.getRowCount()-1;
      for(int i=a; i>=0;i--){
        model_estad.removeRow(i);
      }
      Object rowData[] = new Object[6];
      for(int i=0;i<objvista.getEstadisticas().size();i++){
         rowData[0]=objvista.getEstadisticas().get(i).getId();
         rowData[1]=objvista.getEstadisticas().get(i).getNombre();
         rowData[2]=objvista.getEstadisticas().get(i).getTamano();
         rowData[3]="";
         rowData[4]="";
         rowData[5]=objvista.getEstadisticas().get(i).getEstado();
         model_estad.addRow(rowData);
      }
  }
  public void actualizarEstados(){
      for(int i=0;i<objvista.getEstadisticas().size();i++){
          int j;
          for(j=0;j<objvista.getNuevo().size();j++){
              if(objvista.getEstadisticas().get(i).getId() == objvista.getNuevo().get(j).getId()){
                  objvista.getEstadisticas().get(i).setEstado("nuevo");
              }
          }
          for(j=0;j<objvista.getListo().size();j++){
              if(objvista.getEstadisticas().get(i).getId() == objvista.getListo().get(j).getId()){
                  objvista.getEstadisticas().get(i).setEstado("listo");
              }
          }
          for(j=0;j<objvista.getBloqueado().size();j++){
              if(objvista.getEstadisticas().get(i).getId() == objvista.getBloqueado().get(j).getId()){
                  objvista.getEstadisticas().get(i).setEstado("bloquado");
              }
          }
          for(j=0;j<objvista.getEjecucion().size();j++){
              if(objvista.getEstadisticas().get(i).getId() == objvista.getEjecucion().get(j).getId()){
                  objvista.getEstadisticas().get(i).setEstado("ejecucion");
              }
          }
          for(j=0;j<objvista.getTerminado().size();j++){
              if(objvista.getEstadisticas().get(i).getId() == objvista.getTerminado().get(j).getId()){
                  objvista.getEstadisticas().get(i).setEstado("terminado");
              }
          }
      }
  }
  public void dibujar()
    {
        try{
            this.dibujarEnListo();
            this.dibujarEnEjecucion();
            this.dibujarEnTerminado();
            this.dibujarEnEstadisticas();
        }catch(Exception e){
            System.out.println("error"+e.getMessage());
        }
    }
  public void ejecucionATerminado(){
     
    //cuando hayan procesos en nuevo...
  
       if(objvista.getEjecucion().size() >= 0){ 
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
  public void mostrarterminidado(){
      System.out.println("Listo tiene: ");
      for(int i=0; i< objvista.getListo().size();i++){
          System.out.println(objvista.getListo().get(i).getId());
          System.out.println(objvista.getListo().get(i).getTamano());
      }
        System.out.println("");
          System.out.println("");
  }
  
}
