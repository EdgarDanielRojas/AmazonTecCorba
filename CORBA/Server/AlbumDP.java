
import java.util.StringTokenizer;

public class AlbumDP
{
    // Atributos
    private String id,artista, album, edicion;
    private float precio;
    private int existencia;
    
    // Constructores
    public AlbumDP()
    {
    	this.id = "";
        this.artista = "";
        this.album   = "";
        this.edicion = "";
        this.precio = 0;
        this.existencia = 0;
    }
    
    public AlbumDP(String datos)
    {
        StringTokenizer st = new StringTokenizer(datos,"_");
        this.id =st.nextToken();
        this.artista = st.nextToken();
        this.album   = st.nextToken();
        this.edicion = st.nextToken();
        this.precio = Float.parseFloat(st.nextToken());
        this.existencia = Integer.parseInt(st.nextToken());
    }
    
    // Metodos: Accesors (geter's)
    public String getArtista()
    {
        return this.artista;
    }
    
    public String getAlbum()
    {
        return this.album;
    }
    
    public String getEdicion()
    {
        return this.edicion;
    }
    
    public String getId()
    {
        return this.id;
    }
    public float getPrecio(){
    	return this.precio;
    }
    public int getExistencia(){
    	return this.existencia;
    }
    
    // Metodos: Mutators (seter's)
    public void setId(String i){
    	this.id = i;
    }
    public void setArtista(String artist)
    {
        this.artista = artist;
    }
    
    public void setAlbum(String disco)
    {
        this.album = disco;
    }
    
    public void setEdicion(String release)
    {
        this.edicion = release;
    }
    
    public void setPrecio(float pre)
    {
        this.precio = pre;
    }
    public void setExistencia(int exis){
    	this.existencia = exis;
    }
    public String toString()
    {
        return this.id+"_"+this.artista+"_"+this.album+"_"+this.edicion+"_"+this.precio+"_"+this.existencia;
    }
}
