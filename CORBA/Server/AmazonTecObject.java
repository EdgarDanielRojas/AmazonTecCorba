/** Implmentacion de Mensaje **/

import AmazonTecApp.*;
import org.omg.CORBA.*;
import java.util.StringTokenizer;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class AmazonTecObject extends AmazonTecPOA
{
    private ORB orb;
    private Connection conexion;
    private Statement statement;
    
    private AlbumDP albumdp;
    private String error="none";
    
    public void setORB(ORB orb_val)
    {
        orb = orb_val;
    }
    
    public AmazonTecObject()
    {
    	//super();
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            conexion = DriverManager.getConnection("jdbc:mysql://localhost/amazonTec?user=root");
            System.out.println("Conexion exitosa a la BD en MySQL...");
        }
        catch(ClassNotFoundException cnfe)
        {
            System.out.println("Error: "+cnfe);
            error="Found 1";
        }
        catch(InstantiationException ie)
        {
            System.out.println("Error: "+ie);
            error="Found 2";
        }
        catch(IllegalAccessException iae)
        {
            System.out.println("Error: "+iae);
            error="Found 3";
        }
        catch(SQLException sqle)
        {
            System.out.println("Error: "+sqle);
            error="Found 4";
        }
        
    }
    
    public String obtenerMensaje(String mensaje)
    {
        return "Mensaje del Cliente: " + mensaje + "\nMensaje del Server CORBA: Hola Cliente CORBA ...";
    }
    
     public String altaUsuario(String email,String nombre, String direccion, String notarjeta, String tarjeta, String password, String telefono) {
    	String resultado;
    	String insert = "INSERT INTO cliente(email,nombre,direccion,numerodetarjeta,tarjeta,password,telefono) VALUES('"+email+"','"+nombre+"','"+direccion+"','"+notarjeta+"','"+tarjeta+"','"+password+"','"+telefono+"');";
    	try{
    	statement = conexion.createStatement();
    	statement.executeUpdate(insert);
    	statement.close();
    	resultado = "Captura correcta";
    	}
    	catch(SQLException ioe){
    		System.out.println("Error: " + ioe);
    		resultado = "Error en captura";
    	}
    	return resultado;
    }
    
    public String idCliente(String email){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT id_cliente FROM cliente WHERE email='"+email+"';";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                datos=tr.getString(1);            
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public void actualizarInventario(String album,String cantidad){
    	String resultado;
    	String insert = "UPDATE album set existencia=existencia-"+cantidad+" where id_album="+album+";";
    	try{
    	statement = conexion.createStatement();
    	statement.executeUpdate(insert);
    	statement.close();
    	resultado = "Captura correcta";
    	}
    	catch(SQLException ioe){
    		System.out.println("Error: " + ioe);
    		resultado = "Error en captura";
    	}
    }
    
    public String infoDiscoVenta(String album){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT nombre FROM album WHERE id_album="+album+";";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
            	datos=tr.getString(1);         
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String nombreCliente(String id){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT nombre FROM cliente WHERE email='"+id+"';";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                datos=tr.getString(1);         
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String compra(String album,String cliente, String cantidad){
    	String resultado;
    	String insert = "INSERT INTO compra(cliente,album,cantidad) VALUES("+idCliente(cliente)+","+album+","+cantidad+");";
    	actualizarInventario(album,cantidad);
    	try{
    	statement = conexion.createStatement();
    	statement.executeUpdate(insert);
    	statement.close();
    	resultado= "Hola "+nombreCliente(cliente)+"\n";
    	resultado = resultado+ "Compra realizada del siguiente producto :\n";
    	resultado= resultado + "Album: "+infoDiscoVenta(album) + " Cantidad:"+cantidad+"\n";
    	Date date= new Date();
    	DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    	resultado= resultado +"En la fecha y hora: "+ dateFormat.format(date);
    	}
    	catch(SQLException ioe){
    		System.out.println("Error: " + ioe);
    		resultado = "Error en captura";
    	}
    	return resultado;
    }
     
    public String infoAlbum(String id){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT nombre,precio FROM album WHERE id_album="+id+";";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                datos="nombreAlbum="+tr.getString(1); 
                datos=datos+"&precio="  +tr.getString(2);         
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String verifyUser(String email,String password){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT nombre,direccion,numerodetarjeta FROM cliente WHERE email='"+email+"' and password='"+password+"';";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
            	datos="email="+email;
                datos=datos + "&nombre="+tr.getString(1); 
                datos=datos+"&direccion="  +tr.getString(2); 
                	datos=datos+"&notarjeta="  +tr.getString(3);           
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String obtenerCanciones(String album){
    	String datos="";
    	String info="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT * FROM cancion WHERE album="+album+";";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                info=tr.getString("nombre");
                info=info+"_"+tr.getString("compositor");
               	info=info+"_"+tr.getString("duracion");
                
                //datos = datos + albumdp.toString()+ "\n";
                //datos = datos + albumdp.toStringHtml();
                datos = datos + info+ "*";
                
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String obtenerArtista(String id){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT nombre FROM artista WHERE id_artista="+id+";";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                datos=tr.getString(1);            
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String obtenerId(String artista){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT id_artista FROM artista WHERE nombre='"+artista+"';";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                datos=tr.getString(1);            
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
            System.out.println(datos);
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String obtenerCosto(String id){
    	String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT precio FROM album WHERE id_album="+id+";";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            while(tr.next())
            {
                datos=tr.getString(1);            
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String obtenerAlbums(String artista)
    {
        String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        artista= obtenerId(artista);
        query = "SELECT * FROM album WHERE artista="+artista+";";
        //return query+error;
        
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            albumdp = new AlbumDP();
            while(tr.next())
            {
                albumdp.setId(tr.getString(1));
                albumdp.setArtista(obtenerArtista(tr.getString(2)));
                albumdp.setAlbum(tr.getString(3));
                albumdp.setEdicion(tr.getString(4));
                albumdp.setPrecio(Float.parseFloat(tr.getString(5)));
                albumdp.setExistencia(Integer.parseInt(tr.getString(6)));
                datos = datos + albumdp.toString()+ "*";
                
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
    
    public String obtenerCatalogo()
    {
        String datos="";
        String query;
        ResultSet tr;
        boolean encontrado=false;
        
        query = "SELECT * FROM album;";
        //return query+error;
        try
        {
            // 1. Abrir archivo
            //archivoEntrada = new BufferedReader(new FileReader("Clientes.txt"));
            statement = conexion.createStatement();
            
            // 2. Procesar datos
            //while(archivoEntrada.ready())
            //	datos = datos + archivoEntrada.readLine() + "\n";
            tr = statement.executeQuery(query);
            albumdp = new AlbumDP();
            while(tr.next())
            {
            	albumdp.setId(tr.getString(1));
                albumdp.setArtista(obtenerArtista(tr.getString(2)));
                albumdp.setAlbum(tr.getString(3));
                albumdp.setEdicion(tr.getString(4));
                albumdp.setPrecio(Float.parseFloat(tr.getString(5)));
                albumdp.setExistencia(Integer.parseInt(tr.getString(6)));
                
                //datos = datos + albumdp.toString()+ "\n";
                //datos = datos + albumdp.toStringHtml();
                datos = datos + albumdp.toString()+ "*";
                
                encontrado = true;
            }
            
            // 3. Cerrar archivo
            //archivoEntrada.close();
            statement.close();
            
            System.out.println(conexion.nativeSQL(query));
        }
        catch(SQLException sqle)
        {
            datos = "ERROR";
            System.out.println("Error: "+sqle);
        }
        
        if(!encontrado)
            datos = "NO_LOCALIZADO";
        
        return datos;
    }
}
