/*** Implantacion del Server ***/

/** Paso 1: Incluir el class package de OMG CORBA **/
import AmazonTecApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.io.*;
import java.util.Properties;

public class ServerCorba
{
    public static void main(String args[]) throws Exception
    {
        /** Paso 2: Inicializar el ORB del Server **/
        ORB orb = ORB.init(args,null);
        
        /** Paso 3: Obtener la referencia a RootPOA y activar POAManager **/
        POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
        rootpoa.the_POAManager().activate();
        
        /** Paso 4: Crear una instancia del objeto MensajeObject **/
        AmazonTecObject objeto = new AmazonTecObject();
        objeto.setORB(orb);
        
        /** Paso 5: Obtener una referencia al objeto del server **/
        org.omg.CORBA.Object reference = rootpoa.servant_to_reference(objeto);
        AmazonTec hreference = AmazonTecHelper.narrow(reference);
        
        /** Paso 6: Obtener una referencia del objeto en el Servicio de Nombres ("NameService") **/
        org.omg.CORBA.Object contextObj = orb.resolve_initial_references("NameService");
        NamingContextExt rootContext = NamingContextExtHelper.narrow(contextObj);
        
        /** Paso 7: Insertar (binding) la referencia del Objeto Hello en el Servicio de nombres: "NameService" **/
        String nombre = "Server";
        NameComponent path[] = rootContext.to_name(nombre);
        rootContext.rebind(path,hreference);
        
        /** Paso 8: Esperar la requisicion de un cliente al objeto **/
        java.lang.Object sync = new java.lang.Object();
        
        System.out.println("Servidor CORBA \n Esperando conexiones...");
        synchronized (sync) 
        {
            sync.wait();
        }
        
        //System.out.println("Esperando conexiones...");
        //orb.run();	
    }
}
