package backend;

public class Reacciones {
    private Integer id;
    private String nombre;
    private  Integer idUsuario;

    public Reacciones(int idP, String nombre, int idU){
        id =idP;
        this.nombre=nombre;
        idUsuario =idU;
        //textoExpresiones=new String [] {"(1)like","(2)love","(3)sad","(4)happy","(5)mad","(6)sarprise","(7)core","(8)do'nt core","(9)pleaseExplain"};
        //expresines=new String[]{"\uD83D\uDC4D","❤","\uD83D\uDE25","\uD83D\uDE42","\uD83D\uDE20","\uD83D\uDE2E","\uD83D\uDE18 ","\uD83D\uDE11","❔"};

    }
    public Reacciones(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
